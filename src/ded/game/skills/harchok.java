package ded.game.skills;

import ded.game.effects.SmallHealEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;

public class harchok {
	public void cast(Unit caster, Unit target) {
		switch (0+(int)(Math.random()*2))
		{
		case 1:  /* код попадания */ break;
		}
		target.HP-=1;
		// противник получает урон 1 хп и с вероятностью 50% ослепляется от харчка и пропускает ход
	}
}
