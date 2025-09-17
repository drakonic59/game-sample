package fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.overlays;

import java.awt.Graphics;

import fr.hattane.ilias.games.sample.isometric2d.ui.backgrounds.ColorBackground;

public class ColorOverlay extends ColorBackground {
	
	private long msTime;
	private long msCount = 0;
	
	private float alphaStart;
	private float alphaEnd;
	
	private boolean finished = false;
	private boolean continu = false;
	
	public ColorOverlay() {
		super();
	}

	public ColorOverlay(int x, int y, int width, int height, int red, int green, int blue, float alphaStart, float alphaEnd, long msTime) {
		super(x, y, width, height, red, green, blue, alphaStart);
		this.alphaStart = alphaStart;
		this.alphaEnd = alphaEnd;
		this.msTime = msTime;
	}

	public ColorOverlay(int x, int y, int width, int height, int red, int green, int blue, float alphaStart, float alphaEnd, long msTime, boolean continu) {
		super(x, y, width, height, red, green, blue, alphaStart);
		this.alphaStart = alphaStart;
		this.alphaEnd = alphaEnd;
		this.msTime = msTime;
		this.continu = continu;
	}
	
	@Override
	public void update(long msTime) {
		
		if (!finished) {
			
			msCount += msTime;
			msCount = Math.min(this.msTime, msCount);
			
			if (alphaStart > alphaEnd)
				setAlpha((float) Math.max(Math.min(alphaStart - ((double)msCount / (double)this.msTime) * (double)(alphaStart - alphaEnd), 1.0f), 0.0f));
			else
				setAlpha((float) Math.min(Math.max(alphaStart + ((double)msCount / (double)this.msTime) * (double)(alphaEnd - alphaStart), 0.0f), 1.0f));
			
			if (msCount >= this.msTime) {
				finished = true;
				setAlpha(alphaEnd);
			}
			
		}
		
	}

	@Override
	public void draw(Graphics g) {
		
		if (!finished || continu) {
			g.setColor(getColor());
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		}
		
	}

	public long getMsTime() {
		return msTime;
	}

	public void setMsTime(long msTime) {
		this.msTime = msTime;
	}

	public long getMsCount() {
		return msCount;
	}

	public float getAlphaStart() {
		return alphaStart;
	}

	public void setAlphaStart(float alphaStart) {
		this.alphaStart = alphaStart;
	}

	public void setMsCount(long msCount) {
		this.msCount = msCount;
	}

	public float getAlphaEnd() {
		return alphaEnd;
	}

	public void setAlphaEnd(float alphaEnd) {
		this.alphaEnd = alphaEnd;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isContinue() {
		return continu;
	}

	public void setContinue(boolean continu) {
		this.continu = continu;
	}
	
}
