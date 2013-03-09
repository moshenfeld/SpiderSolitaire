package player.abstructPlayers;

import java.util.List;

import exceptions.IllegalMoveException;

import logic.Board;
import logic.Card;
import logic.Move;

public abstract class Player {
	protected Board board;

	public Player(Board board) {
		this.board = board.clone();
	}
	
	public void setBoard(Board board) {
		this.board = board.clone();
	}
	
	public Board getBoard() {
		return board;
	}

	public abstract Move getNextMove(List<Move> legalMoves)  throws IllegalMoveException ;
	public abstract void update(Card[] upperCards);

	public void update(Move move, Card[] upperCards) throws IllegalMoveException {
		board.move(move, true);
		if(upperCards != null){
			for (int i = 0; i < Board.numOfPiles; i++) {
				if(upperCards[i] != null) {
					board.getCardPiles()[i].setTopToVisible();
					board.getCardPiles()[i].removeCards(1);
					board.getCardPiles()[i].addCard(upperCards[i]);
				}
			}
		}		
	}
	
	public abstract void terminatePlayer();

}
