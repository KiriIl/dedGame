package ded.game;

import framework.Const;
import framework.game.GameObject;
import framework.game.map.GameMap;

public abstract class Phys {

	public static final void collisionWithMap(GameObject obj, GameMap map, int dir) {
		try {
			//Map Bounds
			if(obj.x<map.x) obj.x = map.x;
			else if(obj.x+obj.w>map.x+map.w*map.tile[0].length) obj.x = map.x+map.w*map.tile[0].length - obj.w;
			if(obj.y<map.y) obj.y = map.y;
			else if(obj.y+obj.h>map.y+map.h*map.tile.length) obj.y = map.y+map.h*map.tile.length - obj.h;
			
			//Map tiles
			for (int i = (int) ((obj.y-map.y) / map.h); i <= (obj.y + obj.h-map.y) / map.h; i++) {
				for (int j = (int) ((obj.x-map.x) / map.w); j <= (obj.x + obj.w-map.x) / map.w; j++) {
					if(obj.getBounds().intersects(map.tile[i][j].getBounds()) && map.tile[i][j].isWalkable()) {
						if(dir == Const.X_DIRECTION) {
							if(obj.vx>0) {
								obj.x = map.tile[i][j].x-obj.w;
							} else if(obj.vx<0) {
								obj.x = map.tile[i][j].x+map.tile[i][j].w;
							}
						}else if(dir == Const.Y_DIRECTION) {
							if(obj.vy>0) {
								obj.y = map.tile[i][j].y-obj.h;
							} else if(obj.vy<0){
								obj.y = map.tile[i][j].y+map.tile[i][j].h;
							}
						}
					}
				}
			}
		}catch(Exception e){}
	}
	
	public static final void collision(GameObject obj1, GameObject obj2, int dir) {
		if(obj1.collision(obj2)) {
			if(dir==Const.X_DIRECTION) {
				if(obj1.vx>0) {
					obj1.x = obj2.x-obj1.w;
				} else if(obj1.vx<0) {
					obj1.x = obj2.x+obj2.w;
				}
				obj1.vx = 0;
			}else if(dir==Const.Y_DIRECTION) {
				if(obj1.vy>0) {
					obj1.y = obj2.y-obj1.h;
				} else if(obj1.vy<0){
					obj1.y = obj2.y+obj2.h;
				}
				obj1.vy = 0;
			}
		}
	}
	
}
