package fr.hattane.ilias.games.sample.isometric2d.utils;

import java.awt.Color;

public class ColorUtils {
	
	/** Renvoie une couleur assombrie ou éclaircie.
     *  factor > 1  → plus clair
     *  factor < 1  → plus sombre
     */
    public static Color adjustRGB(Color base, double factor) {
    	
        factor = Math.max(0.0, factor);
        int r = (int)Math.min(255, base.getRed()   * factor);
        int g = (int)Math.min(255, base.getGreen() * factor);
        int b = (int)Math.min(255, base.getBlue()  * factor);
        
        return new Color(r, g, b, base.getAlpha());
    }
    
    /** Éclaircit 
     * 		(positive percent) ou assombrit 
     * 		(negative percent) en jouant sur la brightness
     */
    public static Color adjustBrightness(Color base, double percent) {
        float[] hsb = Color.RGBtoHSB(base.getRed(), base.getGreen(), base.getBlue(), null);
        // percent = +0.2 => +20% de luminosité
        float b = clamp(hsb[2] + (float)percent);
        return Color.getHSBColor(hsb[0], hsb[1], b);
    }
    
    private static float clamp(float v) { return Math.max(0f, Math.min(1f, v)); }
	
}
