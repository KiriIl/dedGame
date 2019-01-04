package ded.game.effects;

import java.awt.Color;
import java.awt.Graphics;

import ded.DedAlesya;
import ded.game.unit.Unit;
import framework.game.Camera;
import framework.game.Effect;

public class BleedingEffect extends Effect {

	Unit target;
	float floor;
	float dropX;
	float dropY;

	public BleedingEffect(Unit target) {
		this.target = target;
		floor = target.inBattleY + 200;
		dropX = this.target.inBattleX+100;
		dropY = this.target.inBattleY+50;
		DedAlesya.updateThread.lock();
	}
	
	@Override
	public void tick() {
		if (dropY < floor) dropY += 2;
		else { 
			stop();
			DedAlesya.updateThread.unlock();
		}
	}

	@Override
	public void render(Graphics g, Camera cam) {
		g.setColor(Color.RED);
		g.drawLine((int)dropX, (int)dropY, (int)dropX, (int)dropY+10);
	}
	
}
