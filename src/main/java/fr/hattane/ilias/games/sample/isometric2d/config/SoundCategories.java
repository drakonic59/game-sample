package fr.hattane.ilias.games.sample.isometric2d.config;

import java.util.List;

public enum SoundCategories {
	
	MENU			( Settings.SOUND_MENU, List.of(Sounds.BUTTON_CLICKED, Sounds.BUTTON_HOVER) ),
	MUSIC			( Settings.SOUND_MUSIC, List.of(Sounds.MUSIC_MYSTERIOUS, Sounds.MUSIC_NOSTALGIC, Sounds.MUSIC_PAFT, Sounds.MUSIC_REPEAT) ),
	GAME			( Settings.SOUND_GAME, List.of() ),
	ENVIRONMENT		( Settings.SOUND_ENVIRONMENT, List.of() ),
	VOICES			( Settings.SOUND_VOICES, List.of() );

	private Settings setting;
	private List<Sounds> sounds;
	
	private SoundCategories(Settings setting, List<Sounds> sounds) {
		this.setting = setting;
		this.sounds = sounds;
	}

	public List<Sounds> getSounds() {
		return sounds;
	}

	public Settings getSetting() {
		return setting;
	}
	
	public static SoundCategories soundCategory(Sounds sound) {
		
		for (SoundCategories category : SoundCategories.values())
			if (category.getSounds().contains(sound))
				return category;
		
		return null;
		
	}
	
}
