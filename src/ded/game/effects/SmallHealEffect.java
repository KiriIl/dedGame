package ded.game.effects;

import java.awt.Color;
import java.awt.Graphics;

import ded.DedAlesya;
import ded.game.unit.Unit;
import framework.game.Camera;
import framework.game.Effect;

public class SmallHealEffect extends Effect{
	private Unit caster;
	private int R = 0;
	
	public SmallHealEffect(Unit caster) {
		this.caster = caster;
		DedAlesya.updateThread.lock();
	}
	
	boolean reverse = false;
	
	@Override
	public void tick() {
		if(!isEnded()) {
			if(!reverse) R++;
			else R--;
			if(R>40) reverse = true;
			if(R<=0) {
				stop();
				DedAlesya.updateThread.unlock();
			}
		}
	}

	@Override
	public void render(Graphics g, Camera cam) {
		g.setColor(new Color(10, 220, 10, 170));
		g.fillOval((int)cam.getX(caster.inBattleX), (int)cam.getY(caster.inBattleY), (int)cam.byDelta(R), (int)cam.byDelta(R));
		g.setColor(Color.GREEN);
		g.drawOval((int)cam.getX(caster.inBattleX), (int)cam.getY(caster.inBattleY), (int)cam.byDelta(R), (int)cam.byDelta(R));
	}

}