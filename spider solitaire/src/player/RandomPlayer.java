package player;

import java.util.Collections;
import java.util.List;

import exceptions.IllegalMoveException;

import logic.Board;
import logic.Card;
import logic.Move;

public class RandomPlayer extends Player {

	public RandomPlayer(Board board) {
		super(board);
	}

	@Override
	public Move getNextMove(List<Move> legalMoves) {
		Collections.shuffle(legalMoves);
		return legalMoves.get(0);
	}

	@Override
	public void update(Card[] upperCards) {
 
	}

	@Override
	public void terminatePlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Move move, Card[] upperCards)
			throws IllegalMoveException {
		// TODO Auto-generated method stub
		
	}


}
