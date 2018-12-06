package ded.game.skills;

import ded.Snds;
import ded.game.unit.Unit;

/**
 * @author Kirill
 *
 */
public class Svertuhi implements Skill{
	public void cast(Unit caster, Unit target) {
		if (0+(int)(Math.random()*4) == 0)
		{
			Snds.sndPunch.snd();
			Snds.sndPunch.setVolume();
			target.HP-=7;			
		}
		// дед делает вертуху с шансом попадания по цели 25%
	}
}
