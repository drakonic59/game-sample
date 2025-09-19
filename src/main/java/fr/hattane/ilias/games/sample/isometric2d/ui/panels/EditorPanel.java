package fr.hattane.ilias.games.sample.isometric2d.ui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.config.BuildingObjects;
import fr.hattane.ilias.games.sample.isometric2d.config.Colors;
import fr.hattane.ilias.games.sample.isometric2d.config.Images;
import fr.hattane.ilias.games.sample.isometric2d.config.MapObjectTypes;
import fr.hattane.ilias.games.sample.isometric2d.config.NaturalObjects;
import fr.hattane.ilias.games.sample.isometric2d.game.GameMap;
import fr.hattane.ilias.games.sample.isometric2d.ui.GameFrame;
import fr.hattane.ilias.games.sample.isometric2d.ui.ObjectsFrame;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.Background;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.ColorBackground;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.overlays.DebugOverlay;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.UiComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.Button;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.ButtonImageComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.SelectorButtonComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.text.Label;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.text.TextField;
import fr.hattane.ilias.games.sample.isometric2d.utils.Function;

public class EditorPanel extends Panel implements Runnable {

	private static final long serialVersionUID = -2821619185094087950L;
	
	private GameFrame frame;
	private MenuPanel pauseMenu;
	
	private SelectorButtonComponent colors;
	private SelectorButtonComponent getColor;
	private SelectorButtonComponent heights;
	private SelectorButtonComponent objects;
	private Label radius;
	private ButtonImageComponent moreRadius;
	private ButtonImageComponent lessRadius;
	
	private DebugOverlay debug;
	
	private Background background;
	
	private GameMap map;

	private int width;
	private int height;
	
	private boolean pause = false;
	
	private boolean mouseLeftClick = false;
	private int mouseX = -1;
	private int mouseY = -1;
	
	private double lastScale = 1.0;
	
	private MapObjectTypes selectedType = MapObjectTypes.NATURAL;
	private int selectedIndex = 1;
	private Color currentColor = Colors.PLAIN_HEIGHT.getColor();
	private int radiusValue = 1;
	private int heightValue = 1;
	
	public EditorPanel(GameFrame frame, int width, int height) {
		
		this.frame = frame;
		this.width = width;
		this.height = height;
		
		pauseMenu = new MenuPanel(frame, width, height, false, new Function() {
			@Override
			public Object execute(Object object) {
				
				alive = false;
				frame.dispose(); 
				
				Main.playMusic = false;
				Main.audio.shutdown();
				
				return null;
			}
		});
		
		background = new ColorBackground(0, 0, width, height, 255, 255, 255);
		map = new GameMap(width, height);
		debug = new DebugOverlay(width, height);
		
		initEditorMenu();
		
		this.setLayout(null);
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.requestFocus();
		
		initListeners();
		
		this.requestFocusInWindow();
		this.requestFocus();
		
		this.setVisible(true);
		
	}
	
	private void initEditorMenu() {
		
		int cx = width/10;
		int cy = 0;
		
		int ch = height / 20;
		int cw = ch;
		
		colors = new SelectorButtonComponent(cx + 10, cy +5, cw-10, ch -10, Images.EDITOR_BUTTON_COLORS, Images.EDITOR_BUTTON_COLORS_HOVER, Images.EDITOR_BUTTON_COLORS_CLICKED, null);
		colors.setSelected(true);
		
		getColor = new SelectorButtonComponent(cx + cw + 15, cy +5, cw-10, ch -10, Images.EDITOR_BUTTON_GET_COLOR, Images.EDITOR_BUTTON_GET_COLOR_HOVER, Images.EDITOR_BUTTON_GET_COLOR_CLICKED, null);
		getColor.setSelected(false);
		
		heights = new SelectorButtonComponent(cx + cw*2 + 20, cy +5, cw-10, ch -10, Images.EDITOR_BUTTON_HEIGHTS, Images.EDITOR_BUTTON_HEIGHTS_HOVER, Images.EDITOR_BUTTON_HEIGHTS_CLICKED, null);
		heights.setSelected(false);
		
		objects = new SelectorButtonComponent(cx + cw*3 + 25, cy +5, cw-10, ch -10, Images.EDITOR_BUTTON_OBJECTS, Images.EDITOR_BUTTON_OBJECTS_HOVER, Images.EDITOR_BUTTON_OBJECTS_CLICKED, null);
		objects.setSelected(false);
		
		objects.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				
				colors.setSelected(false);
				getColor.setSelected(false);
				heights.setSelected(false);
				radius.setText(radiusValue + "");
				
				new ObjectsFrame(get());
				
				return null;
			}
		});
		heights.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				colors.setSelected(false);
				getColor.setSelected(false);
				objects.setSelected(false);
				radius.setText(heightValue + "");
				return null;
			}
		});
		colors.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				heights.setSelected(false);
				getColor.setSelected(false);
				objects.setSelected(false);
				radius.setText(radiusValue + "");
				return null;
			}
		});
		getColor.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				colors.setSelected(false);
				heights.setSelected(false);
				objects.setSelected(false);
				radius.setText(radiusValue + "");
				return null;
			}
		});
		
		radius = new Label(cx + cw*5 + 50, cy, cw-10, ch -10, "1");
		radius.setFont(new Font("Consolas", Font.BOLD, ch-10));
		radius.setY(radius.getY() + radius.getFont().getSize());
		
		moreRadius = new ButtonImageComponent(radius.getX() - cw -5, cy +5, cw-10, ch -10, Images.EDITOR_BUTTON_MORE, Images.EDITOR_BUTTON_MORE_HOVER, Images.EDITOR_BUTTON_MORE_CLICKED, new Function() {
			@Override
			public Object execute(Object object) {
				if (heights.isSelected()) {
					heightValue = Math.min(heightValue+1, 10);
					radius.setText(heightValue + "");
				} else {
					radiusValue = Math.min(radiusValue+1, 10);
					radius.setText(radiusValue + "");
				}
				return null;
			}
		});
		lessRadius = new ButtonImageComponent(radius.getX() - cw*2 -10, cy +5, cw-10, ch -10, Images.EDITOR_BUTTON_LESS, Images.EDITOR_BUTTON_LESS_HOVER, Images.EDITOR_BUTTON_LESS_CLICKED, new Function() {
			@Override
			public Object execute(Object object) {
				if (heights.isSelected()) {
					heightValue = Math.max(heightValue-1, 1);
					radius.setText(heightValue + "");
				} else {
					radiusValue = Math.max(radiusValue-1, 1);
					radius.setText(radiusValue + "");
				}
				return null;
			}
		});
		
	}
	
	public EditorPanel get() {
		return this;
	}
	
	private void initListeners() {
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				
				if (e.getKeyCode() == 27) {
					
					pause = !pause;
					if (pause) {
						
						pauseMenu.restartOverlay();
						pauseMenu.initUnscale(map.getScale());
						
					} else
						pauseMenu.init(frame, width, height, false);
				
				} else if (e.getKeyCode() == 114) {
					
					debug.updateScale(map.getScale());
					Main.debug = !Main.debug;
					
				}
				
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if (moreRadius.clickReleased()) {}
				else if (lessRadius.clickReleased()) {}
				else if (colors.clickReleased()) {}
				else if (heights.clickReleased()) {}
				else if (getColor.clickReleased()) {}
				else if (objects.clickReleased()) {}
				else if (!mouseLeftClick)
					paintColors();
				
				mouseLeftClick = false;
				mouseX = -1;
				mouseY = -1;
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (moreRadius.clicked((int) (e.getX()/map.getScale()), (int) (e.getY()/map.getScale()))) {}
				else if (lessRadius.clicked((int) (e.getX()/map.getScale()), (int) (e.getY()/map.getScale()))) {}
				else if (colors.clicked((int) (e.getX()/map.getScale()), (int) (e.getY()/map.getScale()))) {}
				else if (heights.clicked((int) (e.getX()/map.getScale()), (int) (e.getY()/map.getScale()))) {}
				else if (getColor.clicked((int) (e.getX()/map.getScale()), (int) (e.getY()/map.getScale()))) {}
				else if (objects.clicked((int) (e.getX()/map.getScale()), (int) (e.getY()/map.getScale()))) {}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
					
				
				
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				moreRadius.hoverReleased();
				lessRadius.hoverReleased();
				
				if (moreRadius.hover((int) (e.getX()/map.getScale()), (int) (e.getY()/map.getScale()))) {}
				else if (lessRadius.hover((int) (e.getX()/map.getScale()), (int) (e.getY()/map.getScale()))) {}
				
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				
			    int mask = e.getModifiersEx();

			    boolean leftDown   = (mask & MouseEvent.BUTTON1_DOWN_MASK) != 0;
//			    boolean rightDown  = (mask & MouseEvent.BUTTON3_DOWN_MASK) != 0;
//			    boolean middleDown = (mask & MouseEvent.BUTTON2_DOWN_MASK) != 0;
				
				if (!pause) {
					
					if (!leftDown) {
						if (mouseLeftClick) {
							
							int xDiff = (int) ( ((double)mouseX - (double)e.getXOnScreen()) /  map.getScale() );
							int yDiff = (int) ( ((double)mouseY - (double)e.getYOnScreen()) /  map.getScale() );
												
							map.setCamX(map.getCamX() - xDiff);
							map.setCamY(map.getCamY() - yDiff);
							
							mouseX = e.getXOnScreen();
							mouseY = e.getYOnScreen();
							
						} else {
							
							mouseLeftClick = true;
							mouseX = e.getXOnScreen();
							mouseY = e.getYOnScreen();
							
						}
						map.clampCameraToMap();
					} else
						paintColors();
					
				}
				
			}
		});
		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				if (!pause) {
					
					if (e.getWheelRotation() < 0)
						map.setScale(getScale(true));
					else
						map.setScale(getScale(false));
					
					if (Main.debug)
						debug.updateScale(map.getScale());
					
					updateScale(map.getScale());
					
				}
				
			}
		});
		
		//	Reprise des listeners du MenuPanel
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				

				if (pause) {
					for (UiComponent component : pauseMenu.getUiComponents())
						if (component instanceof Button && ((Button)component).isCheckable() && !((Button)component).isClicked())
							((Button)component).hoverReleased();
					
					for (UiComponent component : pauseMenu.getUiComponents())
						if (component instanceof Button &&  ((Button)component).isCheckable())
							if (((Button)component).hover((int) (e.getX() / map.getScale()), (int) (e.getY() / map.getScale())))
								break;
				}
				
			}
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				if (pause)
					for (UiComponent component : pauseMenu.getUiComponents())
						if (component instanceof Button && ((Button)component).isCheckable() && ((Button)component).isClicked())
							((Button)component).clickReleased();
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {

				if (pause)
					for (UiComponent component : pauseMenu.getUiComponents())
						if (component instanceof Button && ((Button)component).isCheckable() && ((Button)component).isHover())
							if (((Button)component).clicked((int) (e.getX() / map.getScale()), (int) (e.getY() / map.getScale())))
								break;
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if (pause) {
					if (pauseMenu.isNewGame()) {
						
						for (UiComponent component : pauseMenu.getUiComponents())
							if (component instanceof TextField && ((Button)component).isCheckable() && ((Button)component).isClicked())
								if (Character.isLetter(e.getKeyChar()) || e.getKeyChar() == ' ' || e.getKeyChar() == '\'')
									((TextField)component).entry(e.getKeyChar());
						
					}
				}
				
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {

				if (pause) {
					if (pauseMenu.isNewGame()) {
					
						if (e.getKeyCode() == 8)
							for (UiComponent component : pauseMenu.getUiComponents())
								if (component instanceof TextField && ((Button)component).isCheckable() && ((Button)component).isClicked())
										((TextField)component).removeLast();
						
					}
				}
				
			}
		});
		
	}
	
	protected void paintColors() {
		
		Point selectedTile = map.getSelectedTile();
		if (selectedTile != null) {
			
			if (getColor.isSelected()) {
				
				currentColor = map.getTiles()[selectedTile.y][selectedTile.x].getGroundColor();
				getColor.setSelected(false);
				colors.setSelected(true);
				
			} else if (colors.isSelected()) {
				
				map.getTiles()[selectedTile.y][selectedTile.x].setGroundColor(currentColor);
				if (radiusValue > 1) {

					for (int y = selectedTile.y - (radiusValue-1); y < selectedTile.y + radiusValue; y++)
						for (int x = selectedTile.x - (radiusValue-1); x < selectedTile.x + radiusValue; x++)
							map.getTiles()[Math.max(0, Math.min(y, GameMap.SIZE-1))][Math.max(0, Math.min(x, GameMap.SIZE-1))].setGroundColor(currentColor);
					
				}
				
			} else if (heights.isSelected()) {
				
				map.getTiles()[selectedTile.y][selectedTile.x].setH(heightValue);
				if (radiusValue > 1) {
					
					for (int y = selectedTile.y - (radiusValue-1); y < selectedTile.y + radiusValue; y++)
						for (int x = selectedTile.x - (radiusValue-1); x < selectedTile.x + radiusValue; x++)
							map.getTiles()[Math.max(0, Math.min(y, GameMap.SIZE-1))][Math.max(0, Math.min(x, GameMap.SIZE-1))].setH(heightValue);
					
				}
				
			} else if (objects.isSelected()) {
				
				map.getTiles()[selectedTile.y][selectedTile.x].setObject(selectedType == MapObjectTypes.NATURAL ? NaturalObjects.values()[selectedIndex].newInstance() : BuildingObjects.values()[selectedIndex].newInstance());
				if (radiusValue > 1) {
					
					for (int y = selectedTile.y - (radiusValue-1); y < selectedTile.y + radiusValue; y++)
						for (int x = selectedTile.x - (radiusValue-1); x < selectedTile.x + radiusValue; x++)
							map.getTiles()[Math.max(0, Math.min(y, GameMap.SIZE-1))][Math.max(0, Math.min(x, GameMap.SIZE-1))].setObject(selectedType == MapObjectTypes.NATURAL ? NaturalObjects.values()[selectedIndex].newInstance() : BuildingObjects.values()[selectedIndex].newInstance());
					
				}
				
			}
			
		}
		
	}

	protected void updateScale(double scale) {
		
		int cx = width/10;
		int cy = 0;
		
		int ch = height / 20;
		int cw = ch;
		
		colors.setX((int) ((cx + 10) / scale));
		colors.setY((int) ((cy +5) / scale));
		colors.setWidth((int) ((cw-10) / scale));
		colors.setHeight((int) ((ch -10) / scale));
		
		getColor.setX((int) ((cx + cw + 15) / scale));
		getColor.setY((int) ((cy +5) / scale));
		getColor.setWidth((int) ((cw-10) / scale));
		getColor.setHeight((int) ((ch -10) / scale));
		
		heights.setX((int) ((cx + cw*2 + 20) / scale));
		heights.setY((int) ((cy +5) / scale));
		heights.setWidth((int) ((cw-10) / scale));
		heights.setHeight((int) ((ch -10) / scale));
		
		objects.setX((int) ((cx + cw*3 + 25) / scale));
		objects.setY((int) ((cy +5) / scale));
		objects.setWidth((int) ((cw-10) / scale));
		objects.setHeight((int) ((ch -10) / scale));
		
		radius.setX((int) ((cx + cw*5 + 50) / scale));
		radius.setY((int) ((cy) / scale));
		radius.setWidth((int) ((cw-10) / scale));
		radius.setHeight((int) ((ch -10) / scale));
		radius.setFont(new Font("Consolas", Font.BOLD, (int) ((int) ((ch -10) / scale)-(10/scale))));
		radius.setY(radius.getY() + radius.getFont().getSize());
		
		moreRadius.setX((int) (radius.getX() - (cw -5)/scale));
		moreRadius.setY((int) ((cy +5) / scale));
		moreRadius.setWidth((int) ((cw-10) / scale));
		moreRadius.setHeight((int) ((ch -10) / scale));
		
		lessRadius.setX((int) (radius.getX() - (cw*2 -10)/scale));
		lessRadius.setY((int) ((cy +5) / scale));
		lessRadius.setWidth((int) ((cw-10) / scale));
		lessRadius.setHeight((int) ((ch -10) / scale));
		
	}

	public double getScale(boolean more) {
		
		int index = 0;
		for (int i = 0; i < GameMap.SCALES.length; i++) {
			if (GameMap.SCALES[i] == map.getScale()) {
				index = i;
				break;
			}
		}
		
		if (more)
			return index == GameMap.SCALES.length-1 ? GameMap.SCALES[index] : GameMap.SCALES[index+1];
		else
			return index == 0 ? GameMap.SCALES[0] : GameMap.SCALES[index-1];
		
	}

	public void clear() {
		pause = false;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		background.draw(g);
		
		map.draw(g, (!pause ? getMousePosition() : null));
		
		if (pause)
			pauseMenu.paintUnscaledComponent(g, map.getScale());
		
		if (Main.debug)
			debug.draw(g);
		
		drawEditorMenu(g);
		
		colors.draw(g);
		getColor.draw(g);
		heights.draw(g);
		objects.draw(g);
		moreRadius.draw(g);
		lessRadius.draw(g);
		radius.draw(g);
		
	}

	private void drawEditorMenu(Graphics g) {
		
		int scaledWidth = (int) (getWidth() / map.getScale());
		int scaledHeight = (int) (getHeight() / map.getScale());
		
		int x = scaledWidth/10;
		int colorX = x - scaledHeight/20 - 10;
		
		int width = scaledWidth/5;
		int height = scaledHeight/20;
		
		g.setColor(currentColor);
		g.fillRect(colorX, 0, height, height);
		
		g.setColor(Color.black);
		g.drawRect(colorX, 0, height, height);
		
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(x, 0, width, height);
		
	}
	

	@Override
    public void start() {
    	
        if (alive) 
        	return;
        alive = true;
        
        thread = new Thread(this, "GameLoop");
        thread.start();
        
    }

	@Override
    public void end() {
		alive = false;
	}

	@Override
	public void run() {
		
		long lastTime = System.currentTimeMillis();

        while (alive) {
            
            try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
        	
            long now = System.currentTimeMillis();
            update(now - lastTime);
            
            lastTime = now;
            
            frame.repaint();
            
        }
		
	}

	private void update(long time) {
		
		if (pause)
			pauseMenu.update(time);
		else {
			
			map.update();
			
			if (Main.debug) {
				
				Point p = getMousePosition();
				debug.updateLinesContent(
						1.0, 
						map.getScale(),
						GameMap.SIZE, 
						GameMap.SIZE, 
						0, 
						(p != null ? p.x : -1), 
						(p != null ? p.y : -1), 
						map.getSelectedTile(), 
						map.getTiles()[map.getSelectedTile().y][map.getSelectedTile().x].getGroundColor()
				);
				
			}
			
		}
	}

	public GameFrame getFrame() {
		return frame;
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}

	public Background getBackgroundObject() {
		return background;
	}

	public void setBackgroundObject(Background background) {
		this.background = background;
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

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public MenuPanel getPauseMenu() {
		return pauseMenu;
	}

	public void setPauseMenu(MenuPanel pauseMenu) {
		this.pauseMenu = pauseMenu;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	public boolean isMouseLeftClick() {
		return mouseLeftClick;
	}

	public void setMouseLeftClick(boolean mouseLeftClick) {
		this.mouseLeftClick = mouseLeftClick;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public double getLastScale() {
		return lastScale;
	}

	public void setLastScale(double lastScale) {
		this.lastScale = lastScale;
	}

	public DebugOverlay getDebug() {
		return debug;
	}

	public void setDebug(DebugOverlay debug) {
		this.debug = debug;
	}

	public SelectorButtonComponent getColors() {
		return colors;
	}

	public void setColors(SelectorButtonComponent colors) {
		this.colors = colors;
	}

	public SelectorButtonComponent getHeights() {
		return heights;
	}

	public void setHeights(SelectorButtonComponent heights) {
		this.heights = heights;
	}

	public Label getRadius() {
		return radius;
	}

	public void setRadius(Label radius) {
		this.radius = radius;
	}

	public int getRadiusValue() {
		return radiusValue;
	}

	public void setRadiusValue(int radiusValue) {
		this.radiusValue = radiusValue;
	}

	public MapObjectTypes getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(MapObjectTypes selectedType) {
		this.selectedType = selectedType;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	
}
