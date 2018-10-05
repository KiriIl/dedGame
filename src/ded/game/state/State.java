package ded.game.state;

import java.awt.Graphics;

public interface State {

	public void render(Graphics g);
	public void tick();
	public void superTick();
	
}
