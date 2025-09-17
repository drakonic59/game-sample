package fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds;

import java.awt.image.BufferedImage;

public class SpriteSheetBackground {
	
    private final BufferedImage sheet;
    private final int w, h;

    public SpriteSheetBackground(BufferedImage sheet, int tileWidth, int tileHeight) {
        this.sheet = sheet;
        this.w = tileWidth;
        this.h = tileHeight;
    }

    public BufferedImage get(int cx, int cy) {
        return sheet.getSubimage(cx * w, cy * h, w, h);
    }
}
