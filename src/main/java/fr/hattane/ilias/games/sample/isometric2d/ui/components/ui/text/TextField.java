package fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.text;

import java.awt.Color;
import java.awt.Graphics;

import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.Button;

public class TextField extends Label implements Button {
	
	private Color backgroundColor = Color.WHITE;
	private Color borderColor = Color.BLACK;
	private Color selectedBorderColor = Color.BLUE;
	
	private int borderSize = 3;
	
	private int paddingLeft = 20;
	
	private boolean selected = false;
	private boolean hover = false;
	
	public TextField() {}
	
	public TextField(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
	
	public void entry(char c) {
		
		if (isVisible()) {
			
			if (getText() == null)
				setText("" + c);
			else if (getText().length() < 20)
				setText(getText() + c);
			
		}
		
	}
	
	public void removeLast() {
		
		if (isVisible())
			if (getText() != null && getText().length() > 0)
				setText(getText().substring(0, getText().length()-1));
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		if (isVisible()) {
			
			g.setColor(selected ? getSelectedBorderColor() : getBorderColor());
			g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);
			
			g.setColor(getBackgroundColor());
			g.fillRoundRect(getX() +borderSize, getY() + borderSize, getWidth() - borderSize*2, getHeight() - borderSize*2, 20, 20);
			
			g.setColor(getColor());
			g.setFont(getFont());
			g.drawString(getText(), getX() + paddingLeft, getY() + getHeight()/2 + getFont().getSize()/2 - 5);
			
		}
		
	}
	
	@Override
	public void update(long msTime) {
		// TODO Auto-generated method stub
		
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	public int getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getSelectedBorderColor() {
		return selectedBorderColor;
	}

	public void setSelectedBorderColor(Color selectedBorderColor) {
		this.selectedBorderColor = selectedBorderColor;
	}
	
	@Override
	public boolean clicked(int x, int y) {
		
		if (isVisible()) {
			
			if (x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight()) {
				
				selected = true;
				return true;
				
			} else
				selected = false;
			
		}
		
		return false;
	}

	@Override
	public boolean isClicked() {
		return isVisible() && selected;
	}

	@Override
	public boolean clickReleased() {
		return isVisible() && selected;
	}

	@Override
	public boolean hover(int x, int y) {
		
		if (isVisible()) {
			
			if (x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight()) {
				
				hover = true;
				return true;
				
			} else
				hoverReleased();
			
		}
		
		return false;
	}

	@Override
	public boolean hoverReleased() {
		
		if (isVisible()) {
			if (hover) {
				hover = false;
				selected = false;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean isHover() {
		return isVisible() && hover;
	}

	@Override
	public boolean isCheckable() {
		return isVisible();
	}
	
}
