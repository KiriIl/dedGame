package ded.game.effects;

import java.awt.Graphics;

import ded.DedAlesya;
import ded.game.unit.Unit;
import framework.game.Camera;
import framework.game.Effect;

public class TsarapEffect extends Effect {

	Unit caster;
	Unit enemy;
	private float startCasterX;
	private float startCasterY;
	private boolean reverse;
	private int time;
	
	public TsarapEffect(Unit caster, Unit enemy) {
		this.caster = caster;
		this.enemy = enemy;
		startCasterX = caster.inBattleX;
		startCasterY = caster.inBattleY;
		DedAlesya.updateThread.lock();
		reverse = false;
		time = 20;
	}
	
	@Override
	public void tick() {
		if(reverse) {
			caster.inBattleX-=2;
			caster.inBattleY+=1.5f;
			if(caster.inBattleX<startCasterX | caster.inBattleY>startCasterY) {
				caster.inBattleX = startCasterX;
				caster.inBattleY = startCasterY;
			}
		} 
		else {
			caster.inBattleX+=2;
			caster.inBattleY-=1.5f;
		}
		if(time!=0) time--;
		else reverse = true;
		if(caster.inBattleX == startCasterX & caster.inBattleY == startCasterY) {
			stop();
			DedAlesya.updateThread.unlock();
		}
	}

	@Override
	public void render(Graphics g, Camera cam) {
		
	}

}
