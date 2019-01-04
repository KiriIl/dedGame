package ded.game.skills;

import ded.Snds;
import ded.game.effects.TsarapEffect;
import ded.game.state.Battle;
import ded.game.state.Settings;
import ded.game.unit.Unit;

/**
 * @author Kirill
 *
 */
public class Tsarap implements Skill{
	public void cast(Unit caster, Unit target) {
		Battle.effect = new TsarapEffect(caster,target);
		target.HP -= 2;
//		Battle.bleedingCount =+ 2;
		if(Settings.volume>0) Snds.sndTsarap.play();
		// дед наносит удар, снимающий в теченни нескольких ходов(2-3) у врага 1 хп
	}
}
