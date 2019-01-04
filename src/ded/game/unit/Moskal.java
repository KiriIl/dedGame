package ded.game.unit;

import ded.game.skills.Harchok;
import ded.game.skills.Punch;

public class Moskal extends Unit{

	public Moskal(float x, float y) {
		super(x, y, 12, 11);
		w = 32;
		h = 48;
		skill.put("Punch", new Punch());
		skill.put("Harchok", new Harchok());
	}
	
}
