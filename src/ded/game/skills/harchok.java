package ded.game.skills;

import ded.game.state.Battle;
import ded.game.unit.Unit;
import ded.Snds;

public class harchok implements Skill{
	public void cast(Unit caster, Unit target) {
		switch (0+(int)(Math.random()*3))
		{
		case 1: Battle.setPropusk(); break;
		}
		Snds.sndHarchok.snd();
		Snds.sndHarchok.setVolume();
		target.HP-=1;
		// противник получает урон 1 хп и с вероятностью 50% ослепляется от харчка и пропускает ход
	}
}
