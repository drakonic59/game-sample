package fr.hattane.ilias.games.sample.isometric2d.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.ui.panels.EditorPanel;
import fr.hattane.ilias.games.sample.isometric2d.ui.panels.GamePanel;
import fr.hattane.ilias.games.sample.isometric2d.ui.panels.MenuPanel;

public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = 4239955937362976793L;
	
	private MenuPanel menuPanel;
	private GamePanel gamePanel;
	private EditorPanel editorPanel;
	
	public GameFrame() {
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.menuPanel = new MenuPanel(this, (int) dim.getWidth(), (int) dim.getHeight(), true);
		
		this.setContentPane(menuPanel);
				
		this.setVisible(true);
		
		this.addWindowListener(getWindowListener());
		
		menuPanel.start();
		
	}

	public void switchToEditor() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		editorPanel = new EditorPanel(this, (int) dim.getWidth(), (int) dim.getHeight());
		
		setContentPane(editorPanel);
		editorPanel.clear();
		editorPanel.getPauseMenu().clear();
		editorPanel.getPauseMenu().restartOverlay();
		
	    validate();
	    
	    editorPanel.start();
	    editorPanel.requestFocus();
		
		repaint();
		
	}

	public void switchToGame() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gamePanel = new GamePanel(this, (int) dim.getWidth(), (int) dim.getHeight());
		
		setContentPane(gamePanel);
		gamePanel.clear();
		gamePanel.getPauseMenu().clear();
		gamePanel.getPauseMenu().restartOverlay();
		
	    validate();
	    
		gamePanel.start();
		gamePanel.requestFocus();
		
		repaint();
		
	}

	public void switchToMenu() {
		
		setContentPane(menuPanel);
		menuPanel.clear();
		menuPanel.restartOverlay();
		
	    validate();
	    
	    menuPanel.start();
	    menuPanel.requestFocus();
		
		repaint();
		
	}

	private WindowListener getWindowListener() {
		return new WindowListener() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				menuPanel.end();
				if (gamePanel != null)
					gamePanel.end();
				if (editorPanel != null)
					editorPanel.end();
				
				Main.playMusic = false;
				Main.audio.shutdown();

//				if (Main.currentlyPlaying != null)
//					Main.currentlyPlaying.close();
				
			}
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
			
		};
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public EditorPanel getEditorPanel() {
		return editorPanel;
	}

	public void setEditorPanel(EditorPanel editorPanel) {
		this.editorPanel = editorPanel;
	}
	
}
