package view;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private Sprite[][] sprites;
	
	public SpriteSheet(BufferedImage image, int width, int height){
		int numX = image.getWidth() / width;
		int numY = image.getHeight() / height;
		sprites = new Sprite[numX][numY];
		for(int i = 0; i < numX; i++){
			for(int j = 0; j < numY; j++)
				sprites[i][j] = new Sprite(image.getSubimage(i * width, j * height, width, height));
		}
	}
	
	public Sprite getSprite(int i, int j){
		if(i < 0 || i >= sprites.length || j < 0 || j >= sprites[0].length)
			return null;
		return sprites[i][j];
	}
}