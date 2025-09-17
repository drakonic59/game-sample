package fr.hattane.ilias.games.sample.isometric2d.config;

import java.awt.Dimension;

public enum Settings {
	
	SOUND_MASTER			( Double.valueOf(1.0) ),
	
	SOUND_MUSIC				( Double.valueOf(1.0) ),
	SOUND_MENU				( Double.valueOf(1.0) ),
	SOUND_ENVIRONMENT		( Double.valueOf(1.0) ),
	SOUND_GAME				( Double.valueOf(1.0) ),
	
	SOUND_VOICES			( Double.valueOf(1.0) ),
	
	DIMENSIONS				( new Dimension(1920, 1080) );
	
	private Object defaultValue;
	
	private Settings(Object defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}
	
}
