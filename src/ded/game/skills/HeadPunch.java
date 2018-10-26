package ded.game.skills;

import ded.game.effects.SmallHealEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;

public class HeadPunch {
	public void cast(Unit caster, Unit target) {
		target.HP-=5;
		caster.HP-=2;
		// удар в голову наносит врагу 5 хп и себе 2 хп
	}
}
