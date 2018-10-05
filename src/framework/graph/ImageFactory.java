package framework.graph;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class ImageFactory {

	//Load Image from path
	public static BufferedImage load(String path){
		BufferedImage loadedImg = null;
		try{
			loadedImg = ImageIO.read(ImageFactory.class.getResource(path));
		}catch (Exception e) {
			try {
				loadedImg = ImageIO.read(new File(path));
			}catch (Exception ee) {
				loadedImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
			}
		}
		return loadedImg;
	}
	
	public static BufferedImage crop(BufferedImage img, int x, int y, int w, int h){
		BufferedImage _img = null;
		try {
			_img = img.getSubimage(x, y, w, h);
		}catch(Exception e) {
			_img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		}
		return _img;
	}
	
}
