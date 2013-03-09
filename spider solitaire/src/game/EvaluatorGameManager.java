package game;

import heuristics.AttributeWeigths;
import logic.Board;
import logic.Move;
import player.abstructPlayers.EvaluationAIPlayer;
import player.factories.EvaluationAIFactory;

public class EvaluatorGameManager {
	
	public static final int maxNumOfMoves = 1000;
	EvaluationAIFactory factory;

	public EvaluatorGameManager(EvaluationAIFactory factory) {
		this.factory = factory;
	}

	public double runGame(double[] weights) throws Exception{
		Board board = new Board();
		EvaluationAIPlayer player = factory.createPlayer(board, new AttributeWeigths(weights));
		Move move;
		while(board.getNumOfCardsOnBoard() > 0 && board.getNumOfMoves() <= maxNumOfMoves){
			move = player.getNextMove(board.getLegalMoves());
			player.update(board.move(move, true));
		}
		return board.getScore();
	}
		
}
