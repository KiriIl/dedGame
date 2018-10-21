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
	
	//!!!!!!!!!!!!
	private static int enemyMaxHP;
	private static int dedMaxHP;
	
	//!!!!!!!!!!!
	private int index = 0;
	
	public static void start(Unit enemy) {
		R.cam.x = 0;
		R.cam.y = 0;
		R.cam.delta = 1;
		Battle.enemy = enemy;
		enemyMaxHP = enemy.HP;
		dedMaxHP = R.ded.HP;
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
		if(effect.isEnded()) {
			g.setColor(Color.BLACK);
			g.drawString("HP: "+R.ded.HP, (int)effect.dedX, 400);
			g.drawString("HP: "+enemy.HP, (int)effect.enemyX-100, 70);
			
			g.drawString("TEST TEST TEST", 510, 490);
			g.drawPolygon(new int[]{490, 495, 490}, new int[]{480+(index*15), 485+(index*15), 490+(index*15)}, 3);
			
			g.drawRect((int)effect.enemyX+10, 40, 100, 10);
			g.drawRect((int)effect.dedX+190,400,100,10);
			g.setColor(Color.RED);
			g.fillRect((int)effect.enemyX+11, 41, (int)((float)enemy.HP/enemyMaxHP*(99)), 9);
			g.setColor(Color.GREEN);
			g.fillRect((int)effect.dedX+191, 401, (int)((float)R.ded.HP/dedMaxHP*(99)), 9);
			// формула - текущее хп/фулл хп * 100 аыпвпавпа
		}
	}
	
	public void tick() {
		if(R.in.ESC.isClicked() || enemy.HP<=0) {
			R.state = 1;
			R.cam.delta = 2;
			enemy.HP = enemyMaxHP;
		}
		
		if(R.in.MOVE_DOWN.isClicked()) {
			index++;
		}else if(R.in.MOVE_UP.isClicked()) {
			index--;
		}else if(R.in.MOVE_RIGHT.isClicked() || R.in.ACCEPT.isClicked()) {
			switch(index) {
			case 0:
				enemy.HP--;
				break;
			case 1:
				break;
			case 2:
				R.state = 0;
				break;
			}
		}
	}
	public void superTick() {
		effect.tick();
	}
	
}
