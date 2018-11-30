package ded.game.skills;

import ded.Snds;
import ded.game.effects.PunchEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;
import framework.sound.Sound;
public class Punch implements Skill{

	@Override
	public void cast(Unit caster, Unit target) {
		Battle.effect = new PunchEffect(caster, target);
		Snds.sndPunch.snd();
		Snds.sndPunch.setVolume();
		target.HP-=2;
	}

}
