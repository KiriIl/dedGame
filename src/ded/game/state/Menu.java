package ded.game.state;

import java.awt.Color;
import java.awt.Graphics;

import ded.R;

public class Menu implements State{

	//!!!!!!!!!!!
	private int index = 0;
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Play", 100, 200);
		g.drawString("Settings", 100, 215);
		g.drawString("Exit", 100, 230);
		g.drawPolygon(new int[]{80, 85, 80}, new int[]{190+(index*15), 195+(index*15), 200+(index*15)}, 3);
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
