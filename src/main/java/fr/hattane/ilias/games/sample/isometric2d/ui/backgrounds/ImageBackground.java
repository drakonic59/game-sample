package fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageBackground extends Background {
	
	private BufferedImage image;
	
	public ImageBackground() {
		super();
	}

	public ImageBackground(int x, int y, int width, int height, BufferedImage image) {
		super(x, y, width, height);
		this.image = image;
	}

	public ImageBackground(int x, int y, int width, int height, String path) {
		super(x, y, width, height);
		
		try {
			this.image = ImageIO.read(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void draw(Graphics g) {

		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
