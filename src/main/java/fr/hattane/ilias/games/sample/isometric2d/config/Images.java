package fr.hattane.ilias.games.sample.isometric2d.config;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import fr.hattane.ilias.games.sample.isometric2d.game.map.Tile;

public enum Images {
	
	MENU_BACKGROUND								("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\background.png", -1),
	MENU_TITLE									("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\title.png", -1),
	
	MENU_BUTTON_NEW_GAME						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\new_game.png", -1),
	MENU_BUTTON_NEW_GAME_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\new_game_hover.png", -1),
	MENU_BUTTON_NEW_GAME_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\new_game_clicked.png", -1),
	
	MENU_BUTTON_LOAD_GAME						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\load_game.png", -1),
	MENU_BUTTON_LOAD_GAME_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\load_game_hover.png", -1),
	MENU_BUTTON_LOAD_GAME_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\load_game_clicked.png", -1),
	
	MENU_BUTTON_QUIT							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\quit.png", -1),
	MENU_BUTTON_QUIT_HOVER						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\quit_hover.png", -1),
	MENU_BUTTON_QUIT_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\quit_clicked.png", -1),
	
	MENU_BUTTON_START							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\start.png", -1),
	MENU_BUTTON_START_HOVER						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\start_hover.png", -1),
	MENU_BUTTON_START_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\start_clicked.png", -1),
	
	MENU_BUTTON_SAVE							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\save.png", -1),
	MENU_BUTTON_SAVE_HOVER						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\save_hover.png", -1),
	MENU_BUTTON_SAVE_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\save_clicked.png", -1),
	
	MENU_BUTTON_SETTINGS						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\settings.png", -1),
	MENU_BUTTON_SETTINGS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\settings_hover.png", -1),
	MENU_BUTTON_SETTINGS_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\settings_clicked.png", -1),
	
	MENU_BUTTON_MAIN_MENU						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\main_menu.png", -1),
	MENU_BUTTON_MAIN_MENU_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\main_menu_hover.png", -1),
	MENU_BUTTON_MAIN_MENU_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\main_menu_clicked.png", -1),
	
	MENU_BUTTON_EDITOR							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\editor.png", -1),
	MENU_BUTTON_EDITOR_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\editor_hover.png", -1),
	MENU_BUTTON_EDITOR_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\editor_clicked.png", -1),

	MENU_DIFFICULTY_EASY						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_easy.png", -1),
	MENU_DIFFICULTY_EASY_NOT_SELECTED			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_easy_not_selected.png", -1),
	
	MENU_DIFFICULTY_NORMAL						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_normal.png", -1),
	MENU_DIFFICULTY_NORMAL_NOT_SELECTED			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_normal_not_selected.png", -1),
	
	MENU_DIFFICULTY_HARD						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_hard.png", -1),
	MENU_DIFFICULTY_HARD_NOT_SELECTED			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\difficulty_hard_not_selected.png", -1),

	MENU_CHARACTER_GIRL							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\girl.png", -1),
	MENU_CHARACTER_GIRL_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\girl_hover.png", -1),
	MENU_CHARACTER_GIRL_SELECTED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\girl_selected.png", -1),
	MENU_CHARACTER_GIRL_NOT_SELECTED			("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\girl_not_selected.png", -1),

	MENU_CHARACTER_MAN							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\man.png", -1),
	MENU_CHARACTER_MAN_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\man_hover.png", -1),
	MENU_CHARACTER_MAN_SELECTED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\man_selected.png", -1),
	MENU_CHARACTER_MAN_NOT_SELECTED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\man_not_selected.png", -1),
	
	EDITOR_BUTTON_COLORS						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\colors.png", -1),
	EDITOR_BUTTON_COLORS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\colors_hover.png", -1),
	EDITOR_BUTTON_COLORS_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\colors_clicked.png", -1),
	
	EDITOR_BUTTON_HEIGHTS						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\heights.png", -1),
	EDITOR_BUTTON_HEIGHTS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\heights_hover.png", -1),
	EDITOR_BUTTON_HEIGHTS_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\heights_clicked.png", -1),
	
	EDITOR_BUTTON_GET_COLOR						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\get_color.png", -1),
	EDITOR_BUTTON_GET_COLOR_HOVER				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\get_color_hover.png", -1),
	EDITOR_BUTTON_GET_COLOR_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\get_color_clicked.png", -1),
	
	EDITOR_BUTTON_OBJECTS						("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\objects.png", -1),
	EDITOR_BUTTON_OBJECTS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\objects_hover.png", -1),
	EDITOR_BUTTON_OBJECTS_CLICKED				("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\objects_clicked.png", -1),
	
	EDITOR_BUTTON_MORE							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\more.png", -1),
	EDITOR_BUTTON_MORE_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\more_hover.png", -1),
	EDITOR_BUTTON_MORE_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\more_clicked.png", -1),
	
	EDITOR_BUTTON_LESS							("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\less.png", -1),
	EDITOR_BUTTON_LESS_HOVER					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\less_hover.png", -1),
	EDITOR_BUTTON_LESS_CLICKED					("D:\\Bibliothèque\\Projets\\isometric sample\\menu\\less_clicked.png", -1),
	
	GAME_NATURE_TREE_1							("D:\\Bibliothèque\\Projets\\isometric sample\\game\\nature\\tree_1.png", (int) ((double)Tile.TILE_HEIGHT*4));
	
	private BufferedImage image;
	private int height;
	
	private Images(String path, int height) {
		
		try {
			this.image = ImageIO.read(new File(path));
			this.height = height;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public int getHeight() {
		return height;
	}
	
}
