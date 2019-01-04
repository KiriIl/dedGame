package ded.game;

import ded.R;
import framework.core.dedRunnable;

public class Handler implements dedRunnable{
	
	@Override
	public void tick() {
		synchronized(R.unit) {
			switch(R.state) {
			case 0:
				R.menu.tick();
				break;
			case 1:
				R.world.tick();
				break;
			case 2:
				R.battle.tick();
				break;
			case 3:
				R.settings.tick();
				break;
			}
		}
	}
	
	public void superTick() {
		synchronized(R.unit) {
			switch(R.state) {
			case 0:
				R.menu.superTick();
				break;
			case 1:
				R.world.superTick();
				break;
			case 2:
				R.battle.superTick();
				break;
			case 3:
				R.settings.superTick();
				break;
			}
		}
	}
	
}
