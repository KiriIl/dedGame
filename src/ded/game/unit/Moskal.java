package ded.game.unit;

import ded.game.skills.Punch;

public class Moskal extends Unit{

	public Moskal(float x, float y) {
		super(x, y, 20, 11);
		w = 32;
		h = 48;
		skill.put("Punch", new Punch());
	}
	
}
