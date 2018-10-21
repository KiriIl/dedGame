package ded.game.effects;

import java.awt.Graphics;

import ded.DedAlesya;
import ded.game.unit.Unit;
import framework.game.Effect;

public class PunchEffect extends Effect{

	private boolean isEnemy = false;
	
	Unit caster;
	Unit enemy;
	
	private float startCasterX;
	private float startCasterY;
	
	public PunchEffect(Unit caster, Unit enemy) {
		this.caster = caster;
		this.enemy = enemy;
		startCasterX = caster.inBattleX;
		startCasterY = caster.inBattleY;
		if(caster.inBattleY>300) isEnemy = false;
		else isEnemy = true;
		
		DedAlesya.updateThread.lock();
	}
	
	private boolean reverse = false;
	
	int time = 20;
	
	@Override
	public void tick() {
		if(!this.isEnded()) {
			if(isEnemy) {
				if(reverse) {
					caster.inBattleX+=2;
					caster.inBattleY-=1.5f;
					if(caster.inBattleX>startCasterX | caster.inBattleY<startCasterY) {
						caster.inBattleX = startCasterX;
						caster.inBattleY = startCasterY;
					}
				}else {
					caster.inBattleX-=2;
					caster.inBattleY+=1.5f;
				}
			}else {
				if(reverse) {
					caster.inBattleX-=2;
					caster.inBattleY+=1.5f;
					if(caster.inBattleX<startCasterX | caster.inBattleY>startCasterY) {
						caster.inBattleX = startCasterX;
						caster.inBattleY = startCasterY;
					}
				}else {
					caster.inBattleX+=2;
					caster.inBattleY-=1.5f;
				}
			}
			if(time!=0) time--;
			else reverse = true;
			
			if(caster.inBattleX == startCasterX & caster.inBattleY == startCasterY) {
				stop();
				DedAlesya.updateThread.unlock();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
	}

	
	
}
