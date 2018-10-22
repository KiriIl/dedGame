package ded.game.unit;

import java.awt.Graphics;

import framework.game.Camera;

public interface UnitDrawer {

	public void drawUnit(Graphics g, Unit unit, float x, float y, float w, float h, Camera cam);
	
}
