package fr.hattane.ilias.games.sample.isometric2d.game.sounds;

import javafx.scene.media.MediaPlayer;

public class MediaPlayerAdapter {
	
	private MediaPlayer player;
	private boolean finished = false;
	
	public MediaPlayerAdapter(MediaPlayer player) {
		this.player = player;
	}
	
	public MediaPlayerAdapter(MediaPlayer player, boolean finished) {
		super();
		this.player = player;
		this.finished = finished;
	}

	public MediaPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(MediaPlayer player) {
		this.player = player;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
}
