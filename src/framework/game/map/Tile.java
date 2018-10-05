package framework.game.map;

import java.awt.Graphics;

import framework.game.AbstractObject;

public class Tile extends AbstractObject{
	
	private int type;
	protected int i = 0, j = 0;
	
	private boolean walkable = true;
	private boolean free = true;
	
	public Tile(int type) {
		this.type = type;
	}
	
	public boolean isFree() {
		return free;
	}
	
	public void occupyTile() {
		free = false;
	}
	
	public void freeTile() {
		free = true;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int I() {
		return i;
	}
	
	public int J() {
		return j;
	}
	
	public void render(Graphics g, TileDrawer drawer, int x, int y, int w, int h) {
		drawer.draw(g, this, x, y, w, h);
	}
	
	//FOR PATH FINIDING
	protected static final int MOVEMENT_COST = 10;
		
	private int g;
	private int heuristic;
	private Tile parent;
	
	protected void setG(Tile parent){
		g = (parent.getG() + MOVEMENT_COST);
	}
	
	protected int getG() {
		return g;
	}
	
	protected int calculateG(Tile parent) {
		return (parent.getG() + MOVEMENT_COST);
	}
	protected void setHeuristic(Tile goal){
		heuristic = (int)(Math.abs(x - goal.x) + Math.abs(y - goal.y)) * MOVEMENT_COST;
	}
	
	protected Tile getParent() {
		return parent;
	}
	protected void setParent(Tile parent) {
		this.parent = parent;
	}

	protected int getF() {
		return g + heuristic;
	}
	
}
