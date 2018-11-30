package ded.game.skills;

import ded.Snds;
import ded.game.effects.PunchEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;

public class HeadPunch implements Skill{
	public void cast(Unit caster, Unit target) {
		Battle.effect = new PunchEffect(caster, target);
//		Sound.playSound("D://headpunch.wav");
		Snds.sndHeadPunch.snd();
		Snds.sndHeadPunch.setVolume();
		target.HP-=5;
		caster.HP-=2;
		// удар в голову наносит врагу 5 хп и себе 2 хп
	}
}
