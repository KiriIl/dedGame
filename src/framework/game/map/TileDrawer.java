package framework.game.map;

import java.awt.Graphics;

public interface TileDrawer {

	/**
	 * @param g
	 * @param type
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void draw(Graphics g, Tile tile, int x, int y, int w, int h);
	
}
