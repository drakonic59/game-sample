package fr.hattane.ilias.games.sample.isometric2d;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.hattane.ilias.games.sample.isometric2d.config.Settings;
import fr.hattane.ilias.games.sample.isometric2d.config.Sounds;
import fr.hattane.ilias.games.sample.isometric2d.game.sounds.AudioManager;
import fr.hattane.ilias.games.sample.isometric2d.game.sounds.MediaPlayerAdapter;
import fr.hattane.ilias.games.sample.isometric2d.saves.GameSave;
import fr.hattane.ilias.games.sample.isometric2d.ui.GameFrame;
import fr.hattane.ilias.games.sample.isometric2d.utils.Parameters;

public class Main  {

	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public static File MAIN_FOLDER;
	
	public static List<GameSave> saves;
	public static GameSave current;
	
	public static GameFrame frame;
	public static boolean debug = false;
	
	public static boolean pauseMusic = false;
	public static boolean playMusic = true;
	
	public static double musicVolume = 1.0;
	
	public static AudioManager audio;
	
	public static Parameters<Settings> parameters;
	
	public static void main(String[] args) throws IOException {
		
		initSettings();
		loadSaves();
		
		frame = new GameFrame();
		
		startMusics();
		
	}

	private static void initSettings() {
		
		parameters = new Parameters<>();
		for (Settings setting : Settings.values())
			parameters.addProperty(setting, setting.getDefaultValue());
		
		audio = new AudioManager();
		
	}

	private static void loadSaves() throws IOException {
		
		saves = new ArrayList<>();
		
		String appData = System.getenv("APPDATA");
        if (appData == null)
            appData = "./game";

        File main = new File(appData);
        if (!main.exists())
        	main.mkdirs();
                
       	Path game = Paths.get(appData, ".game");
        Files.createDirectories(game);
		
        MAIN_FOLDER = game.toFile();
        
		if (MAIN_FOLDER.listFiles().length == 0) {
			System.out.println("Existe vide");
		} else
			System.out.println("existe plein");
		
	}
	
	public static void startMusics() {
		
		(new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					while (playMusic) {
						
						for (Sounds music : Sounds.musicValues()) {
							
							MediaPlayerAdapter adapter = audio.initMedia(music, music.getPath());
							while (adapter.getPlayer() == null)
								Thread.sleep(100);
							
							while (!adapter.isFinished()) {
								
								if (!playMusic)
									break;
								
								Thread.sleep(1000);
								
							}
							
							while (pauseMusic) {
								
								if (!playMusic)
									break;
								
								Thread.sleep(1000);
								
							}
							
							if (!playMusic)
								break;
							
							Thread.sleep(1000);
				            
						}
			            
					}
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				
			}
		})).start();
		
	}
	
	public static void playSound(Sounds sound) {
		audio.initMedia(sound, sound.getPath());
	}

}
