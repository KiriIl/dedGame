package ded.game.state;

import java.awt.Graphics;

import ded.DedAlesya;
import ded.R;
import ded.game.Phys;
import ded.game.unit.Unit;
import framework.Const;

public class World implements State{

	@Override
	public void render(Graphics g) {
		R.map.render(g, DedAlesya.render, R.cam);
		synchronized(R.unit) {
			for(Unit unit: R.unit) DedAlesya.render.drawUnit(g, unit, 0, 0, 0, 0);
		}
	}

	@Override
	public void tick() {
		synchronized(R.unit) {
			R.ded.vx = 0;
			R.ded.vy = 0;
			
			if(R.in.ESC.isClicked()) {
				R.state = 0;
			}
			
			if(R.in.MOVE_DOWN.isPressed()) {
				R.ded.vy = 2;
			}else if(R.in.MOVE_UP.isPressed()) {
				R.ded.vy = -2;
			}
			R.ded.move(Const.Y_DIRECTION);
			R.ded.tick();
			Phys.collisionWithMap(R.ded, R.map, Const.Y_DIRECTION);
			if(R.in.MOVE_LEFT.isPressed()) {
				R.ded.vx = -2;
			}else if(R.in.MOVE_RIGHT.isPressed()) {
				R.ded.vx = 2;
			}
			R.ded.move(Const.X_DIRECTION);
			R.ded.tick();
			Phys.collisionWithMap(R.ded, R.map, Const.X_DIRECTION);
			R.ded.tick();
			
			R.cam.x = -(R.ded.x*R.cam.delta-(DedAlesya.render.getWidth()/2-R.ded.w/2*R.cam.delta));
			R.cam.y = -(R.ded.y*R.cam.delta-(DedAlesya.render.getHeight()/2-R.ded.h/2*R.cam.delta));
			
			if(R.test.getDistance(R.ded, true)<=48) {
				Battle.start(R.test);
				R.ded.x = 100;
				R.ded.y = 50;
				R.state = 2;
				DedAlesya.updateThread.lock();
				
			}
		}
	}

	public void superTick() {
	}
	
}