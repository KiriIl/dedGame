package ded.game.skills;

import ded.Snds;
import ded.game.state.Battle;
import ded.game.unit.Unit;

/**
 * @author Kirill
 *
 */
public class Harchok implements Skill{
	public void cast(Unit caster, Unit target) {
		if (0+(int)(Math.random()*2)==0)
			Battle.setPropusk();
		Snds.sndHarchok.snd();
		Snds.sndHarchok.setVolume();
		target.HP-=1;
		// противник получает урон 1 хп и с вероятностью 50% ослепляется от харчка и пропускает ход
	}
}
