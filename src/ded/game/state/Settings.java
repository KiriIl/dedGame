package ded.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import ded.DedAlesya;
import ded.Img;
import ded.R;

public class Settings implements State{

	//!!!!!!!!!!!
	private int index = 0;
	public static int voli=0;
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
		g.drawImage(Img.SETTINGS, 0, 0, DedAlesya.render.getWidth(), DedAlesya.render.getHeight(),null);
		g.setColor(Color.WHITE);
		g.drawString(getRezo(), DedAlesya.render.getWidth()/4, DedAlesya.render.getHeight()/4);
		g.drawString(""+showHitboxes, DedAlesya.render.getWidth()/4, DedAlesya.render.getHeight()/4 + DedAlesya.render.getHeight()/12);
		g.drawPolygon(new int[]{DedAlesya.render.getWidth()/50-5, DedAlesya.render.getWidth()/50+5, DedAlesya.render.getWidth()/50-5}, new int[]{(DedAlesya.render.getHeight()/4+(index*(DedAlesya.render.getHeight()/12)))-10, DedAlesya.render.getHeight()/4 +(index*(DedAlesya.render.getHeight()/12)), (DedAlesya.render.getHeight()/4+(index*(DedAlesya.render.getHeight()/12)))+10}, 3);
		for (int volx=0,voly=-5;volx<100;volx+=10,voly-=5) {
			g.drawRect(DedAlesya.render.getWidth()/4+volx, (DedAlesya.render.getHeight()/4+(2*(DedAlesya.render.getHeight()/12)))+25, 5, voly);
			if (voli*10<=volx) {
				g.setColor(Color.DARK_GRAY);
				g.fillRect(DedAlesya.render.getWidth()/4+volx, (DedAlesya.render.getHeight()/4+(2*(DedAlesya.render.getHeight()/12)))+25, 5+1, voly);
			}
		}
	}

	@Override
	public void tick() {
		if(R.in.ESC.isClicked()) {
			R.state = 0;
			return;
		}
		
		if(R.in.MOVE_DOWN.isClicked()) {
			index++;
			if(index>3) index=0;
		}else if(R.in.MOVE_UP.isClicked()) {
			index--;
			if(index<0) index=3;
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
				voli++;
				if (voli>10)
					voli=0;
				break;
			case 3:
				R.state = 0;
				break;
			}
		}
	}

	@Override
	public void superTick() {
		
	}
	
}
