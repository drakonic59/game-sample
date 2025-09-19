package fr.hattane.ilias.games.sample.isometric2d.saves;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import com.google.gson.Gson;

import fr.hattane.ilias.games.sample.isometric2d.saves.config.Characters;
import fr.hattane.ilias.games.sample.isometric2d.saves.config.Difficulties;

public class GameSave {
	
	public static final long SECOND = 1000;
	public static final long MINUTE = SECOND*60;
	public static final long HOUR = MINUTE*60;
	
	private Date creationDate;
	private Date saveDate;
	
	private String name;
	
	private Difficulties difficulty;
	private Characters character;
	
	public GameSave() {}
	
	public GameSave(File file) throws Exception {
		
		if (!load(file))
			throw new Exception("Impossible de lire la sauvegarde du fichier : '" + file.getAbsolutePath() + "'");
		
	}
	
	public GameSave(String name, Difficulties difficulty, Characters character) {
		super();
		this.creationDate = new Date();
		this.saveDate = creationDate;
		this.name = name;
		this.difficulty = difficulty;
		this.character = character;
	}

	public String getGameTime() {
		
		long time = saveDate.getTime() - creationDate.getTime();
		
		if (time > 0) {
			long hourCount = time / HOUR;
			
			time -= hourCount*HOUR;
			long minuteCount = time / MINUTE;
			
			time -= minuteCount*MINUTE;
			long secondCount = time / SECOND;
			
			return hourCount + "h " + minuteCount + "m " + secondCount + "s";
		}
		
		return "0h 0m 0s";
	}
	
	public boolean load(File file) {
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			StringBuilder sb = new StringBuilder();
			
			String line;
			while ((line = br.readLine()) != null)
				sb.append(line + "\n");
			
			br.close();
			
			GameSave save = (new Gson()).fromJson(sb.toString(), GameSave.class);
			if (save != null) {
				setSaveDate(save.getSaveDate());
				setName(save.getName());
				setDifficulty(save.getDifficulty());
				setCharacter(save.getCharacter());
			} else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public boolean save(File file) {
		
		try {
			
			PrintWriter pw = new PrintWriter(file);
			pw.println((new Gson()).toJson(pw));
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Difficulties getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulties difficulty) {
		this.difficulty = difficulty;
	}

	public Characters getCharacter() {
		return character;
	}

	public void setCharacter(Characters character) {
		this.character = character;
	}

	public Date getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
