package ded.game.state;

import java.awt.Color;
import java.awt.Graphics;

import ded.R;

public class Settings implements State{

	//!!!!!!!!!!!
	private int index = 0;
	
	public static boolean showHitboxes = false;
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Resolution: ", 100, 200);
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
		}else if(R.in.MOVE_UP.isClicked()) {
			index--;
		}else if(R.in.MOVE_RIGHT.isClicked() || R.in.ACCEPT.isClicked()) {
			switch(index) {
			case 0:
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
