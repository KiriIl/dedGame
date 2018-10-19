package ded;

import java.util.ArrayList;

import ded.game.Input;
import ded.game.state.Battle;
import ded.game.state.Menu;
import ded.game.state.Settings;
import ded.game.state.World;
import ded.game.unit.Ded;
import ded.game.unit.Moskal;
import ded.game.unit.Unit;
import framework.game.Camera;
import framework.game.map.GameMap;

public abstract class R {
	
	/*
	 0 - menu
	 1 - world
	 2 - battle
	 */
	public static int state = 0;
	public static Menu menu = new Menu();
	public static World world = new World();
	public static Battle battle = new Battle();
	public static Settings settings = new Settings();
	
	public static Camera cam = new Camera();
	
	public static Input in = new Input();
	
	public static Ded ded = new Ded(0, 0);
	public static Moskal test = new Moskal(100, 260);
	public static GameMap map;
	
	public static ArrayList<Unit> unit = new ArrayList<Unit>();
	
	static {
		unit.add(ded);
		unit.add(test);
		map = new GameMap(new int[][] {
			{0,0,0,0,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0,0,1,1,1,0},
			{0,0,0,0,1,0,1,0,0,0,0,0,1,0},
			{1,0,0,1,1,0,1,0,0,0,1,1,1,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,1,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,0,0},
			{1,1,1,1,1,1,1,0,0,1,0,0,0,0},
		}, 64, 64);
		for(int i=0;i<map.tile.length;i++) {
			for(int j=0;j<map.tile[i].length;j++) {
				if(map.tile[i][j].getType()==0) {
					map.tile[i][j].setWalkable(false);
				}
			}
		}
		cam.delta = 2;
	}
}
