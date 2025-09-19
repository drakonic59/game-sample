package fr.hattane.ilias.games.sample.isometric2d.config;

import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.MapObject;
import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.MapObjectBuilder;
import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.natural.TreeObject;

public enum NaturalObjects {
	
	NONE			(null, null),
	TREE			(Images.GAME_NATURE_TREE_1, new TreeObject.Builder());

	private Images image;
	private MapObjectBuilder builder;
	
	private NaturalObjects(Images image, MapObjectBuilder builder) {
		this.image = image;
		this.builder = builder;
	}

	public Images getImage() {
		return image;
	}
	
	public MapObject newInstance() {
		
		if (builder != null)
			return builder.build();
		
		return null;
	}

	public int getValueIndex() {
		
		for (int i = 0; i < NaturalObjects.values().length; i++)
			if (NaturalObjects.values()[i] == this)
				return i;
		
		return 0;
	}
	
}
