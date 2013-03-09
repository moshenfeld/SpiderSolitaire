package player;

import heuristics.Evaluator;
import exceptions.IllegalMoveException;
import logic.Board;
import logic.Card;
import logic.Move;

public class NaiveAIPlayer extends EvaluationAIPlayer{
	
	public NaiveAIPlayer(Board board, Evaluator evaluator){
		super(board);
		this.evaluator = evaluator;		
	}

//	@Override
//	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
//		Move finalMove = null ,currentMove,lastMove;
//		Move undo = new Move(0,0,-1);
//		double maxScore = Double.NEGATIVE_INFINITY ,tempScore = 0.0;
//		for (int i = 0; i < legalMoves.size(); i++) {
//			currentMove = legalMoves.get(i);
//			if(currentMove.numOfCards == -1) // in case we want to undo a move 
//			{
//				lastMove = board.getLastMove();
//				board.move(currentMove);
//				tempScore = evaluator.getValue(board);
//				board.move(lastMove);
//			}
//			else{
//
//				board.move(currentMove);
//				tempScore = evaluator.getValue(board);
//				board.move(undo);
//			}
//			if(tempScore > maxScore)
//			{
//				maxScore = tempScore;
//				finalMove = currentMove;
//			}		
//		}
//		System.out.println("final: "+finalMove);	
//		return finalMove;
//	}

	@Override
	public void update(Card[] upperCards) {
		
	}


	@Override
	public void terminatePlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected double evaluateState(Board board) throws IllegalMoveException {
		return evaluator.getValue(board);
	}

	@Override
	public void update(Move move, Card[] upperCards) throws IllegalMoveException {
		board.move(move, true);
	}
	
}
