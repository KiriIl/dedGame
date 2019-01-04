package ded.game.effects;

import java.awt.Color;
import java.awt.Graphics;

import ded.DedAlesya;
import ded.game.unit.Unit;
import framework.game.Camera;
import framework.game.Effect;

/**
 * @author Kirill
 *
 */

public class HarchokEffect extends Effect {

	Unit caster;
	Unit enemy;
	
	private float startBollX;
	private float startBollrY;
	private float endBollX;
	private float endBollY;
	private int R=0;
	private boolean isEnemy;

	public HarchokEffect(Unit caster, Unit enemy)
	{
		if(caster.inBattleX>200) isEnemy = true;
		else isEnemy = false;
		this.caster = caster;
		this.enemy = enemy;
		if (!isEnemy) {
			startBollX = caster.inBattleX+150;
			startBollrY = caster.inBattleY+50;
			endBollX = enemy.inBattleX;
			endBollY = enemy.inBattleY+50;
		}
		else {
			startBollX = caster.inBattleX+40;
			startBollrY = caster.inBattleY+80;
			endBollX = enemy.inBattleX+100;
			endBollY = enemy.inBattleY+50;
		}
		DedAlesya.updateThread.lock();
	}
	@Override
	public void tick() {
		if (!isEnded()) {
			if (R<45)
				R+=2;
			else {
				if (!isEnemy) {					
					if(startBollX<endBollX) {
						startBollX+=6;
						startBollrY-=5.4f;
					}
					else {
						startBollX = endBollX;
						startBollrY = endBollY;
					}
				}
				else {
					if(startBollX>endBollX) {
						startBollX-=6;
						startBollrY+=5.4f;
					}
					else {
						startBollX = endBollX;
						startBollrY = endBollY;
					}
				}
				if (startBollX == endBollX && startBollrY == endBollY) {					
					stop();
					DedAlesya.updateThread.unlock();
				}
			}
		}
	}

	@Override
	public void render(Graphics g, Camera cam) {
		g.setColor(new Color(193,193,193));
		g.fillOval((int)cam.getX(startBollX), (int)cam.getY(startBollrY), (int)cam.byDelta(R), (int)cam.byDelta(R));

	}
}
