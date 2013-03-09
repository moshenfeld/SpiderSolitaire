package player;

import heuristics.AttributeWeigths;
import heuristics.Evaluator;
import logic.Board;

public abstract class EvaluationAIPlayer extends AIPlayer {
	
	protected Evaluator evaluator;
	
	public EvaluationAIPlayer(Board board) {
		super(board);
	}
	
	public void setWeights(AttributeWeigths weights){
		this.evaluator = new Evaluator(weights);		
	}
	
	
}
