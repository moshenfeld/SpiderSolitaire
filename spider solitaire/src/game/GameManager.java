package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import exceptions.IllegalMoveException;

import player.abstructPlayers.Player;
import logic.Board;

public class GameManager {

	private Board board;
	private Player player;
	private Window window;
	private boolean updateView;
	private boolean updateAtKeyPress;
	private boolean keyPressed;
	
	public GameManager(Board board, Player player, Window window, boolean updateView, boolean updateAtKeyPress){
		this.board = board;
		this.player = player;
		this.window = window;
		this.updateView = updateView;
		this.updateAtKeyPress = updateAtKeyPress;
	}
	
	public void setKeyPressed(boolean keyPressed) {
		this.keyPressed = keyPressed;
	}

	public boolean isUpdateAtKeyPress() {
		return updateAtKeyPress;
	}

	public void setUpdateAtKeyPress(boolean updateAtKeyPress) {
		this.updateAtKeyPress = updateAtKeyPress;
	}

	public void update() throws IllegalMoveException {
		player.update(board.move(player.getNextMove(board.getLegalMoves()), true));
		if(updateView)
			window.repaint();
	}
	
	public void run() throws IllegalMoveException {
		while(board.getNumOfCardsOnBoard() > 0){
			if(!updateAtKeyPress || keyPressed){
				update();
				try {
					Thread.sleep(Main.UPDATE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
	
}