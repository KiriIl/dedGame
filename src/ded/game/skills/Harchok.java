package ded.game.skills;

import ded.Snds;
import ded.game.effects.HarchokEffect;
import ded.game.state.Battle;
import ded.game.state.Settings;
import ded.game.unit.Unit;

/**
 * @author Kirill
 *
 */
public class Harchok implements Skill{
	public void cast(Unit caster, Unit target) {
		if (0+(int)(Math.random()*2)==0)
			target.propusk = true;
		Battle.effect = new HarchokEffect(caster,target);
		if(Settings.volume>0) Snds.sndHarchok.play();
		target.HP-=1;
		// противник получает урон 1 хп и с вероятностью 50% ослепляется от харчка и пропускает ход
	}
}
