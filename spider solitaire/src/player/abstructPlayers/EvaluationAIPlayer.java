package player.abstructPlayers;

import heuristics.AttributeWeigths;
import heuristics.Evaluator;
import heuristics.WeightsEvaluator;
import logic.Board;

public abstract class EvaluationAIPlayer extends AIPlayer {
	
	protected Evaluator evaluator;
	
	public EvaluationAIPlayer(Board board) {
		super(board);
	}
	
	public void setWeights(AttributeWeigths weights){
		this.evaluator = new WeightsEvaluator(weights);		
	}
	
	
}
