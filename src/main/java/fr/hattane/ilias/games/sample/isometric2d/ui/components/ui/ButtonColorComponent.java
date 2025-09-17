package fr.hattane.ilias.games.sample.isometric2d.ui.components.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.config.Sounds;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.UiComponent;
import fr.hattane.ilias.games.sample.isometric2d.utils.Function;

public class ButtonColorComponent extends UiComponent implements Button {

	private Color background = Color.white;
	private Color backgroundHover = Color.gray;
	private Color backgroundClicked = Color.black;
	
	private boolean border = true;
	private int borderSize = 2;
	private Color borderColor = Color.black;
	private Color borderHoverColor = Color.black;
	private Color borderClickedColor = Color.black;
	
	private boolean withText = true;
	private String text;
	private Color textColor = Color.BLACK;	
	
	private Font font = new Font("Consolas", Font.PLAIN, 40);
	private boolean bold = false;
	
	private boolean rounded = true;
	
	private boolean mouseHover = false;
	private boolean mouseClicked = false;
	
	private boolean checkable = true;
	
	private Function postAction;
	
	public ButtonColorComponent() {}
	
	public ButtonColorComponent(int x, int y, int width, int height, Color background, Color backgroundHover, Color backgroundClicked, Function postAction) {
		super(x, y, width, height);
		this.background = background;
		this.backgroundHover = backgroundHover;
		this.backgroundClicked = backgroundClicked;
		this.postAction = postAction;
	}
	
	@Override
	public void draw(Graphics g) {
		
		if (isVisible() && (getBackground() != null || isHover() || isClicked())) {
			
			Color background = getBackground();
			if (isMouseClicked())
				background = getBackgroundClicked();
			else if (isMouseHover())
				background = getBackgroundHover();
			
			if (rounded) {
				
				if (isBorder()) {
					
					Color border = getBorderColor();
					if (isMouseClicked())
						border = getBorderClickedColor();
					else if (isMouseHover())
						border = getBorderHoverColor();
					
					g.setColor(border);
					g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);
					
					g.setColor(background);
					g.fillRoundRect(getX() + getBorderSize(), getY() + getBorderSize(), getWidth() - getBorderSize()*2, getHeight() - getBorderSize()*2, 10, 10);
					
				} else {
				
					g.setColor(background);
					g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);
					
				}
				
			} else {
				
				if (isBorder()) {
					
					Color border = getBorderColor();
					if (isMouseClicked())
						border = getBorderClickedColor();
					else if (isMouseHover())
						border = getBorderHoverColor();
					
					g.setColor(border);
					g.fillRect(getX(), getY(), getWidth(), getHeight());
					
					g.setColor(background);
					g.fillRect(getX() + getBorderSize(), getY() + getBorderSize(), getWidth() - getBorderSize()*2, getHeight() - getBorderSize()*2);
					
				} else {
				
					g.setColor(background);
					g.fillRect(getX(), getY(), getWidth(), getHeight());
					
				}
				
			}
			
			if (isWithText()) {
				
				g.setColor(getTextColor());
				g.setFont(getFont());
				g.drawString(getText(), getX() + getWidth()/2 - 12, getY() + getHeight()/2 + 13);
				
			}
			
		}
		
	}

	@Override
	public void update(long msTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hover(int x, int y) {
		
		if (isVisible()) {
			
			if (x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight()) {

				mouseHover = true;				
				return true;
	
			} else
				mouseHover = false;
		}
		
		return false;
	}

	@Override
	public boolean clicked(int x, int y) {
		
		if (isVisible()) {
			
			if (x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight()) {
				
				mouseClicked = true;
				return true;
				
			} else
				mouseClicked = false;
			
		}
		
		return false;
	}

	@Override
	public boolean clickReleased() {
		
		if (isVisible()) {
			
			if (mouseClicked) {

				if (mouseClicked)
					Main.playSound(Sounds.BUTTON_CLICKED);
				
				mouseClicked = false;
				
				if (postAction != null)
					postAction.execute(null);
				
				return true;
				
			}
			
		}
		
		return false;
	}

	@Override
	public boolean hoverReleased() {
		
		if (isVisible()) {
			
			if (mouseHover) {
				
				mouseHover = false;
				return true;
				
			}
			
		}
		
		return false;
	}
	
	@Override
	public boolean isClicked() {
		return isVisible() && mouseClicked;
	}
	
	@Override
	public boolean isHover() {
		return isVisible() && mouseHover;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getBackgroundHover() {
		return backgroundHover;
	}

	public void setBackgroundHover(Color backgroundHover) {
		this.backgroundHover = backgroundHover;
	}

	public Color getBackgroundClicked() {
		return backgroundClicked;
	}

	public void setBackgroundClicked(Color backgroundClicked) {
		this.backgroundClicked = backgroundClicked;
	}

	public boolean isMouseHover() {
		return mouseHover;
	}

	public void setMouseHover(boolean mouseHover) {
		this.mouseHover = mouseHover;
	}

	public boolean isMouseClicked() {
		return mouseClicked;
	}

	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}

	public Function getPostAction() {
		return postAction;
	}

	public void setPostAction(Function postAction) {
		this.postAction = postAction;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public boolean isWithText() {
		return withText;
	}

	public void setWithText(boolean withText) {
		this.withText = withText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
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

	public boolean isRounded() {
		return rounded;
	}

	public void setRounded(boolean rounded) {
		this.rounded = rounded;
	}

	public Color getBorderHoverColor() {
		return borderHoverColor;
	}

	public void setBorderHoverColor(Color borderHoverColor) {
		this.borderHoverColor = borderHoverColor;
	}

	public Color getBorderClickedColor() {
		return borderClickedColor;
	}

	public void setBorderClickedColor(Color borderClickedColor) {
		this.borderClickedColor = borderClickedColor;
	}

	public void setCheckable(boolean checkable) {
		this.checkable = checkable;
	}

	@Override
	public boolean isCheckable() {
		return checkable;
	}

}
