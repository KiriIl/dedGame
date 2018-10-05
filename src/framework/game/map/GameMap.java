package framework.game.map;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import framework.game.AbstractObject;
import framework.game.Camera;

public class GameMap extends AbstractObject{
	
	public Tile tile[][];
	public float x, y;
	public int w, h;
	
	public GameMap(int[][] type, int w, int h) {
		x = 0;
		y = 0;
		this.w = w;
		this.h = h;
		tile = new Tile[type.length][type[0].length];
		for(int i=0;i<tile.length;i++) {
			for(int j=0;j<tile[0].length;j++) {
				tile[i][j] = new Tile(type[i][j]);
				tile[i][j].i = i;
				tile[i][j].j = j;
				tile[i][j].x = x+(w*j);
				tile[i][j].y = y+(h*i);
				tile[i][j].w = w;
				tile[i][j].h = h;
			}
		}
	}
	
	public void setCoords(float x, float y) {
		this.x = x;
		this.y = y;
		for(int i=0;i<tile.length;i++)
		for(int j=0;j<tile[0].length;j++) {
			tile[i][j].x = x+(w*j);
			tile[i][j].y = y+(h*i);
		}
	}
	
	public int getWidth() {
		return w*tile[0].length;
	}
	
	public int getHeight() {
		return h*tile.length;
	}
	
	public int getWidth(Camera cam) {
		return (int)((w*cam.delta)*tile[0].length);
	}
	
	public int getHeight(Camera cam) {
		return (int)((h*cam.delta)*tile.length);
	}
	
	public void render(Graphics g, TileDrawer drawer) {
		for(int i=0;i<tile.length;i++) {
			for(int j=0;j<tile[0].length;j++) {
				tile[i][j].render(g, drawer, (int)(x+(w*j)), (int)(y+(h*i)), w, h);
			}
		}
	}
	
	public Tile getTileByCords(double x, double y) {
		try {
			return tile[(int)(this.y+y)/h][(int)(this.x+x)/w];
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Tile getTileByCords(double x, double y, Camera cam) {
		try {
			double mcx = (this.x*cam.delta)+cam.x;
			double mcy = (this.y*cam.delta)+cam.y;
			return tile[(int)((this.y+(y-mcy))/(h*cam.delta))][(int)((this.x+(x-mcx))/(w*cam.delta))];
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void render(Graphics g, TileDrawer drawer, Camera cam) {
		for(int i=0;i<tile.length;i++) {
			for(int j=0;j<tile[0].length;j++) {
				tile[i][j].render(g, drawer, (int)Math.round((x+(w*j))*cam.delta+cam.x), (int)Math.round((y+(h*i))*cam.delta+cam.y), (int)Math.round(w*cam.delta), (int)Math.round(h*cam.delta));
			}
		}
	}
	
	//////////////////////////////////////
	
	public final List<Tile> findPath(Tile start, Tile end){
		if(start == end) {
			return new LinkedList<Tile>();
		}
		
		List<Tile> vList = new LinkedList<Tile>();
		List<Tile> nvList = new LinkedList<Tile>();
		
		vList.add(start);
		while(true) {
			Tile current = getCheapestTile(vList);
			vList.remove(current);
			nvList.add(current);
			
			if(current.I() == end.I() && current.J() == end.J()) {
				/*if(antiAliasing) {
					List<Tile> l = calcPath(start, current);
					List<Tile> delete = new LinkedList<Tile>();
					for(int i=0;i<l.size();i++) {
						if(i+2<l.size()) {
							if( l.get(i).x!=l.get(i+2).x && l.get(i).y!=l.get(i+2).y) {
								int di = l.get(i+2).I()-l.get(i).I();
								int dj = l.get(i+2).J()-l.get(i).J();
								if( tile[l.get(i).I()+di][l.get(i).J()].isWalkable() && tile[l.get(i).I()][l.get(i).J()+dj].isWalkable()) {
									delete.add(l.get(i+1));
								}
							}
						}
					}
					for(int i=0;i<delete.size();i++) {
						for(int j=0;j<l.size();j++) {
							if(l.get(j).equals(delete.get(i))) {
								l.remove(j);
								break;
							}
						}
					}
					return l;
				}*/
				return calcPath(start, current);
			}
			
			List<Tile> adjacentNodes = getAdjacent(current, nvList);
			
			for (Tile adjacent : adjacentNodes) {
				if (!vList.contains(adjacent)) {
					adjacent.setParent(current);
					adjacent.setHeuristic(tile[end.I()][end.J()]);
					adjacent.setG(current);
					vList.add(adjacent);
				}else if (adjacent.getG() > adjacent.calculateG(current)){
					adjacent.setParent(current);
					adjacent.setG(current);
				}
			}

			if (vList.isEmpty())
			{
				return new LinkedList<Tile>();
			}
			
		}
	}
	
	private List<Tile> calcPath(Tile start, Tile goal) {
		LinkedList<Tile> path = new LinkedList<Tile>();

		Tile node = goal;
		boolean done = false;
		while (!done){
			path.addFirst(node);
			node = node.getParent();
			if (node.equals(start)){
				path.addFirst(node);
				done = true;
			}
		}
		return path;
	}
	
	private Tile getCheapestTile(List<Tile> list)
	{
		Tile cheapest = list.get(0);
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getF() < cheapest.getF())
			{
				cheapest = list.get(i);
			}
		}
		return cheapest;
	}
	
	private List<Tile> getAdjacent(Tile tile, List<Tile> nvList)
	{
		List<Tile> adjacentNodes = new LinkedList<Tile>();

		Tile adjacent;

		// Check left
		if (tile.J() > 0) {
			try {
				adjacent = this.tile[tile.I()][tile.J()-1];
				if (adjacent != null && adjacent.isWalkable() && !nvList.contains(adjacent))
					adjacentNodes.add(adjacent);
			}catch(Exception e) {}
		}

		// Check right node
		if (tile.J() < this.tile[0].length) {
			try {
				adjacent = this.tile[tile.I()][tile.J()+1];
				if (adjacent != null && adjacent.isWalkable() && !nvList.contains(adjacent)){
					adjacentNodes.add(adjacent);
			}
			}catch(Exception e) {}
		}

		// Check top
		if (tile.I() > 0) {
			try {
				adjacent = this.tile[tile.I()-1][tile.J()];
				if (adjacent != null && adjacent.isWalkable() && !nvList.contains(adjacent))
					adjacentNodes.add(adjacent);
			}catch(Exception e) {}
		}

		// Check bottom
		if (tile.I() < this.tile.length) {
			try {
				adjacent = this.tile[tile.I()+1][tile.J()];
				if (adjacent != null && adjacent.isWalkable() && !nvList.contains(adjacent))
					adjacentNodes.add(adjacent);
			}catch(Exception e) {}
		}
		return adjacentNodes;
	}
	
}
