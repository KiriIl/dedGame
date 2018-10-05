package ded;

import java.awt.Toolkit;

import javax.swing.JFrame;

import ded.game.Handler;
import ded.game.Render;
import framework.core.dedThread;

public class DedAlesya {

	private final String title = "Ded";
	private final String version = "v0.0.1";
	
	private JFrame gameFrame = new JFrame(title+" "+version);
	
	public static Render render;
	public static Handler handler;
	public static dedThread renderThread;
	public static dedThread updateThread;
	
	public void init() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		render = new Render();
		handler = new Handler();
		renderThread = new dedThread("RENDER_THREAD: ", render);
		renderThread.setPriority(8);
		
		updateThread = new dedThread("UPDATE_THREAD: ", handler);
		updateThread.setPriority(6);
		
		gameFrame.setResizable(false);
		gameFrame.add(render);
		gameFrame.pack();
		render.setFocusable(true);
		render.addKeyListener(R.in);
		gameFrame.setLocation(kit.getScreenSize().width/2-render.getWidth()/2, kit.getScreenSize().height/2-render.getHeight()/2);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		
		renderThread.start();
		updateThread.start();
	}
	
	public static void main(String[] args) {
		DedAlesya game = new DedAlesya();
		game.init();
	}
	
}
