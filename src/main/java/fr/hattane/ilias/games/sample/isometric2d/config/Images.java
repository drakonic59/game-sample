package fr.hattane.ilias.games.sample.isometric2d.config;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public enum Images {
	
	MENU_BACKGROUND								("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\background.png"),
	MENU_TITLE									("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\title.png"),
	
	MENU_BUTTON_NEW_GAME						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\new_game.png"),
	MENU_BUTTON_NEW_GAME_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\new_game_hover.png"),
	MENU_BUTTON_NEW_GAME_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\new_game_clicked.png"),
	
	MENU_BUTTON_LOAD_GAME						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\load_game.png"),
	MENU_BUTTON_LOAD_GAME_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\load_game_hover.png"),
	MENU_BUTTON_LOAD_GAME_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\load_game_clicked.png"),
	
	MENU_BUTTON_QUIT							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\quit.png"),
	MENU_BUTTON_QUIT_HOVER						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\quit_hover.png"),
	MENU_BUTTON_QUIT_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\quit_clicked.png"),
	
	MENU_BUTTON_START							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\start.png"),
	MENU_BUTTON_START_HOVER						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\start_hover.png"),
	MENU_BUTTON_START_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\start_clicked.png"),
	
	MENU_BUTTON_SAVE							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\save.png"),
	MENU_BUTTON_SAVE_HOVER						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\save_hover.png"),
	MENU_BUTTON_SAVE_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\save_clicked.png"),
	
	MENU_BUTTON_SETTINGS						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\settings.png"),
	MENU_BUTTON_SETTINGS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\settings_hover.png"),
	MENU_BUTTON_SETTINGS_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\settings_clicked.png"),
	
	MENU_BUTTON_MAIN_MENU						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\main_menu.png"),
	MENU_BUTTON_MAIN_MENU_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\main_menu_hover.png"),
	MENU_BUTTON_MAIN_MENU_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\main_menu_clicked.png"),
	
	MENU_BUTTON_EDITOR							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\editor.png"),
	MENU_BUTTON_EDITOR_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\editor_hover.png"),
	MENU_BUTTON_EDITOR_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\editor_clicked.png"),

	MENU_DIFFICULTY_EASY						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_easy.png"),
	MENU_DIFFICULTY_EASY_NOT_SELECTED			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_easy_not_selected.png"),
	
	MENU_DIFFICULTY_NORMAL						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_normal.png"),
	MENU_DIFFICULTY_NORMAL_NOT_SELECTED			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_normal_not_selected.png"),
	
	MENU_DIFFICULTY_HARD						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_hard.png"),
	MENU_DIFFICULTY_HARD_NOT_SELECTED			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_hard_not_selected.png"),

	MENU_CHARACTER_GIRL							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\girl.png"),
	MENU_CHARACTER_GIRL_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\girl_hover.png"),
	MENU_CHARACTER_GIRL_SELECTED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\girl_selected.png"),
	MENU_CHARACTER_GIRL_NOT_SELECTED			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\girl_not_selected.png"),

	MENU_CHARACTER_MAN							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\man.png"),
	MENU_CHARACTER_MAN_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\man_hover.png"),
	MENU_CHARACTER_MAN_SELECTED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\man_selected.png"),
	MENU_CHARACTER_MAN_NOT_SELECTED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\man_not_selected.png"),
	
	EDITOR_BUTTON_COLORS						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\colors.png"),
	EDITOR_BUTTON_COLORS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\colors_hover.png"),
	EDITOR_BUTTON_COLORS_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\colors_clicked.png"),
	
	EDITOR_BUTTON_HEIGHTS						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\heights.png"),
	EDITOR_BUTTON_HEIGHTS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\heights_hover.png"),
	EDITOR_BUTTON_HEIGHTS_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\heights_clicked.png"),
	
	EDITOR_BUTTON_MORE							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\more.png"),
	EDITOR_BUTTON_MORE_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\more_hover.png"),
	EDITOR_BUTTON_MORE_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\more_clicked.png"),
	
	EDITOR_BUTTON_LESS							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\less.png"),
	EDITOR_BUTTON_LESS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\less_hover.png"),
	EDITOR_BUTTON_LESS_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\less_clicked.png");
	
	private BufferedImage image;
	
	private Images(String path) {
		
		try {
			image = ImageIO.read(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}
