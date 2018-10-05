package framework.game;

import java.awt.Rectangle;

import framework.Const;
import framework.game.GameObject.Hitbox;

public class AbstractObject {
	
	public float x, y;
	public float vx, vy;
	public int w, h;
	
	public final Hitbox toHitbox(Camera cam) {
		return new Hitbox(getXByCam(cam), getYByCam(cam), (int)getWByCam(cam), (int)getHByCam(cam));
	}
	
	public float getDistance(AbstractObject obj, boolean byCenter) {
		if(byCenter) {
			return (float) Math.abs(Math.sqrt( Math.pow(((x+w/2)-(obj.x+obj.w/2)), 2)+Math.pow(((y+h/2)-(obj.y+obj.h/2)), 2)));
		}else {
			return (float) Math.abs(Math.sqrt( Math.pow((x-obj.x), 2)+Math.pow((y-obj.y), 2)));
		}
	}
	
	public void move(int dir) {
		if(dir == Const.X_DIRECTION) x+=vx;
		else if(dir == Const.Y_DIRECTION) y+=vy;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, w, h);
	}
	
	public Rectangle getBounds(Camera cam) {
		return new Rectangle((int)cam.getX(x), (int)cam.getY(y), (int)cam.byDelta(w), (int)cam.byDelta(h));
	}
	
	public boolean contains(float x, float y) {
		if(this.getBounds().contains(x, y)) {
			return true;
		}else return false;
	}
	
	public boolean contains(float x, float y, Camera cam) {
		if(this.getBounds(cam).contains(x, y)) {
			return true;
		}else return false;
	}
	
	public float getXByCam(Camera cam){
		return (x*cam.delta+cam.x);
	}
	
	public float getYByCam(Camera cam){
		return (y*cam.delta+cam.y);
	}
	
	public float getWByCam(Camera cam){
		return (w*cam.delta);
	}
	
	public float getHByCam(Camera cam){
		return (h*cam.delta);
	}
	
	public float getCenterX() {
		return x+w/2;
	}
	
	public float getCenterY() {
		return y+h/2;
	}
	
	public float getCenterXByCam(Camera cam) {
		return getXByCam(cam)+getWByCam(cam)/2;
	}
	
	public float getCenterYByCam(Camera cam) {
		return getYByCam(cam)+getHByCam(cam)/2;
	}
	
}
