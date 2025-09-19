package fr.hattane.ilias.games.sample.isometric2d.game.map.objects;

import java.awt.Graphics2D;
import java.awt.Point;

import fr.hattane.ilias.games.sample.isometric2d.config.Images;
import fr.hattane.ilias.games.sample.isometric2d.config.MapObjectTypes;
import fr.hattane.ilias.games.sample.isometric2d.game.map.Tile;

public abstract class MapObject {
	
	private MapObjectTypes type;
	
	private Images image;
	
	public MapObject(Images image, MapObjectTypes type) {
		this.image = image;
		this.type = type;
	}
	
	public void draw(Graphics2D g2d, Point tileToScreenPoint, int height) {
		
		int y = tileToScreenPoint.y - Tile.TILE_LEVEL_HEIGHT*height;
		int x = tileToScreenPoint.x - Tile.TILE_WIDTH/2;
		
		g2d.drawImage(Images.GAME_NATURE_TREE_1.getImage(), x, y - image.getHeight() + Tile.TILE_HEIGHT, Tile.TILE_WIDTH, image.getHeight(), null);
		
		
	}

	public MapObjectTypes getType() {
		return type;
	}

	public void setType(MapObjectTypes type) {
		this.type = type;
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}
	
}
