package ded.game.effects;

import java.awt.Graphics;

import ded.DedAlesya;
import ded.R;
import ded.game.unit.Unit;
import framework.game.Effect;

public class StartBattleEffect extends Effect{
	
	private short ready = -1;
	
	private Unit enemy;
	private Unit player;
	
	public StartBattleEffect(Unit player, Unit enemy) {
		this.enemy = enemy;
		this.player = player;
		enemy.inBattleX = -enemy.w*3;
		player.inBattleX = -R.ded.w*6;
		enemy.inBattleY = 50;
		player.inBattleY = 300;
	}
	
	private void _tick() {
		if(enemy.inBattleX<410) {
			enemy.inBattleX+=5;
			if(enemy.inBattleX>410) {
				enemy.inBattleX = 410;
				ready++;
			}
		}
		if(player.inBattleX<20) {
			player.inBattleX+=4.4f;
			if(player.inBattleX>20) {
				player.inBattleX = 20;
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
