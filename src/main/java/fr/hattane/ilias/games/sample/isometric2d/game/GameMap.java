package fr.hattane.ilias.games.sample.isometric2d.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Random;

import fr.hattane.ilias.games.sample.isometric2d.config.Colors;
import fr.hattane.ilias.games.sample.isometric2d.game.map.Tile;
import fr.hattane.ilias.games.sample.isometric2d.utils.IsoMath;
import fr.hattane.ilias.games.sample.isometric2d.utils.PerlinUtils;
import fr.hattane.ilias.games.sample.isometric2d.utils.PerlinUtils.Result;

public class GameMap {
	
	public static final double[] SCALES = new double[] {0.5, 0.75, 1.0, 1.5, 2.0, 3.0};
	
	public static final int SIZE = 150;
	
	private int width;
	private int height;
	
    private int originX;
    private int originY;
    private int camX = 0;
    private int camY = 0;
    
    private double scale = 1.0;
	
	private Tile[][] tiles;
	
	public GameMap(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		originX = width/2;
		originY = 0;
		
		setTiles(new Tile[SIZE][SIZE]);
		
		initPerlinMap();
		
		centerCameraOn(SIZE/2, SIZE/2);
		
	}
	
	public void initPerlinMap() {
		
		initRandomMap();
		
		Result result = PerlinUtils.generate(SIZE, SIZE, new PerlinUtils.Params());
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				
				tiles[y][x].setGroundColor(result.colors[y][x]);
				tiles[y][x].setH(result.heights[y][x]);
				
			}
		}
		
		while (!decreaseHeights()) {}
		
	}
	
	private boolean decreaseHeights() {

		boolean hasHeight0 = false;
		boolean allWater0 = true;
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				
				tiles[y][x].setH(Math.max(tiles[y][x].getH()-1, 0));
				if (tiles[y][x].getH() == 0)
					hasHeight0 = true;
				
				if (Colors.isWater(tiles[y][x].getGroundColor()) && tiles[y][x].getH() != 0)
					allWater0 = false;
				
			}
		}
		
		return hasHeight0 && allWater0;
	}
	
	public void initRandomMap() {
		
		Random r = new Random();
		for (int y = 0; y < SIZE; y++)
			for (int x = 0; x < SIZE; x++)
				tiles[y][x] = new Tile(x, y, r.nextInt(2), new Color(118, 215, r.nextInt(250)+1));
		
	}
	
	public void centerCameraOn(int tx, int ty) {
        Point p = IsoMath.tileToScreen(tx, ty, originX, originY, 0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, tiles[ty][tx].getH());
        camX = getWidth()/2 - p.x;
        camY = getHeight()/2 - p.y - 40;
    }
	
	public void draw(Graphics g, Point mouse) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		transform(g2d);

		int scaledWidth = (int) ((double)width / scale) * (scale >= 2 ? 10 : 2);
//		int scaledHeight = (int) (double)(height / scale) * (scale >= 2 ? 10 : 2);
//
//		int scaledTileWidth = (int) ((double)Tile.TILE_WIDTH * scale);
//		int scaledTileHeight = (int) ((double)Tile.TILE_HEIGHT * scale);

		int scaledOriginX = scaledWidth/2;
		
//        // Culling: déterminer un rectangle de tuiles visibles
//        int[] t00 = IsoMath.screenToTile(0, 0, scaledOriginX, originY, camX, camY, scaledTileWidth, scaledTileHeight);
//        int[] t10 = IsoMath.screenToTile(scaledWidth, 0, scaledOriginX, originY, camX, camY, scaledTileWidth, scaledTileHeight);
//        int[] t01 = IsoMath.screenToTile(0, scaledHeight, scaledOriginX, originY, camX, camY, scaledTileWidth, scaledTileHeight);
//        int[] t11 = IsoMath.screenToTile(scaledWidth, scaledHeight, scaledOriginX, originY, camX, camY, scaledTileWidth, scaledTileHeight);
//        int txMin = Math.max(0, Math.min(Math.min(t00[0], t10[0]), Math.min(t01[0], t11[0])) - 2);
//        int tyMin = Math.max(0, Math.min(Math.min(t00[1], t10[1]), Math.min(t01[1], t11[1])) - 2);
//        int txMax = Math.min(SIZE-1, Math.max(Math.max(t00[0], t10[0]), Math.max(t01[0], t11[0])) + 2);
//        int tyMax = Math.min(SIZE-1, Math.max(Math.max(t00[1], t10[1]), Math.max(t01[1], t11[1])) + 2);
        
        Point tile = null;
        if (mouse != null) {
        	
        	double inv = 1.0 / scale;
    	    double worldX = (mouse.x) * inv - camX;
    	    double worldY = (mouse.y) * inv - camY;

    	    int[] t = IsoMath.screenToTile(
    	        (int)Math.floor(worldX),
    	        (int)Math.floor(worldY),
    	        scaledOriginX, originY,
    	        0, 0,
    	        Tile.TILE_WIDTH, Tile.TILE_HEIGHT,
    	        0
    	    );
    	    
    	    if (t[1] >= 0 && t[1] < SIZE && t[0] >= 0 && t[0] < SIZE)
	    	    t = IsoMath.screenToTile(
	    	        (int)Math.floor(worldX),
	    	        (int)Math.floor(worldY),
	    	        scaledOriginX, originY,
	    	        0, 0,
	    	        Tile.TILE_WIDTH, Tile.TILE_HEIGHT,
	    	        tiles[t[1]][t[0]].getH()
	    	    );
        	
            if (t[0] >= 0 && t[1] >= 0 && t[0] < SIZE && t[1] < SIZE)
            	tile = new Point(t[0], t[1]);
            
        }

        drawTiles(g2d, 0, SIZE-1, 0, SIZE-1, tile, scaledOriginX);
//        drawTiles(g2d, txMin, txMax, tyMin, tyMax, tile, scaledOriginX);
		
	}
	
	private void transform(Graphics2D g2d) {
		
		AffineTransform transform = new AffineTransform();
		transform.scale(scale, scale);
		g2d.transform(transform);
		
	}

	public void drawTiles(Graphics2D g2d, int txMin, int txMax, int tyMin, int tyMax, Point mouse, int scaledOriginX) {
		
		for (int sum = txMin + tyMin; sum <= txMax + tyMax; sum++) {
        	
            int xStart = Math.max(txMin, sum - tyMax);
            int xEnd   = Math.min(txMax, sum - tyMin);
            for (int tx = xStart; tx <= xEnd; tx++) {
            	
            	int ty = sum - tx;
            	tiles[ty][tx].drawGround(
            			g2d, 
            			IsoMath.tileToScreen(tx, ty, scaledOriginX, originY, camX, camY, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 0),
            			(mouse != null && mouse.x == tx && mouse.y == ty)
            	);
            	
            }
            
        }
		
	}
	
	public void update() {
		
	}
	
    public void clampCameraToMap() {
    	
    	double scaledWidth = (double) width / scale;
    	double scaledHeight = (double) height / scale;
    	
    	int scaledOriginX = (int) (scaledWidth/2);
    	
    	Point p00 = IsoMath.tileToScreen(0, 0, scaledOriginX, originY, 0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, tiles[0][0].getH());
    	Point p01 = IsoMath.tileToScreen(0, SIZE, scaledOriginX, originY, 0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, tiles[SIZE-1][0].getH());
    	Point p11 = IsoMath.tileToScreen(SIZE, SIZE, scaledOriginX, originY, 0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, tiles[SIZE-1][SIZE-1].getH());
    	Point p10 = IsoMath.tileToScreen(SIZE, 0, scaledOriginX, originY, 0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, tiles[0][SIZE-1].getH());

//    	System.out.println(p00.x + " - " + p00.y);
//    	System.out.println(p01.x + " - " + p01.y);
//    	System.out.println(p11.x + " - " + p11.y);
//    	System.out.println(p10.x + " - " + p10.y);
    	
    	int minY = 0 - (int) (p11.y - scaledHeight);
    	int maxY = p00.y;
    	
    	int minX = (int) (p10.x - (scaledWidth*1.5));
    	int maxX = p01.x - (int) (scaledWidth/2);
//    	
//    	int minX = 
//    	
//        poly.addPoint(
//        		tileToScreenPoint.x, 
//        		tileToScreenPoint.y
//        );
//        poly.addPoint(
//        		tileToScreenPoint.x + Tile.TILE_WIDTH/2, 
//        		tileToScreenPoint.y + Tile.TILE_HEIGHT/2
//        );
//        poly.addPoint(
//        		tileToScreenPoint.x, 
//        		tileToScreenPoint.y + Tile.TILE_HEIGHT
//        );
//        poly.addPoint(
//        		tileToScreenPoint.x - Tile.TILE_WIDTH/2,
//        		tileToScreenPoint.y + Tile.TILE_HEIGHT/2
//        );
//
//    	double scaledTileWidth = Tile.TILE_WIDTH;//(double) Tile.TILE_WIDTH * scale;
//    	double scaledTileHeight = Tile.TILE_HEIGHT;//(double) Tile.TILE_HEIGHT * scale;
//    	
//    	double mapWidth = scaledTileWidth * (double) SIZE;
//    	double mapHeight = scaledTileHeight * (double) SIZE;
//    	
//    	int minX = (int) (((scaledWidth) /2.0) - (mapWidth/2.0));
//    	int maxX = (int) (minX + mapWidth - scaledWidth);
//    	
//    	int minY = (int) (0.0 - mapHeight - scaledHeight);
    	
    	this.camX = Math.max(Math.min(this.camX, minX), maxX);
    	this.camY = Math.max(Math.min(this.camY, maxY), minY);
        
    }

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getOriginX() {
		return originX;
	}

	public void setOriginX(int originX) {
		this.originX = originX;
	}

	public int getOriginY() {
		return originY;
	}

	public void setOriginY(int originY) {
		this.originY = originY;
	}

	public int getCamX() {
		return camX;
	}

	public void setCamX(int camX) {
		this.camX = camX;
	}

	public int getCamY() {
		return camY;
	}

	public void setCamY(int camY) {
		this.camY = camY;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
	
}
