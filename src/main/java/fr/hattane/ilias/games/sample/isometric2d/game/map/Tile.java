package fr.hattane.ilias.games.sample.isometric2d.game.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.game.map.objects.MapObject;

public class Tile {
	
	public static final int TILE_WIDTH = 64;
	public static final int TILE_HEIGHT = 32;
	
	public static final int TILE_LEVEL_HEIGHT = 8;
	
	private int x;
	private int y;
	private int h;
	
	private Color groundColor;
	
	private MapObject object;
	
	public Tile() {}

	public Tile(int x, int y, Color groundColor) {
		super();
		this.x = x;
		this.y = y;
		this.h = 0;
		this.groundColor = groundColor;
	}

	public Tile(int x, int y, int h, Color groundColor) {
		super();
		this.x = x;
		this.y = y;
		this.h = h;
		this.groundColor = groundColor;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Color getGroundColor() {
		return groundColor;
	}

	public void setGroundColor(Color groundColor) {
		this.groundColor = groundColor;
	}

	public void drawGround(Graphics2D g2d, Point tileToScreenPoint, boolean hover) {
		
		int y = tileToScreenPoint.y - TILE_LEVEL_HEIGHT*h;
		
        Polygon ground = new Polygon();
        ground.addPoint(
        		tileToScreenPoint.x, 
        		y
        );
        ground.addPoint(
        		tileToScreenPoint.x + Tile.TILE_WIDTH/2, 
        		y + Tile.TILE_HEIGHT/2
        );
        ground.addPoint(
        		tileToScreenPoint.x, 
        		y + Tile.TILE_HEIGHT
        );
        ground.addPoint(
        		tileToScreenPoint.x - Tile.TILE_WIDTH/2,
        		y + Tile.TILE_HEIGHT/2
        );
        
        g2d.setColor(getGroundColor());
        g2d.fill(ground);

        if (h > 0) {
        	
        	Polygon left = new Polygon();
        	Polygon right = new Polygon();

        	left.addPoint(
            		tileToScreenPoint.x - Tile.TILE_WIDTH/2,
            		y + Tile.TILE_HEIGHT/2
            );
        	left.addPoint(
	        		tileToScreenPoint.x - Tile.TILE_WIDTH/2,
	        		y + Tile.TILE_HEIGHT/2 + Tile.TILE_LEVEL_HEIGHT*h
	        );
        	left.addPoint(
	        		tileToScreenPoint.x, 
	        		y + Tile.TILE_HEIGHT + Tile.TILE_LEVEL_HEIGHT*h
	        );
        	left.addPoint(
	        		tileToScreenPoint.x, 
	        		y + Tile.TILE_HEIGHT
	        );
        	
	        right.addPoint(
	        		tileToScreenPoint.x, 
	        		y + Tile.TILE_HEIGHT
	        );
	        right.addPoint(
	        		tileToScreenPoint.x, 
	        		y + Tile.TILE_HEIGHT + Tile.TILE_LEVEL_HEIGHT*h
	        );
	        right.addPoint(
	        		tileToScreenPoint.x + Tile.TILE_WIDTH/2, 
	        		y + Tile.TILE_HEIGHT/2 + Tile.TILE_LEVEL_HEIGHT*h
	        );
	        right.addPoint(
	        		tileToScreenPoint.x + Tile.TILE_WIDTH/2, 
	        		y + Tile.TILE_HEIGHT/2
	        );
	        
	        g2d.setColor(getGroundColor().darker());
	        g2d.fill(left);
	        
	        g2d.setColor(getGroundColor().brighter());
	        g2d.fill(right);
	        
        }
        
        if (Main.debug) {
	        g2d.setColor(Color.BLACK);
	        g2d.draw(ground);
        }
        
        if (hover) {
	        g2d.setColor(Color.WHITE);
	        g2d.draw(ground);
        }
		
	}
	
	public void drawObjects(Graphics2D g2d, Point tileToScreenPoint) {
		
		if (object != null)
			object.draw(g2d, tileToScreenPoint, getH());
		
	}

	public MapObject getObject() {
		return object;
	}

	public void setObject(MapObject object) {
		this.object = object;
	}
	
}
