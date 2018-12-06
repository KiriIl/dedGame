package ded.game.skills;

import ded.Snds;
import ded.game.unit.Unit;

/**
 * @author Kirill
 *
 */
public class Tsarap {
	public void cast(Unit caster, Unit target) {
		// дед наносит удар, снимающий в теченни нескольких ходов(2-3) у врага 1 хп
		Snds.sndTsarap.snd();
		Snds.sndTsarap.setVolume();
	}
}
