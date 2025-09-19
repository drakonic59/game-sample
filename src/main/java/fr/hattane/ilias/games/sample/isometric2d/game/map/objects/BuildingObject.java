package fr.hattane.ilias.games.sample.isometric2d.game.map.objects;

import fr.hattane.ilias.games.sample.isometric2d.config.BuildingObjects;
import fr.hattane.ilias.games.sample.isometric2d.config.Images;
import fr.hattane.ilias.games.sample.isometric2d.config.MapObjectTypes;

public class BuildingObject extends MapObject {
	
	private BuildingObjects object;
	
	public BuildingObject(Images image, MapObjectTypes type, BuildingObjects object) {
		super(image, type);
		this.object = object;
	}

	public BuildingObjects getObject() {
		return object;
	}

	public void setObject(BuildingObjects object) {
		this.object = object;
	}

}
