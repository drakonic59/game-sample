package fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import fr.hattane.ilias.games.sample.isometric2d.ui.components.UiComponent;

public class Label extends UiComponent {
	
	private String text;
	
	private Color color = Color.BLACK;
	
	private Font font = new Font("Consolas", Font.PLAIN, 50);
	private boolean bold = false;
	
	public Label() {}
	
	public Label(int x, int y, int width, int height, String text) {
		super(x, y, width, height);
		this.text = text;
	}
	
	@Override
	public void draw(Graphics g) {
		
		if (isVisible()) {
			
			g.setColor(getColor());
			g.setFont(getFont());
			g.drawString(getText(), getX(), getY());
			
		}
		
	}
	
	@Override
	public void update(long msTime) {
		// TODO Auto-generated method stub
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
