package player;

import heuristics.Evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import logic.Board;
import logic.Card;
import logic.CardsPile;
import logic.Move;
import exceptions.IllegalMoveException;

public class DeepAIPlayer  extends EvaluationAIPlayer{

 
	
	private int depthDegree;
	
	private Board tempBoard;
	private final Move undo = new Move(0,0,-1);




	private class MovesAndScore{
		public double score;
		public Move move;

		public MovesAndScore(double score, Move move) {
			super();
			this.score = score;
			this.move = move;
		}
	}

	public DeepAIPlayer(Board board, Evaluator evaluator,int depthDegree){
		super(board);
		this.depthDegree = depthDegree;
		this.evaluator = evaluator;		
	}


	@Override
	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
		tempBoard = board.clone();
		ScoreAndMove bestScoreAndMove = getNextDeepMove(this.depthDegree);	
		if(bestScoreAndMove.move != null)
		{
			board.move(bestScoreAndMove.move, true);
		}
		return bestScoreAndMove.move;
	}

	private class ScoreAndMove implements Comparable<ScoreAndMove>{

		public double score;
		public Move move;

		public ScoreAndMove(double score, Move move) {
			this.score = score;
			this.move = move;
		}

		@Override
		public int compareTo(ScoreAndMove other) {
			return (int) (other.score - this.score);
		}
		
		
	}
	
		private ScoreAndMove getNextDeepMove(int depth) throws IllegalMoveException
		{
			depth--;
			if(tempBoard.isGameOver())
			{
				System.out.println("out");
				return new ScoreAndMove((evaluator.getValue(tempBoard) * (depth+2)),null);			
			}
			if(depth < 0)
			{
				return new ScoreAndMove(evaluator.getValue(tempBoard),null);
			}
			List<Move> legalMoves = tempBoard.getLegalMoves();
			ScoreAndMove tempScoreAndMove, bestScoreAndMove = new ScoreAndMove(Double.NEGATIVE_INFINITY,null);
			Move undoMove = null;
			for (Move move : legalMoves) {
				if(move.isUndoMove()){ // in case we want to undo a move 
					undoMove = tempBoard.getLastMove();	
				}
				else{
					undoMove = undo;
				}
				tempBoard.move(move, false);
				tempScoreAndMove = getNextDeepMove(depth);
				tempScoreAndMove.score +=evaluator.getValue(tempBoard);	
				tempBoard.move(undoMove, false);
				tempBoard.fakeMoveResetData(2);

				if(tempScoreAndMove.score > bestScoreAndMove.score )
				{
					bestScoreAndMove = new ScoreAndMove(tempScoreAndMove.score,move);
				}
			}
			return bestScoreAndMove;
		}





	private boolean checkIsEmptyDestination(Board tempBoard, Move move) {
		if(move.numOfCards > 0)
		{
			CardsPile[] cardPiles = tempBoard.getCardPiles();
			return cardPiles[move.dest].getSize() == 0;

		}
		return false;
	}


	@Override
	public void update(Card[] upperCards) {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminatePlayer() {
		// TODO Auto-generated method stub

	}


	@Override
	protected double evaluateState(Board board) throws IllegalMoveException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void update(Move move, Card[] upperCards)
			throws IllegalMoveException {
		// TODO Auto-generated method stub

	}

}
