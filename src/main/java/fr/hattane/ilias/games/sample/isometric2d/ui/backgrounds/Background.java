package fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds;

import fr.hattane.ilias.games.sample.isometric2d.ui.components.Component;

public abstract class Background extends Component {
	
	public Background() {
		super();
	}
	
	public Background(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	@Override
	public void update(long msTime) {
		
	}
	
}
