package game;

import java.awt.event.KeyEvent;

import exceptions.IllegalMoveException;

import player.Player;
import logic.Board;

public class GameManager implements Runnable {

	Board board;
	Player player;
	Window window;
	private boolean updateView;
	boolean updateAtKeyPress;
	
	public GameManager(Board board, Player player, Window window, boolean updateView, boolean updateAtKeyPress){
		this.board = board;
		this.player = player;
		this.window = window;
		this.updateView = updateView;
		this.updateAtKeyPress = updateAtKeyPress;
	}
	
	public void keyPressed(KeyEvent e){
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_ENTER)
			updateAtKeyPress = !updateAtKeyPress;
		else if(k == KeyEvent.VK_SPACE){
			updateAtKeyPress = true;
			update();
		}
	}
	
	public void update(){
		try {
			player.update(board.move(player.getNextMove(board.getLegalMoves()), true));
		} catch (IllegalMoveException e) {
			e.printStackTrace();
		}
		if(updateView)
			window.repaint();
	}
	
	public void run() {
		//TODO when to stop?
		while(board.getNumOfCardsOnBoard() > 0){
			if(!updateAtKeyPress){
				try {
					update();
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}