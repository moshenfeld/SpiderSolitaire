package player;

import java.util.Map;

import game.MainParameters;
import heuristics.StateAttributes;
import logic.Board;
import exceptions.IllegalMoveException;

public class ApproximationAIFactory{

	public ApproximationAIPlayer createPlayer(Board board, Map<StateAttributes, Double> approximations) throws IllegalMoveException {
		if(MainParameters.FULL_OBSERVATION){
			board.startGame();
			return new ApproximationAIPlayer(board.clone(), approximations);
		}
		return new ApproximationAIPlayer(board.startGame(), approximations);
	}

}
