package player.abstructPlayers;

import java.util.Collections;
import java.util.List;


import exceptions.IllegalMoveException;
import logic.Board;
import logic.Move;

public abstract class AIPlayer extends Player{

	public AIPlayer(Board board) {
		super(board);
	}

	@Override
	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
		Collections.shuffle(legalMoves);
		Move finalMove = null ,currentMove,lastMove;
		Move undo = new Move(0,0,-1);
		double maxScore = Double.NEGATIVE_INFINITY ,tempScore = 0.0;
		for (int i = 0; i < legalMoves.size(); i++) {
			currentMove = legalMoves.get(i);
			if(currentMove.numOfCards == -1){ // in case we want to undo a move 
				lastMove = board.getLastMove();
				board.move(currentMove, false);
				tempScore = evaluateState(board);
				board.move(lastMove, false);
			}
			else{
				board.move(currentMove, false);
				tempScore = evaluateState(board);
				board.move(undo, false);
			}
			if(tempScore > maxScore)
			{
				maxScore = tempScore;
				finalMove = currentMove;
			}		
		}
		return finalMove;
	}

	protected abstract double evaluateState(Board board) throws IllegalMoveException;

}