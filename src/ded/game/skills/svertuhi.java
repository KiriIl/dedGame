package ded.game.skills;

import ded.game.effects.SmallHealEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;

public class svertuhi {
	public void cast(Unit caster, Unit target) {
		switch (0+(int)(Math.random()*4))
		{
		case 3: target.HP-=7; /* код попадания */ break;
		default: /* код промаха */ break;
		}
		// дед делает вертуху с шансом попадания по цели 25%
	}
}
