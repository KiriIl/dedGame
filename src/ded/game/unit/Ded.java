package ded.game.unit;

import ded.game.skills.HeadPunch;
import ded.game.skills.Punch;
import ded.game.skills.SmallHeal;
import ded.game.skills.Harchok;
import ded.game.skills.Svertuhi;

public class Ded extends Unit{

	public Ded(float x, float y) {
		super(x, y, 30, 10);
		w = 64;
		h = 48;
		skill.put("Punch", new Punch());
		skill.put("Navernut' kartoshechki", new SmallHeal());
		skill.put("Harknut'", new Harchok());
		skill.put("S vertuxi", new Svertuhi());
		skill.put("Udarit' golovoy",new HeadPunch());
	}
	
}
