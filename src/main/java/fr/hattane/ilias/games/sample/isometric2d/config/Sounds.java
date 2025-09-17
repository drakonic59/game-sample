package fr.hattane.ilias.games.sample.isometric2d.config;

import java.util.ArrayList;
import java.util.List;

public enum Sounds {
	
	BUTTON_HOVER		("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\sounds\\4.mp3"),
	BUTTON_CLICKED		("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\sounds\\1.mp3"),
	
	MUSIC_REPEAT		("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\sounds\\musics\\repeat.mp3"),
	MUSIC_NOSTALGIC		("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\sounds\\musics\\nostalgic.mp3"),
	MUSIC_PAFT			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\sounds\\musics\\paft.mp3"),
	MUSIC_MYSTERIOUS	("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\sounds\\musics\\mysterious.mp3");

	private String path;
	
	private Sounds(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public static List<Sounds> musicValues() {
		
		List<Sounds> musics = new ArrayList<>();
		for (Sounds sound : Sounds.values())
			if (sound.name().contains("MUSIC"))
				musics.add(sound);
		
		return musics;
		
	}
	
}
