package ded.game.state;

import java.awt.Color;
import ded.DedAlesya;
import java.awt.Graphics;

import ded.R;
import ded.Img;

public class Menu implements State{

	//!!!!!!!!!!!
	private int index = 0;
	
	public void render(Graphics g) {
		g.drawImage(Img.MENU,0,0,DedAlesya.render.getWidth(),DedAlesya.render.getHeight(),null);
		g.setColor(Color.WHITE);
//		g.drawString("Play", 100, 200);
//		g.drawString("Settings", 100, 215);
//		g.drawString("Exit", 100, 230);
		g.drawPolygon(new int[]{DedAlesya.render.getWidth()/50-5, DedAlesya.render.getWidth()/50+5, DedAlesya.render.getWidth()/50-5}, new int[]{(DedAlesya.render.getHeight()/4+(index*(DedAlesya.render.getHeight()/12)))-10, DedAlesya.render.getHeight()/4 +(index*(DedAlesya.render.getHeight()/12)), (DedAlesya.render.getHeight()/4+(index*(DedAlesya.render.getHeight()/12)))+10}, 3);
	}
	
	public void tick() {
		if(R.in.ESC.isClicked()) {
			System.exit(1);
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
				R.state = 1;
				break;
			case 1:
				R.state = 3;
				break;
			case 2:
				System.exit(1);
				break;
			}
		}
	}
	
	public void superTick() {
		
	}
	
}
