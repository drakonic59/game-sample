package fr.hattane.ilias.games.sample.isometric2d.config;

import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.MapObject;
import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.MapObjectBuilder;

public enum BuildingObjects {
	
	NONE			(null, null);

	private Images image;
	private MapObjectBuilder builder;
	
	private BuildingObjects(Images image, MapObjectBuilder builder) {
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
		
		for (int i = 0; i < BuildingObjects.values().length; i++)
			if (BuildingObjects.values()[i] == this)
				return i;
		
		return 0;
	}
	
}
