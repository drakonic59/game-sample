package fr.hattane.ilias.games.sample.isometric2d.ui.components;

public abstract class UiComponent extends Component {
		
	private String flag;
	
	public UiComponent() {}
	
	public UiComponent(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
