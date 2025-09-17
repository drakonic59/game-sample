package fr.hattane.ilias.games.sample.isometric2d.game.sounds;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import fr.hattane.ilias.games.sample.isometric2d.Main;
import fr.hattane.ilias.games.sample.isometric2d.config.SoundCategories;
import fr.hattane.ilias.games.sample.isometric2d.config.Sounds;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
	
	private static final AtomicBoolean FX_STARTED = new AtomicBoolean(false);
	
	private Map<Sounds, MediaPlayerAdapter> players;
	
	public AudioManager() {
		
		players = new HashMap<>();
		startToolkitIfNeeded();
		
	}
	
	public MediaPlayerAdapter initMedia(Sounds sound, String path) {
		
		final MediaPlayerAdapter adapter = new MediaPlayerAdapter(null);
		runOnFX(() -> {
			
			try {
				
				Media media = resolveMedia(path);
				
				MediaPlayer player = new MediaPlayer(media);
				player.setOnReady(() ->  {
					 
					player.setVolume(((Double) Main.parameters.getProperty(SoundCategories.soundCategory(sound).getSetting())).doubleValue());
					player.play();
					 
				});
				player.setOnEndOfMedia(() -> adapter.setFinished(true));
				adapter.setPlayer(player);

				if (players.get(sound) != null) {
					stop(players.get(sound).getPlayer());
					players.replace(sound, adapter);
				} else
					players.put(sound, adapter);
								
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		
		return adapter;
		
	}
	
	private Media resolveMedia(String input) throws Exception {
		
		URL url = AudioManager.class.getResource(input.startsWith("/") ? input : "/" + input);
		if (url != null) return new Media(url.toExternalForm());
		
		Path p = Path.of(input).toAbsolutePath();
		if (Files.exists(p)) return new Media(p.toUri().toString());
		
		throw new IllegalArgumentException("Audio introuvable (classpath ou fichier): " + input);
	}
	
	 private static void startToolkitIfNeeded() {
		 
		if (FX_STARTED.compareAndSet(false, true)) {

			final CountDownLatch latch = new CountDownLatch(1);
			Platform.startup(latch::countDown);
			
			try { latch.await(); } catch (InterruptedException ignored) {}
			
		}
	}
	
	private static void runOnFX(Runnable r) {
		
		if (Platform.isFxApplicationThread()) 
			r.run();
		else
			Platform.runLater(r);
		
	}
	
	public void shutdown() {
		
        if (!FX_STARTED.get()) 
        	return;
        
        for (Sounds sound : players.keySet())
        	if (players.get(sound) != null)
        		stop(players.get(sound).getPlayer());
        
        final CountDownLatch latch = new CountDownLatch(1);
        Platform.exit();
        
        new Thread(() -> {
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
            latch.countDown();
        }, "FXShutdownWait").start();
        
        try { latch.await(); } catch (InterruptedException ignored) {}
    }

    public void play(MediaPlayer player) { 
    	if(player != null) 
    		player.play();
    }
    
    public void pause(MediaPlayer player) { 
    	if (player != null) 
    		player.pause();
    }
    
    public void stop(MediaPlayer player) { 
    	if (player != null) 
    		player.stop();
    }
    
    public void kill(MediaPlayer player) {
    	
    	stop(player);
    	
        runOnFX(() -> {
            if (player != null) {
                try { player.stop(); } catch (Exception ignored) {}
                try { player.dispose(); } catch (Exception ignored) {}
            }
        });
    	
    }
    
    public void updateVolume() {
    	
    	runOnFX(() -> {
            
    		for (Sounds sound : players.keySet())
    			if (players.get(sound) != null && players.get(sound).getPlayer() != null)
					players.get(sound).getPlayer().setVolume(((Double) Main.parameters.getProperty(SoundCategories.soundCategory(sound).getSetting())).doubleValue());
    		
    	});
    	
    }
    
}
