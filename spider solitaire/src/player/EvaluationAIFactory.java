package player;

import heuristics.AttributeWeigths;
import logic.Board;
import exceptions.IllegalMoveException;

public abstract class EvaluationAIFactory {

	public abstract EvaluationAIPlayer createPlayer(Board board, AttributeWeigths attributeWeigths) throws IllegalMoveException;
	
}
