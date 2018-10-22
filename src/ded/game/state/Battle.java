package ded.game.state;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import ded.DedAlesya;
import ded.R;
import ded.game.effects.StartBattleEffect;
import ded.game.unit.Unit;
import framework.game.Camera;
import framework.game.Effect;

public class Battle implements State{
	
	public static Effect effect;
	private static Unit enemy;
	private static Unit player;
	
	//!!!!!!!!!!!
	private int index = 0;
	private static Camera battleCamera = new Camera();
	
	public Battle() {
		
	}
	
	public static void start(Unit player, Unit enemy) {
		battleCamera.x = 0;
		battleCamera.y = 0;
		battleCamera.delta = (float)DedAlesya.render.getHeight()/480f;
		Battle.player = player;
		Battle.enemy = enemy;
		effect = new StartBattleEffect(player, enemy);
	}
	
	public void render(Graphics g) {
		//Fill background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, DedAlesya.render.getWidth(), DedAlesya.render.getHeight());
		g.setColor(new Color(10, 50, 50, 100));
		g.fillOval((int)battleCamera.getX(enemy.inBattleX-20), (int)battleCamera.getY(enemy.inBattleY+enemy.h*3-16), (int)battleCamera.byDelta(enemy.w*3+40), (int)battleCamera.byDelta(30));
		
		DedAlesya.render.drawUnit(g, enemy, enemy.inBattleX, enemy.inBattleY, enemy.w*3, enemy.h*3, battleCamera);
		DedAlesya.render.drawUnit(g, player, player.inBattleX, player.inBattleY, player.w*6, player.h*6, battleCamera);
		
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
			
			//HP Bars
			g.setColor(Color.RED);
			g.fillRect((int)battleCamera.getX(enemy.inBattleX+10), (int)battleCamera.getY(enemy.inBattleY-10), (int)(int)battleCamera.byDelta((float)enemy.HP/enemy.maxHP*(100)), (int)battleCamera.byDelta(10));
			g.setColor(Color.GREEN);
			g.fillRect((int)battleCamera.getX(player.inBattleX+190), (int)battleCamera.getY(player.inBattleY-10), (int)battleCamera.byDelta((float)player.HP/player.maxHP*(100)), (int)battleCamera.byDelta(10));
			g.setColor(Color.BLACK);
			g.drawRect((int)battleCamera.getX(enemy.inBattleX+10), (int)battleCamera.getY(enemy.inBattleY-10), (int)battleCamera.byDelta(100), (int)battleCamera.byDelta(10));
			g.drawRect((int)battleCamera.getX(player.inBattleX+190), (int)battleCamera.getY(player.inBattleY-10), (int)battleCamera.byDelta(100), (int)battleCamera.byDelta(10));
		}else effect.render(g, battleCamera);
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
				playerTurn = true;
			}
		}
		
	}
	
	public void superTick() {
		if(R.in.ESC.isClicked() || enemy.HP<=0) {
			R.state = 1;
			enemy.HP = enemy.maxHP;
			while(DedAlesya.updateThread.isLocked()) DedAlesya.updateThread.unlock();
		}
		effect.tick();
	}
	
}
