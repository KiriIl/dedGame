package ded.game;

import java.awt.event.KeyEvent;

import framework.core.dedInput;

public class Input extends dedInput{

	public Key MOVE_UP    = new Key();
	public Key MOVE_DOWN  = new Key();
	public Key MOVE_RIGHT = new Key();
	public Key MOVE_LEFT  = new Key();
	public Key ACCEPT = new Key();
	public Key ESC = new Key();
	
	@Override
	public void toggleKey(int keyCode, boolean isPressed) {
		switch(keyCode) {
		case KeyEvent.VK_ESCAPE:
			ESC.toggle(isPressed);
			break;
		case KeyEvent.VK_ENTER:
			ACCEPT.toggle(isPressed);
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			MOVE_UP.toggle(isPressed);
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			MOVE_DOWN.toggle(isPressed);
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			MOVE_LEFT.toggle(isPressed);
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			MOVE_RIGHT.toggle(isPressed);
			break;
		}
	}
	
}
