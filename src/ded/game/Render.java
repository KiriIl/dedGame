package ded.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import ded.Img;
import ded.R;
import ded.game.state.Menu;
import ded.game.state.Settings;
import ded.game.unit.Ded;
import ded.game.unit.Moskal;
import ded.game.unit.Unit;
import ded.game.unit.UnitDrawer;
import framework.core.dedRunnable;
import framework.game.map.Tile;
import framework.game.map.TileDrawer;
import framework.graph.ImageFactory;

public class Render extends JComponent implements TileDrawer, UnitDrawer, dedRunnable{
	//kakayato huyina
	private static final long serialVersionUID = 1L;
	
	public Render() {
		this.setSize(800, 600);
		this.setPreferredSize(this.getSize());
		this.setMinimumSize(this.getSize());
	}
	
	Menu m = new Menu();
	
	@Override
	public void paintComponent(Graphics g) {
		if(System.getProperty("os.name").toLowerCase().contains("linux")) Toolkit.getDefaultToolkit().sync();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		switch(R.state) {
		case 0:
			R.menu.render(g);
			break;
		case 1:
			R.world.render(g);
			break;
		case 2:
			R.battle.render(g);
			break;
		case 3:
			R.settings.render(g);
			break;
		}
	}

	public void tick() {
		repaint();
	}
	
	public void superTick() {
		
	}
	
	private BufferedImage texture =  ImageFactory.load("/res/img/textures.png");
	private BufferedImage grass = ImageFactory.crop(texture, 0, 0, 16, 16);
	private BufferedImage wall = ImageFactory.crop(texture, 16, 0, 16, 16);
	
	public void drawUnit(Graphics g, Unit unit, float x, float y, float w, float h) {
		switch(R.state) {
		case 0:
			break;
		case 1:
			g.setColor(new Color(0, 0, 0, 90));
			g.fillOval((int)R.cam.getX(unit.x), (int)R.cam.getY(unit.y+unit.h-4), (int)R.cam.byDelta(unit.w), (int)R.cam.byDelta(6));
			if(unit instanceof Ded) {
				g.drawImage(Img.DED, (int)R.cam.getX(unit.x), (int)R.cam.getY(unit.y), (int)R.cam.byDelta(unit.w), (int)R.cam.byDelta(unit.h), null);
			}else if(unit instanceof Moskal) {
				g.drawImage(Img.MOSKAL, (int)R.cam.getX(unit.x), (int)R.cam.getY(unit.y), (int)R.cam.byDelta(unit.w), (int)R.cam.byDelta(unit.h), null);
			}
			break;
		case 2:
			if(unit instanceof Ded) {
				g.drawImage(Img.DED, (int)R.cam.getX(x), (int)R.cam.getY(y), (int)R.cam.byDelta(w), (int)R.cam.byDelta(h), null);
			}else if(unit instanceof Moskal) {
				g.drawImage(Img.MOSKAL, (int)R.cam.getX(x), (int)R.cam.getY(y), (int)R.cam.byDelta(w), (int)R.cam.byDelta(h), null);
			}
			break;
		}
		if(Settings.showHitboxes) {
			unit.render(g, R.cam);
		}
	}
	
	@Override
	public void draw(Graphics g, Tile tile, int x, int y, int w, int h) {
		if(x>-10-w && x<this.getWidth()+10 && y>-10-h && y<this.getHeight()+10)
			switch(tile.getType()) {
			case 0:
				g.drawImage(grass, x, y, w, h, null);
				break;
			case 1:
				g.drawImage(wall, x, y, w, h, null);
				break;
			}
	}
	
}
