package fr.hattane.ilias.games.sample.isometric2d.game.map.objects.natural;

import fr.hattane.ilias.games.sample.isometric2d.config.NaturalObjects;
import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.MapObject;
import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.MapObjectBuilder;
import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.NaturalObject;

public class TreeObject extends NaturalObject {

	public TreeObject() {
		super(NaturalObjects.TREE);
	}
	
	public static class Builder extends MapObjectBuilder {

		@Override
		public MapObject build() {
			return new TreeObject();
		}
		
	}

}
