package framework.game;

import java.awt.Graphics;

public abstract class Effect {

	private boolean ended = false;
	
	public boolean isEnded() {
		return ended;
	}
	
	public void stop() {
		ended = true;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g, Camera cam);
	
}
