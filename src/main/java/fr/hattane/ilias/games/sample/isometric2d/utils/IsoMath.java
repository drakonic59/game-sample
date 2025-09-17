package fr.hattane.ilias.games.sample.isometric2d.utils;

import java.awt.*;

import fr.hattane.ilias.games.sample.isometric2d.game.map.Tile;

public class IsoMath {

	//	Tile and Screen
	
	public static int[] screenToTile(int mx, int my, int originX, int originY,
                                     int camX, int camY, int tileW, int tileH) {
        int sx = mx - camX - originX;
        int sy = my - camY - originY;
        double hw = tileW / 2.0;
        double hh = tileH / 2.0;
        double fx = (sy / hh + sx / hw) / 2.0;
        double fy = (sy / hh - sx / hw) / 2.0;
        int tx = (int)Math.floor(fx);
        int ty = (int)Math.floor(fy);
        return new int[]{tx, ty};
    }

    public static Point tileToScreen(int tx, int ty, int originX, int originY,
                                     int camX, int camY, int tileW, int tileH) {
        int isoX = (tx - ty) * (tileW / 2);
        int isoY = (tx + ty) * (tileH / 2);
        return new Point(isoX + originX + camX, isoY + originY + camY);
    }
    
    /** écran -> tuile en tenant compte de la hauteur (h) et de la hauteur de niveau (levelH, en px). */
    public static int[] screenToTile(int mx, int my,
                                     int originX, int originY,
                                     int camX, int camY,
                                     int tileW, int tileH,
                                     int h) {
        // on annule la caméra et l'origine
        int sx = mx - camX - originX;
        int sy = my - camY - originY;

        // IMPORTANT : on remet le plan "au sol" (au rendu on a soustrait h*levelH, on l'ajoute ici)
        sy += h * Tile.TILE_LEVEL_HEIGHT;

        double hw = tileW / 2.0;
        double hh = tileH / 2.0;

        double fx = (sy / hh + sx / hw) * 0.5;
        double fy = (sy / hh - sx / hw) * 0.5;

        int tx = (int)Math.floor(fx);
        int ty = (int)Math.floor(fy);
        return new int[]{tx, ty};
    }

    /** tuile -> écran en tenant compte de la hauteur (h) et de la hauteur de niveau (levelH, en px). */
    public static Point tileToScreen(int tx, int ty,
                                     int originX, int originY,
                                     int camX, int camY,
                                     int tileW, int tileH,
                                     int h) {
        int isoX = (tx - ty) * (tileW / 2);
        int isoY = (tx + ty) * (tileH / 2);

        // on lève la tuile de h niveaux (décale vers le haut)
        isoY -= h * Tile.TILE_LEVEL_HEIGHT;

        return new Point(isoX + originX + camX, isoY + originY + camY);
    }
    
    public static double pythagore(int ab, int bc) {
    	
    	int ab2 = ab * ab;
    	int bc2 = bc * bc;
    	return Math.sqrt(ab2 + bc2);
    	
    }

}
