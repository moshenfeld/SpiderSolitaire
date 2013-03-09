package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import exceptions.IllegalMoveException;
import game.Main;

import logic.Board;
import logic.Card;
import logic.CardsPile;
import logic.Move;

public class Viewer {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	private static final int X_OFFSET = 15;
	private static final int Y_OFFSET = 22;
	
	private static final int CARD_WIDTH = 72;
	private static final int CARD_HEIGHT = 96;
	private static final int CARDS_MOVED_FITTING = 3;
	
	private static final SpriteSheet card_images = new SpriteSheet(ImageLoader.getImage("visible cards.png"), CARD_WIDTH, CARD_HEIGHT);
	private static final SpriteSheet invisible_card_images = new SpriteSheet(ImageLoader.getImage("invisible cards.png"), CARD_WIDTH, CARD_HEIGHT);
	
	static int numOfFinishedSeries = 0;
	
	public static void calcDimensions(){
		
	}
	
	private static void renderCard(Card card, int x, int y, Graphics g){
		BufferedImage img = null;
		if(card.isVisible())
			img = card_images.getSprite(card.number.ordinal(), card.type.ordinal()).getImage();
		else
			img = invisible_card_images.getSprite(card.number.ordinal(), card.type.ordinal()).getImage();
		
		g.drawImage(img, x, (int)y, null);
		
		img.flush();
	}
	
	public static void renderBoard(Graphics g, Board board){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D imgG = img.createGraphics();
//		imgG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // uncomment for cool matrix effect!!!1
		
		imgG.setColor(Color.getHSBColor(0.35f, 1, 0.45f));
		imgG.fillRect(0, 0, img.getWidth(), img.getHeight());
		
		// draw extra cards
		int startY = HEIGHT - (Y_OFFSET * Board.numOfRounds + CARD_HEIGHT + 20);
		Card[][] extras = board.getExtraCards();
		for(int i = 0; i < Board.numOfRounds; i++){
			for(int j = 0; j < Board.numOfPiles; j++){
				int x_offset = X_OFFSET + j * (X_OFFSET + CARD_WIDTH);
				int y_offset = i * Y_OFFSET + startY;
				Card c = extras[i][j];
				if(c != null)
					renderCard(c, x_offset, y_offset, imgG);
				else{
					imgG.setColor(Color.getHSBColor(0.35f, 1, 0.45f));
					imgG.fillRect(x_offset, y_offset, CARD_WIDTH, CARD_HEIGHT);
					imgG.setColor(Color.BLACK);
					imgG.drawRect(x_offset, y_offset, CARD_WIDTH, CARD_HEIGHT);
				}
			}
		}
		
		// draw card piles
		CardsPile[] cardPiles = board.getCardPiles();
		for(int i = 0; i < Board.numOfPiles; i++){
			CardsPile pile = cardPiles[i];
			for(int j = 0; j < pile.getSize(); j++){
				int x_offset = X_OFFSET + i * (X_OFFSET + CARD_WIDTH);
				int y_offset = X_OFFSET + j * Y_OFFSET;
				try {
					renderCard(pile.getCard(j), x_offset, y_offset, imgG);
				} catch (IllegalMoveException e) {
					e.printStackTrace();
				}
			}
		}
		
		int y = img.getHeight() - 20;
		imgG.setColor(Color.LIGHT_GRAY);
		imgG.fillRect(0, y, img.getWidth(), 20);
		imgG.setColor(Color.BLACK);
		imgG.drawRect(0, y, img.getWidth(), 20);
		imgG.drawString("Moves: " + board.getNumOfMoves() + ", Score: " + board.getScore(), 10, y + 13);
		
		imgG.setColor(Color.BLACK);
		int startX = 10 + X_OFFSET + Board.numOfPiles * (X_OFFSET + CARD_WIDTH);
		imgG.fillRect(startX, 0, 15, y);
		
		// draw moved cards
		int currNumOfFinishedSeries = board.getNumOfFinishedSeries();
		if(numOfFinishedSeries == currNumOfFinishedSeries){
			numOfFinishedSeries = currNumOfFinishedSeries;
			
			if(board.getNumOfMoves() > 0){
				startX += 15 + 2 * X_OFFSET;
				Move lastMove = board.getLastMove();
				if(lastMove != null){
					int numOfCards = lastMove.numOfCards;
					CardsPile pile = board.getCardPiles()[lastMove.dest];
					int size = pile.getSize();
					Card[] movedCards = new Card[numOfCards];
					for(int i = size - 1; i >= size - numOfCards; i--){
						int index = size - 1 - i;
						int x_offset = startX + (index % CARDS_MOVED_FITTING) * (X_OFFSET + CARD_WIDTH);
						int y_offset = Y_OFFSET + (index / CARDS_MOVED_FITTING) * (Y_OFFSET + CARD_HEIGHT);
						try {
							movedCards[index] = pile.getCard(i);
						} catch (IllegalMoveException e) {
							e.printStackTrace();
						}
						renderCard(movedCards[index], x_offset, y_offset, imgG);
					}
					if(lastMove.numOfCards == 0)
						imgG.drawString("Distributed cards", startX, (int)(0.5 * Y_OFFSET));
					else
						imgG.drawString(lastMove.toString(), startX, (int)(0.5 * Y_OFFSET));
				}
			}
		}
		
		AffineTransform at = new AffineTransform();
		at.translate(8, 30);
        at.scale((double)(Main.window.getWidth() - 16) / WIDTH, (double)(Main.window.getHeight() - 38) / HEIGHT);
        g2d.drawImage(img, at, null);
//		Image scaled = img.getScaledInstance(Main.window.getWidth(), Main.window.getHeight(), BufferedImage.SCALE_SMOOTH);
//		g.drawImage(scaled, 8, 30, null);
	}
}