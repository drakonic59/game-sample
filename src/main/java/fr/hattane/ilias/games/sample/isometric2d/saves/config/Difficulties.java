package fr.hattane.ilias.games.sample.isometric2d.saves.config;

public enum Difficulties {
	
	EASY		("Facile"),
	NORMAL		("Normal"),
	HARD		("Difficile");
	
	private String label;

	private Difficulties(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
}
