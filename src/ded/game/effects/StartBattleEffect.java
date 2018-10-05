package ded.game.effects;

import java.awt.Graphics;

import ded.DedAlesya;
import ded.R;
import ded.game.unit.Unit;
import framework.game.Effect;

public class StartBattleEffect extends Effect{

	public float enemyX = 0;
	public float dedX = 0;
	
	private short ready = -1;
	
	public StartBattleEffect(Unit enemy) {
		enemyX = -enemy.w*4;
		dedX = -R.ded.w*8;
	}
	
	private void _tick() {
		if(enemyX<450) {
			enemyX+=5;
			if(enemyX>450) {
				enemyX = 450;
				ready++;
			}
		}
		if(dedX<10) {
			dedX+=4f;
			if(dedX>10) {
				dedX = 10;
				ready++;
			}
		}
	}
	
	@Override
	public void tick() {
		if(!isEnded()) {
			if(R.in.ACCEPT.isClicked()) {
				while(!(ready==1)) {
					_tick();
				}
			}else _tick();
			if(ready==1) {
				DedAlesya.updateThread.unlock();
				stop();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
	}
	
}
