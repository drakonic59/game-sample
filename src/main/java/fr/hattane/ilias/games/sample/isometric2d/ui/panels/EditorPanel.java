package fr.hattane.ilias.games.sample.isometric2d.ui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.config.Images;
import fr.hattane.ilias.games.sample.isometric2d.game.GameMap;
import fr.hattane.ilias.games.sample.isometric2d.ui.GameFrame;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.Background;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.ColorBackground;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.overlays.DebugOverlay;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.UiComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.Button;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.SelectorButtonComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.text.Label;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.text.TextField;
import fr.hattane.ilias.games.sample.isometric2d.utils.Function;

public class EditorPanel extends Panel implements Runnable {

	private static final long serialVersionUID = -2821619185094087950L;
	
	private GameFrame frame;
	private MenuPanel pauseMenu;
	
	private SelectorButtonComponent colors;
	private SelectorButtonComponent heights;
	private Label radius;
	
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
		
		int cx = width/10;
		int cy = 0;
		
		int ch = height / 20;
		int cw = ch;
		
		colors = new SelectorButtonComponent(cx + 10, cy +5, cw-10, ch -10, Images.EDITOR_BUTTON_COLORS, Images.EDITOR_BUTTON_COLORS_HOVER, Images.EDITOR_BUTTON_COLORS_CLICKED, null);
		colors.setSelected(true);
		
		heights = new SelectorButtonComponent(cx + cw + 15, cy +5, cw-10, ch -10, Images.EDITOR_BUTTON_HEIGHTS, Images.EDITOR_BUTTON_HEIGHTS_HOVER, Images.EDITOR_BUTTON_HEIGHTS_CLICKED, null);
		heights.setSelected(false);
		
		radius = new Label(cx + cw*5 + 50, cy, cw-10, ch -10, "1");
		radius.setFont(new Font("Consolas", Font.BOLD, ch-10));
		radius.setY(radius.getY() + radius.getFont().getSize());
		
		this.setLayout(null);
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.requestFocus();
		
		initListeners();
		
		this.requestFocusInWindow();
		this.requestFocus();
		
		this.setVisible(true);
		
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
				
				mouseLeftClick = false;
				mouseX = -1;
				mouseY = -1;
				
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {}
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if (!pause) {
					
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
		heights.draw(g);
		radius.draw(g);
		
	}

	private void drawEditorMenu(Graphics g) {
		
		int scaledWidth = (int) (getWidth() / map.getScale());
		int scaledHeight = (int) (getHeight() / map.getScale());
		
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(scaledWidth/10, 0, scaledWidth/5, scaledHeight/20);
		
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
	
}
