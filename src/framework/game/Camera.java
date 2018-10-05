package framework.game;

public class Camera {

	public float x = 0, y = 0;
	public float delta = 1;
	
	public float getX(float x) {
		return (x*this.delta+this.x);
	}
	
	public float getY(float y) {
		return (y*this.delta+this.y);
	}
	
	public float byDelta(float arg) {
		return arg*delta;
	}
	
}