package fr.hattane.ilias.games.sample.isometric2d.game.map.objects;

import fr.hattane.ilias.games.sample.isometric2d.config.MapObjectTypes;
import fr.hattane.ilias.games.sample.isometric2d.config.NaturalObjects;

public class NaturalObject extends MapObject {
	
	private NaturalObjects object;
	
	public NaturalObject(NaturalObjects object) {
		super(object.getImage(), MapObjectTypes.NATURAL);
		this.object = object;
	}

	public NaturalObjects getObject() {
		return object;
	}

	public void setObject(NaturalObjects object) {
		this.object = object;
	}

}
