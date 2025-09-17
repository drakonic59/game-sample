package fr.hattane.ilias.games.sample.isometric2d.ui.components.ui;

public interface Button {
	
	public boolean hover(int x, int y);
	public boolean clicked(int x, int y);
	
	public boolean clickReleased();
	public boolean hoverReleased();
	
	public boolean isClicked();
	public boolean isHover();
	
	public boolean isCheckable();
	
}
