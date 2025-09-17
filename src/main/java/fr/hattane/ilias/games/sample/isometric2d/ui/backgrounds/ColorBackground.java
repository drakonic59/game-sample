package fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds;

import java.awt.Color;
import java.awt.Graphics;

public class ColorBackground extends Background {
		
	private int red;
	private int green;
	private int blue;
	
	private float alpha;
	
	private Color color;
	
	public ColorBackground() {
		super();
	}

	public ColorBackground(int x, int y, int width, int height, int red, int green, int blue, float alpha) {
		super(x, y, width, height);
		this.red = red;
		this.green = green;
		this.blue = blue;
		setAlpha(alpha);
	}

	public ColorBackground(int x, int y, int width, int height, int red, int green, int blue) {
		super(x, y, width, height);
		this.red = red;
		this.green = green;
		this.blue = blue;
		setAlpha(1.0f);
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
		this.color = new Color(red, green, blue, (int) Math.min(254, alpha * 255));
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
		this.color = new Color(red, green, blue, (int) Math.min(254, alpha * 255));
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
		this.color = new Color(red, green, blue, (int) Math.min(254, alpha * 255));
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
		this.color = new Color(red, green, blue, (int) Math.min(254, alpha * 255));
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
	}
	
}
