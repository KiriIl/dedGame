package ded.game.skills;

import ded.game.effects.PunchEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;

public class Punch implements Skill{

	@Override
	public void cast(Unit caster, Unit target) {
		Battle.effect = new PunchEffect(caster, target);
		target.HP-=2;
	}

}
