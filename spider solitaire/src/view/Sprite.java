package view;

import java.awt.image.BufferedImage;

public class Sprite {

	private BufferedImage image;
	
	public Sprite(){
		image = null;
	}
	
	public Sprite(BufferedImage image){
		this.image = image;
	}
	
	public Sprite(String file){
		this.image = ImageLoader.getImage(file);
	}
	
	public BufferedImage getImage(){
		return this.image;
	}
}