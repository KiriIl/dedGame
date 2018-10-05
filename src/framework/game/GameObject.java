package framework.game;

import java.awt.Color;
import java.awt.Graphics;

public class GameObject extends AbstractObject{
	
	protected Hitbox[] hitbox = null;
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public GameObject(float x, float y, int w, int h){
		hitbox = new Hitbox[1];
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		hitbox[0] = new Hitbox(x, y, w, h);
		updateHitboxPosition();
		hitbox[0].updateCenter();
	}
	
	public void updateHitboxPosition(){
		for(int i=0;i<hitbox.length;i++){
			hitbox[i]._x = this.x - hitbox[i].x;
			hitbox[i]._y = this.y - hitbox[i].y;
			hitbox[i].updateCenter();
		}
	}
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param hitbox
	 */
	public GameObject(float x, float y, int w, int h, Hitbox[] hitbox){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		setHitboxes(hitbox);
		updateHitboxPosition();
	}
	
	public void tick(){
		for(int i=0;i<hitbox.length;i++){
			hitbox[i].x = (x-hitbox[i]._x)+hitbox[i].dx;
			hitbox[i].y = (y-hitbox[i]._y)+hitbox[i].dy;
			hitbox[i].x = (hitbox[i].x);
			hitbox[i].y = (hitbox[i].y);
			hitbox[i].updateCenter();
		}
	}
	
	public void move(double vx, double vy) {
		this.x+=vx;
		this.y+=vy;
	}
	
	/**
	 * @param a - center X
	 * @param b - center Y
	 * @param R - radius
	 * @param angle
	 */
	public void moveByAngle(float a, float b, float R, float angle) {
		x = (float) (a+(R*Math.cos(Math.toRadians(angle))));
		y = (float) (b+(R*Math.sin(Math.toRadians(angle))));
	}
	
	/**
	 * @param a - center X
	 * @param b - center Y
	 * @param R - radius
	 * @param angle
	 */
	public void moveByAngleX(float a, double b, float R, float angle) {
		x = (float) (a+(R*Math.cos(Math.toRadians(angle))));
	}
	
	/**
	 * @param a - center X
	 * @param b - center Y
	 * @param R - radius
	 * @param angle
	 */
	public void moveByAngleY(double a, double b, double R, double angle) {
		y = (float) (b+(R*Math.sin(Math.toRadians(angle))));
	}
	
	public void posByHitbox(int i){
		try{
			x = (float) (hitbox[i].x+hitbox[i]._x-hitbox[i].dx);
			y = (float) (hitbox[i].y+hitbox[i]._y-hitbox[i].dy);
		}catch(Exception e){}
	}
	
	public void posByHitboxX(int i){
		try{
			x = (float) (hitbox[i].x+hitbox[i]._x-hitbox[i].dx);
		}catch(Exception e){}
	}
	
	public void posByHitboxY(int i){
		try{
			y = (float) (hitbox[i].y+hitbox[i]._y-hitbox[i].dy);
		}catch(Exception e){}
	}
	
	/**
	 * @param g
	 */
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, w, h);
		g.setColor(Color.CYAN);
		for(int i=0;i<hitbox.length;i++)
		g.drawRect((int)Math.round(hitbox[i].x), (int)Math.round(hitbox[i].y), hitbox[i].w, hitbox[i].h);
	}
	
	/**
	 * @param g
	 * @param cam
	 */
	public void render(Graphics g, Camera cam){
		g.setColor(Color.RED);
		g.drawRect((int)Math.round(x*cam.delta+cam.x), (int)Math.round(y*cam.delta+cam.y), (int)Math.round(w*cam.delta), (int)Math.round(h*cam.delta));
		for(int i=0;i<hitbox.length;i++){
			g.drawRect((int)hitbox[i].getXByCam(cam), (int)hitbox[i].getYByCam(cam), (int)hitbox[i].getWByCam(cam), (int)(int)hitbox[i].getHByCam(cam));
		}
	}
	
	public Hitbox getHitbox(int i){
		try{
			return hitbox[i];
		}catch(Exception e){
			return null;
		}
	}
	
	public void setHitboxes(Hitbox[] hitbox){
		this.hitbox = new Hitbox[hitbox.length];
		for(int i=0;i<hitbox.length;i++){
			this.hitbox[i] = new Hitbox(x+hitbox[i].x, y+hitbox[i].y, hitbox[i].w, hitbox[i].h);
		}
	}
	
	public void scale(double d) {
		this.w*=d;
		this.h*=d;
		for(int i=0;i<hitbox.length;i++) {
			hitbox[i]._x*=d;
			hitbox[i]._y*=d;
			hitbox[i].w*=d;
			hitbox[i].h*=d;
		}
	}
	
	public void scale(double dW, double dH) {
		this.w*=dW;
		this.h*=dH;
		for(int i=0;i<hitbox.length;i++) {
			hitbox[i]._x*=dW;
			hitbox[i]._y*=dH;
			hitbox[i].w*=dW;
			hitbox[i].h*=dH;
		}
	}
	
	public boolean collision(GameObject obj){
		boolean b = false;
		for(int i=0;i<hitbox.length;i++){
			for(int j=0;j<obj.hitbox.length;j++){
				if(hitbox[i].collision(obj.hitbox[j])) b = true;
			}
			if(b) break;
		}
		return b;
	}
	
	public static class Hitbox extends AbstractObject{
		public float _x, _y;
		public float cx, cy;
		public float dx = 0, dy = 0;
		
		public Hitbox(float x, float y, int w, int h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		public void updateCenter(){
			cx = x+w/2;
			cy =y+h/2;
		}
		
		public boolean collision(Hitbox hitbox) {
			if(this.getBounds().intersects(hitbox.getBounds())) return true;
			else return false;
		}
		
	}
	
}
