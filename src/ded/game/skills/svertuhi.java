package ded.game.skills;

import ded.Snds;
import ded.game.state.Battle;
import ded.game.unit.Unit;
import framework.sound.Sound;

public class svertuhi implements Skill{
	public void cast(Unit caster, Unit target) {
		switch (0+(int)(Math.random()*4))
		{
		case 3: 
			Snds.sndPunch.snd();
			Snds.sndPunch.setVolume();
			target.HP-=7; 
			break;
		default: break;
		}
		// дед делает вертуху с шансом попадания по цели 25%
	}
}
