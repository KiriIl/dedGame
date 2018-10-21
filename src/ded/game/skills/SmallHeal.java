package ded.game.skills;

import ded.game.unit.Unit;

public class SmallHeal implements Skill{
	
	public void cast(Unit caster, Unit skill) {
		caster.HP+=3;
		if(caster.maxHP<caster.HP) caster.HP = caster.maxHP;
	}
	
}
