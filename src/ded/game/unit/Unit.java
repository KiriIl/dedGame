package ded.game.unit;

import framework.game.GameObject;

public abstract class Unit extends GameObject{
	
	public int HP = 0;
	
	public Unit(float x, float y) {
		super(x, y,0,0);
	}
	
}
