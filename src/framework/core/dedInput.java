package framework.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class dedInput implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	
	public static final int MOUSE_DOWN = 1;
	public static final int MOUSE_UP = 2;
	public static final int MOUSE_MOVED = 3;
	
	private int x, y;
	private int mouseAction;
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getMouseAction() {
		return mouseAction;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseAction = MOUSE_MOVED;
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseAction = MOUSE_DOWN;
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseAction = MOUSE_UP;
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	public class Key{
		private boolean pressed = false;
		private boolean clicked = false;
		
		public boolean isPressed(){
			return pressed;
		}
		
		public boolean isClicked(){
			boolean b = false;
			if(pressed & !clicked){
				clicked = true;
				b = true;
			}else if(pressed & clicked){
				return false;
			}
			return b;
		}
		
		public void toggle(boolean isPressed){
			pressed = isPressed;
			if(!pressed){
				clicked = false;
			}
		}
	}
	
	public void toggleKey(int keyCode, boolean isPressed){}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {}
	
	//public void mouseAction(String action, int x, int y){}
}
