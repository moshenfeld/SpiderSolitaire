package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	static ImageLoader il = new ImageLoader();
	
	public static BufferedImage getImage(String file){
		BufferedImage img;
		try {
			img = ImageIO.read(il.getClass().getResource("/" + file));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return img;
	}
}