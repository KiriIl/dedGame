package ded.game.state;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import ded.DedAlesya;
import ded.R;
import ded.game.effects.StartBattleEffect;
import ded.game.unit.Unit;
import framework.game.Effect;

public class Battle implements State{

	private float x = 0;
	
	public static Effect effect;
	private static Unit enemy;
	private static Unit player;
	
	//!!!!!!!!!!!
	private int index = 0;
	
	public static void start(Unit player, Unit enemy) {
		R.cam.x = 0;
		R.cam.y = 0;
		R.cam.delta = 1;
		Battle.player = player;
		Battle.enemy = enemy;
		effect = new StartBattleEffect(player, enemy);
	}
	
	public void render(Graphics g) {
		//Fill background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, DedAlesya.render.getWidth(), DedAlesya.render.getHeight());
		g.setColor(new Color(10, 50, 50, 100));
		g.fillOval((int)(enemy.inBattleX-20), 50+enemy.h*4-16, enemy.w*4+40, 30);
		DedAlesya.render.drawUnit(g, enemy, enemy.inBattleX, enemy.inBattleY, enemy.w*4, enemy.h*4);
		DedAlesya.render.drawUnit(g, player, player.inBattleX, player.inBattleY, player.w*8, player.h*8);
		if(effect.isEnded()) {
			g.setColor(Color.BLACK);
			g.drawString("HP: "+R.ded.HP, (int)player.inBattleX, 400);
			g.drawString("HP: "+enemy.HP, (int)enemy.inBattleX-100, 70);
			
			int i = 0;
			for(String skill: R.ded.getSkillList()) {
				g.drawString(skill, 510, 490+(i*15));
				i++;
			}
			g.drawPolygon(new int[]{490, 495, 490}, new int[]{480+(index*15), 485+(index*15), 490+(index*15)}, 3);
			
			g.setColor(Color.RED);
			g.fillRect((int)enemy.inBattleX+10, 40, (int)((float)enemy.HP/enemy.maxHP*(100)), 10);
			g.setColor(Color.GREEN);
			g.fillRect((int)player.inBattleX+190, 400, (int)((float)player.HP/player.maxHP*(100)), 10);
			g.setColor(Color.BLACK);
			g.drawRect((int)enemy.inBattleX+10, 40, 100, 10);
			g.drawRect((int)player.inBattleX+190,400,100,10);
		}
	}

	private boolean playerTurn = true;
	boolean picked = false;
	public void tick() {
		if(playerTurn) {
			picked = false;
			if(R.in.MOVE_DOWN.isClicked()) {
				index++;
				if(index>player.getSkillList().size()-1) index = 0;
			}else if(R.in.MOVE_UP.isClicked()) {
				index--;
				if(index<0) index = player.getSkillList().size()-1;
			}else if(R.in.MOVE_RIGHT.isClicked() || R.in.ACCEPT.isClicked()) {
				picked = true;
			}
		}
		
		if(picked) {
			if(playerTurn) {
				player.cast(R.ded.getSkillList().get(index), enemy);
				playerTurn = false;
			}else {
				enemy.cast(enemy.getSkillList().get(new Random().nextInt(enemy.getSkillList().size())), player);
				System.out.println("enemy");
				playerTurn = true;
			}
		}
		
	}
	
	public void superTick() {
		if(R.in.ESC.isClicked() || enemy.HP<=0) {
			R.state = 1;
			R.cam.delta = 2;
			enemy.HP = enemy.maxHP;
		}
		effect.tick();
	}
	
}
