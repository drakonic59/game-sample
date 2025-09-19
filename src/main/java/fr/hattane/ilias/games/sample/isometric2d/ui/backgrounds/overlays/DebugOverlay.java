package fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.overlays;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.UiComponent;

public class DebugOverlay extends UiComponent {
	
	public static final int TEXT_SIZE = 25;

	public static final int ORIGIN_START_X = 20;
	public static final int ORIGIN_START_Y = 25;

	public int startX = ORIGIN_START_X;
	public int startY = ORIGIN_START_Y;
	
	public int originWidth;
	public int originHeight;
	
	private Font font = new Font("Consolas", Font.BOLD, TEXT_SIZE);
	private Color textColor = Color.WHITE;
	
	private boolean background = false;
	private Color backgroundColor = new Color(255, 255, 255, 80);
	
	private Map<Point, String> lines = new HashMap<>();
	private List<String> texts = new ArrayList<>();
	
	private double scale = 1.0;
	
	public DebugOverlay(int width, int height) {
		super(0, 0, width, height);
		
		originWidth = width;
		originHeight = height;
		
		addLine("Sauvegarde : <Cofee Lovers> - <Difficile> - <25/12/2025 12:45:32>");
		addLine("Temps de jeu : <4h 38mn>");
		addLine("Vitesse : <x1.5>      | Zoom : <x0.5>");
		addLine("Carte : <150 x 150>   | Entités : 24");
		addLine("Pointeur : <x=800 y=1200>");
		addLine("Case : <x=50 y=74>    | Couleur : <255, 120, 43, 80>");
		
		updateScale(1.0);

	}
	
	public void updateLinesContent(double speed, double scale, int mapWidth, int mapHeight, int entityCount, int mouseX, int mouseY, Point tile, Color color) {
		
		texts.clear();
		
		addLine("Sauvegarde : " + Main.current.getName() + " - " 
								+ Main.current.getDifficulty().getLabel() + " - " 
								+ Main.FORMATTER.format(Main.current.getSaveDate()));
		addLine("Temps de jeu (sauvegardé) : " + Main.current.getGameTime());
		addLine("Vitesse : " + speed + "      | Zoom : " + scale);
		addLine("Carte : " + mapWidth + "x" + mapHeight + "   | Entités : " + entityCount);
		addLine("Pointeur : x=" + mouseX + " y=" + mouseY);
		addLine("Case : " + (tile != null ? "x=" + tile.x + " y=" + tile.y : "Hors de la Carte") + "    | Couleur : " + (color != null ? color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ", " + color.getAlpha() : "Vide"));
		
		updateScale(scale);
		
	}
	
	public void updateScale(double scale) {
		
		font = new Font("Consolas", Font.BOLD, (int) ((double) TEXT_SIZE / scale));
		
		setWidth((int) ((double) originWidth / scale));
		setHeight((int) ((double) originHeight / scale));
		
		setStartX((int) ((double) ORIGIN_START_X / scale));
		setStartY((int) ((double) ORIGIN_START_Y / scale));
		
		updateLines();
		
	}
	
	public void addLine(String text) {
		
		texts.add(text);
		lines.put(
				new Point(getX() + startX, getY() + startY + (texts.size() * font.getSize())), 
				text
		);
		
	}
	
	public void updateLines() {
		
		lines = new HashMap<>();
		for (int i = 0; i < texts.size(); i++)
			lines.put(
					new Point(getX() + startX, getY() + startY + (i * font.getSize())), 
					texts.get(i)
			);
		
	}

	@Override
	public void draw(Graphics g) {
		
		if (isVisible()) {
			
			if (background) {
				
				g.setColor(backgroundColor);
				g.fillRect(0, 0, getWidth(), getHeight());
				
			}
			
			for (Point p : lines.keySet()) {
				
				String text = lines.get(p);
				
				if (text != null) {
					g.setColor(textColor);
					g.setFont(font);
					g.drawString(text, p.x, p.y);
				}
				
			}
			
		}
		
	}

	@Override
	public void update(long msTime) {
		// TODO Auto-generated method stub
		
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public boolean isBackground() {
		return background;
	}

	public void setBackground(boolean background) {
		this.background = background;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Map<Point, String> getLines() {
		return lines;
	}

	public void setLines(Map<Point, String> lines) {
		this.lines = lines;
	}

	public List<String> getTexts() {
		return texts;
	}

	public void setTexts(List<String> texts) {
		this.texts = texts;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getOriginWidth() {
		return originWidth;
	}

	public void setOriginWidth(int originWidth) {
		this.originWidth = originWidth;
	}

	public int getOriginHeight() {
		return originHeight;
	}

	public void setOriginHeight(int originHeight) {
		this.originHeight = originHeight;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

}
