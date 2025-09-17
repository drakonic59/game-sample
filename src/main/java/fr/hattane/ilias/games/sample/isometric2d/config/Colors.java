package fr.hattane.ilias.games.sample.isometric2d.config;

import java.awt.Color;

public enum Colors {
	
	BEACH				( new Color( 252, 202, 70 ) ),
	PLAIN_LIGHT			( new Color( 128, 171, 84 ) ),
//	PLAIN_MEDIUM		( new Color( 128, 171, 84 ) ),
	PLAIN_MEDIUM		( new Color( 128, 171, 84 ) ),
	PLAIN_HEIGHT		( new Color( 105, 140, 69 ) ),
	STONE_LIGHT			( new Color( 211, 211, 211 ) ),
	STONE_MEDIUM		( new Color( 196, 196, 196 ) ),
	STONE_HEIGHT		( new Color( 173, 173, 173 ) );
	
	private Color color;
	
	private Colors(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public static boolean isWater(Color color) {
		
		for (Colors c : Colors.values())
			if (c.getColor().equals(color))
				return false;
		
		return true;
	}
	
}
