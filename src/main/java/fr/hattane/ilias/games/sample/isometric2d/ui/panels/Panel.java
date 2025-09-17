package fr.hattane.ilias.games.sample.isometric2d.ui.panels;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Panel extends JPanel {

	private static final long serialVersionUID = -2233782318730364842L;
	
	protected Thread thread;
	protected boolean alive = false;
	
	public abstract void start();
	public abstract void end();
	
	@Override
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}
