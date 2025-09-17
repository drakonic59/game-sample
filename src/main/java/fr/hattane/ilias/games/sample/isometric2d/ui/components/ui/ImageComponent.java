package fr.hattane.ilias.games.sample.isometric2d.ui.components.ui;

import java.awt.Graphics;

import fr.hattane.ilias.games.sample.isometric2d.config.Images;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.UiComponent;

public class ImageComponent extends UiComponent {

	private Images image;
	
	public ImageComponent() {}
	
	public ImageComponent(int x, int y, int width, int height, Images image) {
		super(x, y, width, height);
		this.image = image;
	}
	
	@Override
	public void draw(Graphics g) {
		
		if (isVisible()) {
			
			g.drawImage(image.getImage(), getX(), getY(), getWidth(), getHeight(), null);
			
		}
		
	}

	@Override
	public void update(long msTime) {
		// TODO Auto-generated method stub
		
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}

}
