package ded.game.skills;

import ded.Snds;
import ded.game.effects.PunchEffect;
import ded.game.state.Battle;
import ded.game.state.Settings;
import ded.game.unit.Unit;
public class Punch implements Skill{

	@Override
	public void cast(Unit caster, Unit target) {	
		Battle.effect = new PunchEffect(caster, target);
		if(Settings.volume>0) Snds.sndPunch.play();
		target.HP-=2;
	}

}
