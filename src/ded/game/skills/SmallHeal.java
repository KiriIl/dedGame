package ded.game.skills;

import ded.Snds;
import ded.game.effects.SmallHealEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;
import framework.sound.Sound;

public class SmallHeal implements Skill{
	
	public void cast(Unit caster, Unit skill) {
		Battle.effect = new SmallHealEffect(caster);
		Snds.sndEating.snd();
		Snds.sndEating.setVolume();
		caster.HP+=3;
		if(caster.maxHP<caster.HP) caster.HP = caster.maxHP;
	}
	
}
