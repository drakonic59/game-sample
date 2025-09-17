package fr.hattane.ilias.games.sample.isometric2d.ui.components.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.config.Images;
import fr.hattane.ilias.games.sample.isometric2d.config.Sounds;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.UiComponent;
import fr.hattane.ilias.games.sample.isometric2d.utils.Function;

public class ButtonImageComponent extends UiComponent implements Button {

	private Images image;
	private Images hover;
	private Images clicked;
	
	private boolean mouseHover = false;
	private boolean mouseClicked = false;
	
	private boolean checkable = true;
	
	private boolean lasHoverTrue = false;
	
	private Function postAction;
	
	public ButtonImageComponent() {}
	
	public ButtonImageComponent(int x, int y, int width, int height, Images image, Images hover, Images clicked, Function postAction) {
		super(x, y, width, height);
		this.image = image;
		this.hover = hover;
		this.clicked = clicked;
		this.postAction = postAction;
	}
	
	@Override
	public void draw(Graphics g) {
		
		if (isVisible()) {
			
			BufferedImage image = this.image.getImage();
			if (mouseClicked)
				image = getClicked().getImage();
			else if (mouseHover)
				image = getHover().getImage();
			
			g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
			
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
				
				if (!mouseHover && !lasHoverTrue)
					Main.playSound(Sounds.BUTTON_HOVER);

				lasHoverTrue = true;
				mouseHover = true;				
				return true;
	
			} else {
				lasHoverTrue = false;
				mouseHover = false;
			}
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
	
	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}

	public Images getHover() {
		return hover;
	}

	public void setHover(Images hover) {
		this.hover = hover;
	}

	public Images getClicked() {
		return clicked;
	}

	public void setClicked(Images clicked) {
		this.clicked = clicked;
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

	public boolean isLasHoverTrue() {
		return lasHoverTrue;
	}

	public void setLasHoverTrue(boolean lasHoverTrue) {
		this.lasHoverTrue = lasHoverTrue;
	}

	public void setCheckable(boolean checkable) {
		this.checkable = checkable;
	}

	@Override
	public boolean isCheckable() {
		return checkable;
	}

}
