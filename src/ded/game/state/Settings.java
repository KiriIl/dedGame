package ded.game.state;

import java.awt.Color;
import java.awt.Graphics;

import ded.DedAlesya;
import ded.R;

public class Settings implements State{

	//!!!!!!!!!!!
	private int index = 0;
	
	public static boolean showHitboxes = false;
	
	
	//!!!!!!!!!!!!
	
	/*
	 * 0-800x600
	 * 1-1024x768
	 * 2-1280x720
	 * 3-320x240
	 */
	
	public static int rezo = 0;
	
	private String getRezo() {
		switch(rezo) {
		case 0:
			return "800:600";
		case 1:
			return "1024:768";
		case 2:
			return "1280:720";
		case 3:
			return "640:480";
		}
		return "N/A";
	}
	
	private void setRezolution() {
		switch(rezo) {
		case 0:
			DedAlesya.render.setSize(800, 600);
			DedAlesya.render.setPreferredSize(DedAlesya.render.getSize());
			DedAlesya.gameFrameUpdate();
			R.cam.delta = 1.5f;
			break;
		case 1:
			DedAlesya.render.setSize(1024, 768);
			DedAlesya.render.setPreferredSize(DedAlesya.render.getSize());
			DedAlesya.gameFrameUpdate();
			R.cam.delta = 2f;
			break;
		case 2:
			DedAlesya.render.setSize(1280, 720);
			DedAlesya.render.setPreferredSize(DedAlesya.render.getSize());
			DedAlesya.gameFrameUpdate();
			R.cam.delta = 2.5f;
			break;
		case 3:
			DedAlesya.render.setSize(640, 480);
			DedAlesya.render.setPreferredSize(DedAlesya.render.getSize());
			DedAlesya.gameFrameUpdate();
			R.cam.delta = 1f;
			break;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Resolution: "+getRezo(), 100, 200);
		g.drawString("Show hitboxes: " + showHitboxes, 100, 215);
		g.drawString("Back", 100, 230);
		g.drawPolygon(new int[]{80, 85, 80}, new int[]{190+(index*15), 195+(index*15), 200+(index*15)}, 3);
	}

	@Override
	public void tick() {
		if(R.in.ESC.isClicked()) {
			R.state = 0;
			return;
		}
		
		if(R.in.MOVE_DOWN.isClicked()) {
			index++;
			if(index>2) index=0;
		}else if(R.in.MOVE_UP.isClicked()) {
			index--;
			if(index<0) index=2;
		}else if(R.in.MOVE_RIGHT.isClicked() || R.in.ACCEPT.isClicked()) {
			switch(index) {
			case 0:
				rezo++;
				if(rezo>3) rezo = 0;
				setRezolution();
				break;
			case 1:
				showHitboxes = !showHitboxes;
				break;
			case 2:
				R.state = 0;
				break;
			}
		}
	}

	@Override
	public void superTick() {
		
	}
	
}
