package fr.hattane.ilias.games.sample.isometric2d.ui.components.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.hattane.ilias.games.sample.isometric2d.config.Images;
import fr.hattane.ilias.games.sample.isometric2d.utils.Function;

public class SelectorButtonComponent extends ButtonImageComponent {
	
	private boolean selected = false;
	
	public SelectorButtonComponent() {}
	
	public SelectorButtonComponent(int x, int y, int width, int height, Images image, Images hover, Images clicked, Function postAction) {
		super(x, y, width, height, image, hover, clicked, postAction);
	}
	
	@Override
	public boolean clickReleased() {
		
		if (isVisible()) {
			
			if (super.clickReleased()) {
				
				if (!selected)
					selected = true;
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		if (isVisible()) {
			
			BufferedImage image = getImage().getImage();
			if (isMouseClicked())
				image = getClicked().getImage();
			else if (isMouseHover())
				image = getHover().getImage();
			
			g.drawImage(selected ? getClicked().getImage() : image, getX(), getY(), getWidth(), getHeight(), null);
			
		}
		
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
