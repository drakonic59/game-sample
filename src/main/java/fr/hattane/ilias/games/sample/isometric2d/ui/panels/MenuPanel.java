package fr.hattane.ilias.games.sample.isometric2d.ui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.config.Images;
import fr.hattane.ilias.games.sample.isometric2d.saves.GameSave;
import fr.hattane.ilias.games.sample.isometric2d.saves.config.Characters;
import fr.hattane.ilias.games.sample.isometric2d.saves.config.Difficulties;
import fr.hattane.ilias.games.sample.isometric2d.ui.GameFrame;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.Background;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.ColorBackground;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.ImageBackground;
import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.overlays.ColorOverlay;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.UiComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.Button;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.ButtonColorComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.ButtonImageComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.ImageComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.SelectorButtonComponent;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.text.Label;
import fr.hattane.ilias.games.sample.isometric2d.ui.components.ui.text.TextField;
import fr.hattane.ilias.games.sample.isometric2d.utils.Function;

public class MenuPanel extends Panel implements Runnable {
	
	private static final long serialVersionUID = 4421378238787095029L;
	
	public static final String NEW_GAME_MENU_FLAG = "NEW_GAME_MENU_FLAG";
	public static final String LOAD_GAME_MENU_FLAG = "LOAD_GAME_MENU_FLAG";
	
	private GameFrame frame;
	
	private Background background;
	private ColorOverlay overlay;
	
	private List<UiComponent> components;

	private int width;
	private int height;
	
	private boolean menu;
		
    private int originButtonHeight;
    private int buttonHeight;
    private int originButtonWidth;
    private int buttonWidth;
    
    private boolean newGame = false;
    private boolean loadGame = false;
    
    private boolean hasToReload = false;
    private boolean reloading = false;
    
    private Difficulties selectedDifficulty = Difficulties.EASY;
    private Characters selectedCharacter = Characters.GIRL;
    
    private Function close = null;
    
    private double scale = 1.0;
    
    public MenuPanel(GameFrame frame, int width, int height, boolean menu, Function close) {
    	
    	this.close = close;
		init(frame, width, height, menu);
    	
    }
	
	public MenuPanel(GameFrame frame, int width, int height, boolean menu) {
		init(frame, width, height, menu);
	}
	
	public void init(GameFrame frame, int width, int height, boolean menu) {
		
		this.components = new ArrayList<>();
		
		this.frame = frame;
		this.width = width;
		this.height = height;
		this.menu = menu;
		
		initMenu();
		
		initNewGameMenu();
		setNewGameMenuVisibility(false);
		setLoadGameMenuVisibility(false);
		
		if (menu)
			background = new ImageBackground(0, 0, width, height, "D:\\Bibliothèque\\Projets\\isometric sample\\menu\\background.png");
		else
			background = new ColorBackground(0, 0, width, height, 0, 0, 0, 0.4f);
		
		restartOverlay();
				
		this.setLayout(null);
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.requestFocus();
		
		if (menu)
			initListeners();
		
		this.setVisible(true);
		
	}

	private void initMenu() {
		
		int imgWidth = width/5;
		components.add(new ImageComponent(width/6, 75, imgWidth, (int)(imgWidth*0.810546875), Images.MENU_TITLE));
		
		int btnX = components.get(0).getX() - 50;
		int startY = components.get(0).getY() + components.get(0).getHeight() + 75;
		
		buttonWidth = imgWidth + 100;
		originButtonWidth = buttonWidth;
		
		buttonHeight = (int) (buttonWidth * 0.15);
		originButtonHeight = buttonHeight;
		
		if (menu)
			components.add(new ButtonImageComponent(btnX, startY, buttonWidth, buttonHeight, Images.MENU_BUTTON_NEW_GAME, Images.MENU_BUTTON_NEW_GAME_HOVER, Images.MENU_BUTTON_NEW_GAME_CLICKED, new Function() {
				@Override
				public Object execute(Object object) {
					loadGame = false;
					setLoadGameMenuVisibility(false);
					newGame = !newGame;
					setNewGameMenuVisibility(newGame);
					return null;
				}
			}));
		else
			components.add(new ButtonImageComponent(btnX, startY, buttonWidth, buttonHeight, Images.MENU_BUTTON_SAVE, Images.MENU_BUTTON_SAVE_HOVER, Images.MENU_BUTTON_SAVE_CLICKED, new Function() {
				@Override
				public Object execute(Object object) {
					loadGame = false;
					setLoadGameMenuVisibility(false);
					newGame = !newGame;
					setNewGameMenuVisibility(newGame);
					return null;
				}
			}));
		
		components.add(new ButtonImageComponent(btnX, startY + buttonHeight + 20, buttonWidth, buttonHeight, Images.MENU_BUTTON_LOAD_GAME, Images.MENU_BUTTON_LOAD_GAME_HOVER, Images.MENU_BUTTON_LOAD_GAME_CLICKED, new Function() {
			@Override
			public Object execute(Object object) {
				newGame = false;
				setNewGameMenuVisibility(false);
				loadGame = !loadGame;
				setLoadGameMenuVisibility(loadGame);
				return null;
			}
		}));
		components.add(new ButtonImageComponent(btnX, startY + buttonHeight*2 + 40, buttonWidth, buttonHeight, Images.MENU_BUTTON_SETTINGS, Images.MENU_BUTTON_SETTINGS_HOVER, Images.MENU_BUTTON_SETTINGS_CLICKED, new Function() {
			@Override
			public Object execute(Object object) {
				newGame = false;
				setNewGameMenuVisibility(false);
				loadGame = !loadGame;
				setLoadGameMenuVisibility(loadGame);
				return null;
			}
		}));
		
		if (menu) {
		
			components.add(new ButtonImageComponent(btnX, startY + buttonHeight*3 + 60, buttonWidth, buttonHeight, Images.MENU_BUTTON_EDITOR, Images.MENU_BUTTON_EDITOR_HOVER, Images.MENU_BUTTON_EDITOR_CLICKED, new Function() {
				@Override
				public Object execute(Object object) {
					
					alive = false;
					frame.switchToEditor();
					
					return null;
				}
			}));
			components.add(new ButtonImageComponent(btnX, startY + buttonHeight*4 + 80, buttonWidth, buttonHeight, Images.MENU_BUTTON_QUIT, Images.MENU_BUTTON_QUIT_HOVER, Images.MENU_BUTTON_QUIT_CLICKED, close == null ? new Function() {
				@Override
				public Object execute(Object object) {
					
					alive = false;
					frame.dispose();

					Main.playMusic = false;
					Main.audio.shutdown();
					
					return null;
				}
			} : close));
		
		} else
			components.add(new ButtonImageComponent(btnX, startY + buttonHeight*3 + 60, buttonWidth, buttonHeight, Images.MENU_BUTTON_MAIN_MENU, Images.MENU_BUTTON_MAIN_MENU_HOVER, Images.MENU_BUTTON_MAIN_MENU_CLICKED, new Function() {
				@Override
				public Object execute(Object object) {
					
					if (frame.getGamePanel() != null)
						frame.getGamePanel().setAlive(false);
					if (frame.getEditorPanel() != null)
						frame.getEditorPanel().setAlive(false);
					frame.switchToMenu();
					
					return null;
				}
			}));
		
	}

	private void initNewGameMenu() {
		
		UiComponent gameNameLabel = new Label(width/2 + 50, 200, 0, 0, "Nom du restaurant");
		gameNameLabel.setFlag(NEW_GAME_MENU_FLAG);
		
		TextField gameName = new TextField(width/2 + 50, 235, (int) (width/2.85), 75, "Coffee Lovers");
		gameName.setFlag(NEW_GAME_MENU_FLAG);
		components.add(gameName);

		UiComponent difficultyLabel = new Label(width/2 + 50, 400, 0, 0, "Difficulté");
		difficultyLabel.setFlag(NEW_GAME_MENU_FLAG);

		UiComponent caracterLabel = new Label(width/2 + 50, 600, 0, 0, "Personnage");
		caracterLabel.setFlag(NEW_GAME_MENU_FLAG);

		components.add(gameNameLabel);
		components.add(difficultyLabel);
		components.add(caracterLabel);
		
		initCharacters();
		initDifficulty();
		
		UiComponent startGame = new ButtonImageComponent(width/2 + 150, 875, buttonWidth, buttonHeight, Images.MENU_BUTTON_START, Images.MENU_BUTTON_START_HOVER, Images.MENU_BUTTON_START_CLICKED, new Function() {
			@Override
			public Object execute(Object object) {
				
				if (gameName.getText().length() >= 1 && Main.saves.size() < 4) {
					
					GameSave game = new GameSave(
							gameName.getText(),
							selectedDifficulty,
							selectedCharacter
					);
					
					Main.saves.add(game);
					Main.current = game;
					
					alive = false;
					frame.switchToGame();
					
				}
				
				return null;
			}
		});
		startGame.setFlag(NEW_GAME_MENU_FLAG);
		components.add(startGame);
		
	}

	private void initCharacters() {
		
		int sw = (int) (width/2.5)/4;
		int sh = (int) (sw * 0.89626556016597518962655601659751);
		
		SelectorButtonComponent man = new SelectorButtonComponent(width/2 + 150, 650, sw, sh, Images.MENU_CHARACTER_MAN_NOT_SELECTED, Images.MENU_CHARACTER_MAN_HOVER, Images.MENU_CHARACTER_MAN_SELECTED, null);
		man.setFlag(NEW_GAME_MENU_FLAG);
		
		SelectorButtonComponent girl = new SelectorButtonComponent(width/2 + 450, 650, sw, sh, Images.MENU_CHARACTER_GIRL_NOT_SELECTED, Images.MENU_CHARACTER_GIRL_HOVER, Images.MENU_CHARACTER_GIRL_SELECTED, null);
		girl.setFlag(NEW_GAME_MENU_FLAG);
		girl.setSelected(true);
		
		man.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				girl.setSelected(false);
				selectedCharacter = Characters.MAN;
				return null;
			}
		});
		girl.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				man.setSelected(false);
				selectedCharacter = Characters.GIRL;
				return null;
			}
		});
		
		components.add(man);
		components.add(girl);
		
	}
	
	private void initDifficulty() {
		
		int levelWidth = width/15;
		int levelHeight = (int) (levelWidth * 1.048192771084337);
		SelectorButtonComponent easy = new SelectorButtonComponent(width/2 + 100, 420, levelWidth, levelHeight, Images.MENU_DIFFICULTY_EASY_NOT_SELECTED, Images.MENU_DIFFICULTY_EASY, Images.MENU_DIFFICULTY_EASY, null);
		easy.setFlag(NEW_GAME_MENU_FLAG);
		easy.setSelected(true);
		
		SelectorButtonComponent normal = new SelectorButtonComponent(width/2 + 325, 420, levelWidth, levelHeight, Images.MENU_DIFFICULTY_NORMAL_NOT_SELECTED, Images.MENU_DIFFICULTY_NORMAL, Images.MENU_DIFFICULTY_NORMAL, null);
		normal.setFlag(NEW_GAME_MENU_FLAG);
		
		SelectorButtonComponent hard = new SelectorButtonComponent(width/2 + 550, 420, levelWidth, levelHeight, Images.MENU_DIFFICULTY_HARD_NOT_SELECTED, Images.MENU_DIFFICULTY_HARD, Images.MENU_DIFFICULTY_HARD, null);
		hard.setFlag(NEW_GAME_MENU_FLAG);
		
		easy.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				hard.setSelected(false);
				normal.setSelected(false);
				selectedDifficulty = Difficulties.EASY;
				return null;
			}
		});
		normal.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				hard.setSelected(false);
				easy.setSelected(false);
				selectedDifficulty = Difficulties.NORMAL;
				return null;
			}
		});
		hard.setPostAction(new Function() {
			@Override
			public Object execute(Object object) {
				easy.setSelected(false);
				normal.setSelected(false);
				selectedDifficulty = Difficulties.HARD;
				return null;
			}
		});
		
		components.add(easy);
		components.add(normal);
		components.add(hard);
		
	}

	private void initListeners() {
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				for (UiComponent component : components)
					if (component instanceof Button && ((Button)component).isCheckable() && !((Button)component).isClicked())
						((Button)component).hoverReleased();
				
				for (UiComponent component : components)
					if (component instanceof Button &&  ((Button)component).isCheckable())
						if (((Button)component).hover(e.getX(), e.getY()))
							break;
				
			}
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				for (UiComponent component : components)
					if (component instanceof Button && ((Button)component).isCheckable() && ((Button)component).isClicked())
						((Button)component).clickReleased();
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				for (UiComponent component : components)
					if (component instanceof Button && ((Button)component).isCheckable() && ((Button)component).isHover())
						if (((Button)component).clicked(e.getX(), e.getY()))
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
				
				if (newGame) {
					
					for (UiComponent component : components)
						if (component instanceof TextField && ((Button)component).isCheckable() && ((Button)component).isClicked())
							if (Character.isLetter(e.getKeyChar()) || e.getKeyChar() == ' ' || e.getKeyChar() == '\'')
								((TextField)component).entry(e.getKeyChar());
					
				}
				
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (newGame) {
				
					if (e.getKeyCode() == 8)
						for (UiComponent component : components)
							if (component instanceof TextField && ((Button)component).isCheckable() && ((Button)component).isClicked())
									((TextField)component).removeLast();
					
				}
				
			}
		});
		
	}
	
	private void setNewGameMenuVisibility(boolean visibility) {
		
		for (UiComponent comp : components)
			if (comp.getFlag() != null && comp.getFlag().equalsIgnoreCase(NEW_GAME_MENU_FLAG))
				comp.setVisible(visibility);
		
	}

	private void setLoadGameMenuVisibility(boolean visibility) {
		
		if (visibility)
			setHasToReload(true);
		else
			for (UiComponent comp : components)
				if (comp.getFlag() != null && comp.getFlag().equalsIgnoreCase(LOAD_GAME_MENU_FLAG))
					comp.setVisible(false);
		
	}
	
	private void reloadSaves() {
		
		//	Suppression sauvegardes
		setHasToReload(false);
		setReloading(true);
		
		List<UiComponent> componentsToRemove = new ArrayList<>();
		for (UiComponent comp : components)
			if (comp.getFlag() != null && comp.getFlag().equalsIgnoreCase(LOAD_GAME_MENU_FLAG))
				componentsToRemove.add(comp);
		
		if (!componentsToRemove.isEmpty())
			for (UiComponent comp : componentsToRemove)
				components.remove(comp);

		setReloading(false);
		
		//	Ajout sauvegardes
		if (!Main.saves.isEmpty()) {
			
			final int startX = width/2 + 50;
			final int startY = 175;
			final int levelSpace = 50;
			
			final int levelWidth = width/14;
			final int levelHeight = (int) (levelWidth * 1.048192771084337);

			final int saveWidth = (int) (width/2.85) + 20;
			final int saveHeight = levelHeight + 20;
			
			int i = 0;
			for (GameSave save : Main.saves.stream().sorted( (gs1, gs2) -> gs2.getSaveDate().compareTo(gs1.getSaveDate()) ).toList()) {
				
				ButtonColorComponent saveBack = new ButtonColorComponent(startX - 10, startY + i*levelHeight + i*levelSpace - 10, saveWidth, saveHeight, null, new Color(255, 255, 255, 75), new Color(255, 255, 255, 125), new Function() {
					@Override
					public Object execute(Object object) {
						// TODO Auto-generated method stub
						return null;
					}
				});
				saveBack.setFlag(LOAD_GAME_MENU_FLAG);
				saveBack.setBorder(false);
				saveBack.setWithText(false);
				components.add(saveBack);
				
				ButtonColorComponent saveRemove = new ButtonColorComponent(startX + saveWidth - 80, startY + i*levelHeight + i*levelSpace - 10 - 20, 50, 50, new Color(245, 73, 39), new Color(248, 24, 99), new Color(250, 161, 143), new Function() {
					@Override
					public Object execute(Object object) {
						
						Main.saves.remove(save);
						setHasToReload(true);
						
						return null;
					}
				});
				saveRemove.setFlag(LOAD_GAME_MENU_FLAG);
				saveRemove.setBorderColor(new Color(112, 23, 5));
				saveRemove.setBorderHoverColor(new Color(68, 14, 3));
				saveRemove.setBorderClickedColor(new Color(24, 5, 1));
				saveRemove.setText("X");
				saveRemove.setTextColor(Color.white);
				components.add(saveRemove);
				
				SelectorButtonComponent diff = null;
				switch (save.getCharacter()) {
				case GIRL:
					diff = new SelectorButtonComponent(startX, startY + i*levelHeight + i*levelSpace, levelWidth, levelHeight, Images.MENU_CHARACTER_GIRL_NOT_SELECTED, Images.MENU_CHARACTER_GIRL_NOT_SELECTED, Images.MENU_CHARACTER_GIRL, null);
					break;
				case MAN:
					diff = new SelectorButtonComponent(startX, startY + i*levelHeight + i*levelSpace, levelWidth, levelHeight, Images.MENU_CHARACTER_MAN_NOT_SELECTED, Images.MENU_CHARACTER_MAN_NOT_SELECTED, Images.MENU_CHARACTER_MAN, null);
					break;
				}
				
				diff.setFlag(LOAD_GAME_MENU_FLAG);
				diff.setCheckable(false);
				diff.setSelected(true);
				components.add(diff);
				
				UiComponent gameNameLabel = new Label(startX + levelWidth + 15, startY + i*levelHeight + i*levelSpace + 50, 0, 0, save.getName());
				gameNameLabel.setFlag(LOAD_GAME_MENU_FLAG);
				components.add(gameNameLabel);
				
				Label gameDateLabel = new Label(startX + levelWidth + 15, startY + i*levelHeight + i*levelSpace + 65 + 15, 0, 0, Main.FORMATTER.format(save.getSaveDate()));
				gameDateLabel.setFlag(LOAD_GAME_MENU_FLAG);
				gameDateLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
				components.add(gameDateLabel);
				
				Label gameDifficultyLabel = new Label(startX + levelWidth + 15, startY + i*levelHeight + i*levelSpace + 65 + 30 + 15*2, 0, 0, "Difficulté : " + save.getDifficulty().getLabel());
				gameDifficultyLabel.setFlag(LOAD_GAME_MENU_FLAG);
				gameDifficultyLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
				components.add(gameDifficultyLabel);
				
				i++;
				
			}
			
		} else {
			
			UiComponent gameNameLabel = new Label(width/2 + 150, 200, 0, 0, "Aucune Sauvegarde");
			gameNameLabel.setFlag(LOAD_GAME_MENU_FLAG);
			components.add(gameNameLabel);
			
		}
		
	}

	public void restartOverlay() {
		
		if (menu)
			overlay = new ColorOverlay(0, 0, width, height, 0, 0, 0, 1.0f, 0.0f, 2000);
		else 
			overlay = new ColorOverlay(0, 0, width, height, 0, 0, 0, 0.0f, 0.4f, 1000, true);
		
	}

	public void clear() {
		
		setNewGame(false);
		setLoadGame(false);
		setNewGameMenuVisibility(false);
		setLoadGameMenuVisibility(false);
		
	}
	
	public void initUnscale(double scale) {
		
		this.setScale(scale);
		
		buttonWidth = (int) ((double)originButtonWidth / scale);
		buttonHeight = (int) ((double)originButtonHeight / scale);
		
		overlay.setWidth((int) ((double)width / scale));
		overlay.setHeight((int) ((double)height / scale));
		
		for (UiComponent c : components) {
			
			c.setWidth((int) ((double) c.getWidth() / scale));
			c.setHeight((int) ((double) c.getHeight() / scale));
			
			c.setX((int) ((double) c.getX() / scale));
			c.setY((int) ((double) c.getY() / scale));
			
		}
		
	}
	
	public void paintUnscaledComponent(Graphics g, double scale) {
		
		overlay.draw(g);
		
		UiComponent comp = null;
		for (UiComponent c : components)
			if (c instanceof ImageComponent)
				comp = c;
		
		drawMenuBackground(g, comp, scale);
		
		if (newGame || loadGame)
			drawNewGameMenu(g);
		
		if (!reloading)
			for (UiComponent component : components)
				component.draw(g);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		if (menu)
			background.draw(g);
		else
			overlay.draw(g);
		
		UiComponent comp = null;
		for (UiComponent c : components)
			if (c instanceof ImageComponent)
				comp = c;
		
		drawMenuBackground(g, comp, 1.0);
		
		if (newGame || loadGame)
			drawNewGameMenu(g);
		
		if (!reloading)
			for (UiComponent component : components)
				component.draw(g);
		
		if (menu)
			overlay.draw(g);
		
	}
	
	private void drawMenuBackground(Graphics g, UiComponent comp, double scale) {

		int scaledSize = (int) (5.0 / scale);
		
		int scaledWidthSpace = (int) (20.0 / scale);
		int scaledHeightSpace = (int) (105.0 / scale);
		
		int scaledXSpace = (int) (60.0 / scale);
		int scaledYSpace = (int) (50.0 / scale);
		
		int yWithHeight = comp.getY() + comp.getHeight();
		int yWithHeightAndButtons = yWithHeight + buttonHeight* (menu ? 6 : 5);
		
		g.setColor(Color.black);
		g.fillRect(
				comp.getX() - scaledXSpace, 
				yWithHeight + scaledYSpace, 
				buttonWidth +scaledWidthSpace, 
				(int) (5.0 / scale)
		);
		
		g.setColor(new Color(255, 255, 255, 50));
		g.fillRect(
				comp.getX() - scaledXSpace, 
				yWithHeight + scaledYSpace +scaledSize, 
				buttonWidth +scaledWidthSpace, 
				(yWithHeightAndButtons + scaledHeightSpace) - (yWithHeight + scaledYSpace) - scaledSize
		);
		
		g.setColor(Color.black);
		g.fillRect(
				comp.getX() - scaledXSpace, 
				yWithHeightAndButtons + scaledHeightSpace, 
				buttonWidth +scaledWidthSpace, 
				scaledSize);
		
	}

	public void drawNewGameMenu(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(width/2, 100, (int) (width/2.5), 5);
		
		g.setColor(new Color(255, 255, 255, 80));
		g.fillRect(width/2, 100 +5, (int) (width/2.5), height -200 -5);
		
		g.setColor(Color.black);
		g.fillRect(width/2, height -100, (int) (width/2.5), 5);
		
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

	public void update(long time) {
		
		overlay.update(time);
		
		if (hasToReload)
			reloadSaves();
		
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

	public Background getMenuBackground() {
		return background;
	}

	public void setMenuBackground(Background background) {
		this.background = background;
	}

	public ColorOverlay getOverlay() {
		return overlay;
	}

	public void setOverlay(ColorOverlay overlay) {
		this.overlay = overlay;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public GameFrame getFrame() {
		return frame;
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}

	public boolean isNewGame() {
		return newGame;
	}

	public void getNewGame(boolean newGame) {
		this.newGame = newGame;
	}

	public boolean isLoadGame() {
		return loadGame;
	}

	public void setLoadGame(boolean loadGame) {
		this.loadGame = loadGame;
	}

	public boolean isReloading() {
		return reloading;
	}

	public void setReloading(boolean reloading) {
		this.reloading = reloading;
	}

	public boolean isHasToReload() {
		return hasToReload;
	}

	public void setHasToReload(boolean hasToReload) {
		this.hasToReload = hasToReload;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	public List<UiComponent> getUiComponents() {
		return components;
	}

	public void setUiComponents(List<UiComponent> components) {
		this.components = components;
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

	public int getButtonHeight() {
		return buttonHeight;
	}

	public void setButtonHeight(int buttonHeight) {
		this.buttonHeight = buttonHeight;
	}

	public int getButtonWidth() {
		return buttonWidth;
	}

	public void setButtonWidth(int buttonWidth) {
		this.buttonWidth = buttonWidth;
	}

	public Difficulties getSelectedDifficulty() {
		return selectedDifficulty;
	}

	public void setSelectedDifficulty(Difficulties selectedDifficulty) {
		this.selectedDifficulty = selectedDifficulty;
	}

	public Characters getSelectedCharacter() {
		return selectedCharacter;
	}

	public void setSelectedCharacter(Characters selectedCharacter) {
		this.selectedCharacter = selectedCharacter;
	}

	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}

	public Function getClose() {
		return close;
	}

	public void setClose(Function close) {
		this.close = close;
	}

	public int getOriginButtonHeight() {
		return originButtonHeight;
	}

	public void setOriginButtonHeight(int originButtonHeight) {
		this.originButtonHeight = originButtonHeight;
	}

	public int getOriginButtonWidth() {
		return originButtonWidth;
	}

	public void setOriginButtonWidth(int originButtonWidth) {
		this.originButtonWidth = originButtonWidth;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

}
