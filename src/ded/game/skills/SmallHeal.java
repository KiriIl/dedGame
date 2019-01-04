package ded.game.skills;

import ded.Snds;
import ded.game.effects.SmallHealEffect;
import ded.game.state.Battle;
import ded.game.state.Settings;
import ded.game.unit.Unit;

public class SmallHeal implements Skill{
	
	public void cast(Unit caster, Unit skill) {
		Battle.effect = new SmallHealEffect(caster);
		if(Settings.volume>0) Snds.sndEating.play();
		caster.HP+=3;
		if(caster.maxHP<caster.HP) caster.HP = caster.maxHP;
	}
	
}
