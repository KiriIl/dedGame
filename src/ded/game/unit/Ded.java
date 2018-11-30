package ded.game.unit;

import ded.game.skills.*;

public class Ded extends Unit{

	public Ded(float x, float y) {
		super(x, y, 30, 10);
		w = 64;
		h = 48;
		skill.put("Punch", new Punch());
		skill.put("Navernut' kartoshechki", new SmallHeal());
		skill.put("Harknut'", new harchok());
		skill.put("S vertuxi", new svertuhi());
		skill.put("Udarit' golovoy",new HeadPunch());
	}
	
}
