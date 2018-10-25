package ded.game.skills;

import ded.game.effects.SmallHealEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;

public class speedskill {
	public void cast(Unit caster, Unit skill) {
		caster.speed+=5;
		caster.maxHP-=2;
		// скилл обменивающий скорость на хп
	}
}
