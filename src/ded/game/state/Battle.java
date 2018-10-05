package ded.game.state;

import java.awt.Color;
import java.awt.Graphics;

import ded.DedAlesya;
import ded.R;
import ded.game.effects.StartBattleEffect;
import ded.game.unit.Unit;

public class Battle implements State{

	private float x = 0;
	
	private static StartBattleEffect effect;
	private static Unit enemy;
	
	public static void start(Unit enemy) {
		R.cam.x = 0;
		R.cam.y = 0;
		R.cam.delta = 1;
		Battle.enemy = enemy;
		effect = new StartBattleEffect(enemy);
	}
	
	public void render(Graphics g) {
		//Fill background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, DedAlesya.render.getWidth(), DedAlesya.render.getHeight());
		
		g.setColor(new Color(10, 50, 50, 100));
		g.fillOval((int)(effect.enemyX-20), 50+enemy.h*4-16, enemy.w*4+40, 30);
		DedAlesya.render.drawUnit(g, enemy, effect.enemyX, 50, enemy.w*4, enemy.h*4);
		DedAlesya.render.drawUnit(g, R.ded, effect.dedX, 400, R.ded.w*8, R.ded.h*8);
	}
	
	public void tick() {
		if(R.in.ESC.isClicked()) {
			R.state = 1;
			R.cam.delta = 2;
		}
	}
	
	public void superTick() {
		effect.tick();
	}
	
}
