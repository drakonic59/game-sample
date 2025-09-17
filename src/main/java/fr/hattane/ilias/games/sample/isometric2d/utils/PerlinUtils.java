package fr.hattane.ilias.games.sample.isometric2d.utils;

import java.awt.Color;
import java.util.Random;

import fr.hattane.ilias.games.sample.isometric2d.config.Colors;

public class PerlinUtils {
	
	/** Paramètres utilisateur. */
    public static class Params {
    	
        public long seed = (new Random()).nextLong();
        public double scale = 160.0;      // plus grand => features plus larges
        public int octaves = 6;
        public double persistence = 0.7;  // décroissance d'amplitude par octave
        public double lacunarity = 2.0;   // croissance de fréquence par octave

        public int maxHeight = 15;       // hauteur maximale (int)
        public boolean island = true;    // applique un falloff radial (île)
        public double islandStrength = 0.6; // 0..2  (1.0 recommandé)

        // Bruit d'humidité (pour le biome)
        public double moistureScale = 220.0;
        public int moistureOctaves = 8;
        public double moisturePersistence = 0.55;
        public double moistureLacunarity = 2.0;

        // Offsets (utile pour "scroller" une carte sans changer la graine)
        public double offsetX = 0.0, offsetY = 0.0;
        public double moistureOffsetX = 1000.0, moistureOffsetY = -1000.0; // éviter corrélation
        
    }

    /** Résultat : deux couches. */
    public static class Result {
    	
        public final Color[][] colors;
        public final int[][] heights;
        public Result(Color[][] colors, int[][] heights) { this.colors = colors; this.heights = heights; }
        
    }

    // ------------------------------------------------------------

    public static Result generate(int width, int height, Params p) {
    	
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Invalid size");
        Color[][] colors = new Color[height][width];
        int[][] heights = new int[height][width];

        Perlin perlinElev = new Perlin(p.seed);
        Perlin perlinMoist = new Perlin(p.seed * 31_415_926_1L ^ 0x9E3779B97F4A7C15L); // autre graine

        // Pré-calc
        double invScaleElev = 1.0 / Math.max(1e-9, p.scale);
        double invScaleMoist = 1.0 / Math.max(1e-9, p.moistureScale);

        // Pour falloff "île"
        double cx = (width - 1) * 0.5;
        double cy = (height - 1) * 0.5;
        double maxDist = Math.hypot(cx, cy);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // ----- Hauteur (FBM Perlin) -----
                double nx = (x + p.offsetX) * invScaleElev;
                double ny = (y + p.offsetY) * invScaleElev;
                double elev = fbm(perlinElev, nx, ny, p.octaves, p.persistence, p.lacunarity);

                // Normalise de [-1,1] -> [0,1]
                elev = (elev * 0.5) + 0.5;

                // Option "île" : falloff radial (plus on est loin du centre, plus on baisse)
                if (p.island) {
                    double dist = Math.hypot(x - cx, y - cy) / maxDist; // 0..1
                    double falloff = smoothstep(0.25, 1.0, dist);        // doux
                    elev = clamp01(elev - falloff * 0.85 * p.islandStrength);
                }

                // Échelle en hauteur entière
                int h = (int) Math.round(elev * p.maxHeight);
                heights[y][x] = h;

                // ----- Humidité (FBM Perlin) -----
                double mx = (x + p.moistureOffsetX) * invScaleMoist;
                double my = (y + p.moistureOffsetY) * invScaleMoist;
                double moist = fbm(perlinMoist, mx, my, p.moistureOctaves, p.moisturePersistence, p.moistureLacunarity);
                moist = (moist * 0.5) + 0.5; // 0..1

                // ----- Couleur de biome -----
                colors[y][x] = pickBiomeColor(elev, moist);
                
            }
        }
        
        return new Result(colors, heights);
    }

    // --------- Biomes simples (tu peux adapter les seuils/palettes) ----------
    private static Color pickBiomeColor(double elev, double moist) {
    	
        // seuils d'altitude (0..1)
        double waterDeep = 0.05;
        double water     = 0.30;
        double beach     = 0.32;
        double plains    = 0.55;
        double hills     = 0.70;
        double mountain  = 0.85;

        if (elev < waterDeep) return lerpColor(new Color(  5,  30,  80), new Color(  8,  45, 110), remap(elev, 0.00, waterDeep));
        if (elev < water)     return lerpColor(new Color( 10,  70, 140), new Color( 20, 100, 170), remap(elev, waterDeep, water));
        if (elev < beach)     return Colors.BEACH.getColor(); // plage

        // terres : variation selon humidité
        if (elev < plains) {
            return moist < 0.5 ? Colors.PLAIN_MEDIUM.getColor() // steppe
                               : Colors.PLAIN_LIGHT.getColor(); // prairie
        }
        if (elev < hills) {
            return moist < 0.4 ? Colors.PLAIN_MEDIUM.getColor() // broussailles
                               : Colors.PLAIN_HEIGHT.getColor(); // forêt
        }
        if (elev < mountain) {
            return Colors.STONE_MEDIUM.getColor(); // roche
        }
        
        return Color.WHITE; // neige
    }

    // --------- FBM Perlin 2D ----------
    private static double fbm(Perlin p, double x, double y, int octaves, double persistence, double lacunarity) {
    	
        double amp = 1.0;
        double freq = 1.0;
        double sum = 0.0;
        double norm = 0.0;
        
        for (int i = 0; i < octaves; i++) {
            sum += amp * p.noise2D(x * freq, y * freq);
            norm += amp;
            amp *= persistence;
            freq *= lacunarity;
        }
        
        // Perlin renvoie env. [-1,1], ici somme pondérée -> normalisons
        return sum / Math.max(1e-9, norm);
    }

    // --------- Outils ---------
    private static double clamp01(double v) { return v < 0 ? 0 : (v > 1 ? 1 : v); }
    private static double remap(double v, double a, double b) { return clamp01((v - a) / (b - a + 1e-9)); }
    private static double smoothstep(double a, double b, double x) {
        double t = clamp01((x - a) / (b - a + 1e-9));
        return t * t * (3 - 2 * t);
    }

    private static Color lerpColor(Color c1, Color c2, double t) {
    	
        t = clamp01(t);
        
        int r = (int)Math.round(c1.getRed()   + (c2.getRed()   - c1.getRed())   * t);
        int g = (int)Math.round(c1.getGreen() + (c2.getGreen() - c1.getGreen()) * t);
        int b = (int)Math.round(c1.getBlue()  + (c2.getBlue()  - c1.getBlue())  * t);
        
        return new Color(r, g, b);
    }

    // --------- Perlin 2D (classique), seedable ----------
    private static final class Perlin {
    	
        private final int[] perm = new int[512];

        Perlin(long seed) {
            int[] p = new int[256];
            for (int i = 0; i < 256; i++) p[i] = i;
            Random rnd = new Random(seed);
            for (int i = 255; i > 0; i--) {
                int j = rnd.nextInt(i + 1);
                int tmp = p[i]; p[i] = p[j]; p[j] = tmp;
            }
            for (int i = 0; i < 512; i++) perm[i] = p[i & 255];
        }

        double noise2D(double x, double y) {
        	
            int X = fastFloor(x) & 255;
            int Y = fastFloor(y) & 255;

            double xf = x - fastFloor(x);
            double yf = y - fastFloor(y);

            double u = fade(xf);
            double v = fade(yf);

            int aa = perm[X     + perm[Y    ]];
            int ab = perm[X     + perm[Y + 1]];
            int ba = perm[X + 1 + perm[Y    ]];
            int bb = perm[X + 1 + perm[Y + 1]];

            double x1 = lerp( grad(aa, xf,     yf    ), grad(ba, xf - 1, yf    ), u);
            double x2 = lerp( grad(ab, xf,     yf - 1), grad(bb, xf - 1, yf - 1), u);
            
            return lerp(x1, x2, v); // ~ [-1,1]
        }

        private static int fastFloor(double x) { int xi = (int)x; return x < xi ? xi - 1 : xi; }
        private static double fade(double t) { return t * t * t * (t * (t * 6 - 15) + 10); }
        private static double lerp(double a, double b, double t) { return a + t * (b - a); }
        private static double grad(int h, double x, double y) {
            switch (h & 3) {
                case 0: return  x + y;
                case 1: return -x + y;
                case 2: return  x - y;
                default:return -x - y;
            }
        }
    }
	
}
