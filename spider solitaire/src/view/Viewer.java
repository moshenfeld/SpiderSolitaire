package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import exceptions.IllegalMoveException;
import game.Main;

import logic.Board;
import logic.Card;
import logic.CardsPile;
import logic.Move;

public class Viewer {

	private static final int X_OFFSET = 15;
	private static final int Y_OFFSET = 30;
	
	private static final int CARD_WIDTH = 72;
	private static final int CARD_HEIGHT = 96;
	private static final double SCALE = 0.2;
	private static final int CARDS_MOVED_FITTING = 6;
	
	private static final SpriteSheet card_images = new SpriteSheet(ImageLoader.getImage("visible cards.png"), CARD_WIDTH, CARD_HEIGHT);
	
	private static double getScale(){
		return (((double)Main.window.getWidth() / Main.WIDTH) + ((double)Main.window.getHeight() / Main.HEIGHT)) / 2 - SCALE;
	}
	
	private static void renderCard(Card card, int x, int y, Graphics g){
		BufferedImage img;
		img = card_images.getSprite(card.number.ordinal(), card.type.ordinal()).getImage();
		
		double scale = getScale();
		Image scaledImage = img.getScaledInstance((int)(CARD_WIDTH * scale), (int)(CARD_HEIGHT * scale), img.SCALE_SMOOTH);
		g.drawImage(scaledImage, (int)(x * scale), (int)(y * scale), null);
		if(!card.isVisible()){
			g.setColor(Color.BLACK);
			g.fillRect((int)(x * scale), (int)((y + 7) * scale), (int)(CARD_WIDTH * scale), (int)(4 * scale));
		}
		
		img.flush();
		scaledImage.flush();
	}
	
	public static void renderBoard(Graphics g, Board board){
		double scale = getScale();
		
		int startY = Y_OFFSET * (Card.Number.values().length + 4);
		Card[][] extras = board.getExtraCards();
		for(int i = 0; i < board.numOfRounds; i++){
			for(int j = 0; j < board.numOfPiles; j++){
				int x_offset = X_OFFSET + j * (X_OFFSET + CARD_WIDTH);
				int y_offset = i * (CARD_HEIGHT + X_OFFSET) + startY;
				Card c = extras[i][j];
				if(c != null)
					renderCard(c, x_offset, y_offset, g);
				else{
					g.setColor(Color.BLACK);
					g.drawRect((int)(x_offset * scale), (int)(y_offset * scale), (int)(CARD_WIDTH * scale), (int)(CARD_HEIGHT * scale));
				}
			}
		}
		
		CardsPile[] cardPiles = board.getCardPiles();
		for(int i = 0; i < board.numOfPiles; i++){
			CardsPile pile = cardPiles[i];
			for(int j = 0; j < pile.getSize(); j++){
				int x_offset = X_OFFSET + i * (X_OFFSET + CARD_WIDTH);
				int y_offset = X_OFFSET + j * Y_OFFSET;
				try {
					renderCard(pile.getCard(j), x_offset, y_offset, g);
				} catch (IllegalMoveException e) {
					e.printStackTrace();
				}
			}
		}
		
		int y = Main.window.getHeight() - 20;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, y, Main.window.getWidth(), 20);
		g.setColor(Color.BLACK);
		g.drawRect(0, y, Main.window.getWidth(), 20);
		g.drawString("Moves: " + board.getNumOfMoves() + ", Score: " + board.getScore(), 10, y + 13);
		
		g.setColor(Color.BLACK);
		int startX = 10 + X_OFFSET + board.numOfPiles * (X_OFFSET + CARD_WIDTH);
		g.fillRect((int)(startX * scale), 0, 15, y);
//		if(board.getNumOfMoves() > 0){
//			startX += 15 + 2 * X_OFFSET;
//			Move lastMove = board.getLastMove();
//			if(lastMove != null){
//				int numOfCards = lastMove.numOfCards;
//				CardsPile pile = board.getCardPiles()[lastMove.dest];
//				int size = pile.getSize();
//				Card[] movedCards = new Card[numOfCards];
//				for(int i = size - 1; i >= size - numOfCards; i--){
//					try {
//						int index = size - 1 - i;
//						int x_offset = startX + (index % CARDS_MOVED_FITTING) * (X_OFFSET + CARD_WIDTH);
//						int y_offset = Y_OFFSET + (index / CARDS_MOVED_FITTING) * (Y_OFFSET + CARD_HEIGHT);
//						
//						movedCards[index] = pile.getCard(i);
//						renderCard(movedCards[index], x_offset, y_offset, g);
//					} catch (IllegalMoveException e) {
//						e.printStackTrace();
//					}
//				}
//				if(lastMove.numOfCards == 0)
//					g.drawString("Distributed cards", (int)(startX * scale), (int)(0.5 * Y_OFFSET * scale));
//				else
//					g.drawString(lastMove.toString(), (int)(startX * scale), (int)(0.5 * Y_OFFSET * scale));
//			}
//		}
	}
}