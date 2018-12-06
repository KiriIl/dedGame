package ded.game.skills;

import ded.game.unit.Unit;

/**
 * @author Kirill
 *
 */
public class SpeedSkill {
	public void cast(Unit caster, Unit skill) {
		caster.speed+=5;
		caster.maxHP-=2;
		// скилл обменивающий скорость на хп
	}
}
