package ded.game.skills;

import ded.Snds;
import ded.game.effects.SmallHealEffect;
import ded.game.state.Battle;
import ded.game.unit.Unit;
import framework.sound.Sound;

public class tsarap {
	public void cast(Unit caster, Unit target) {
		// дед наносит удар, снимающий и в теченни нескольких ходов(2-3) у врага отнимается 1 хп
		Snds.sndTsarap.snd();
		Snds.sndTsarap.setVolume();
	}
}
