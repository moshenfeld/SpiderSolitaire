package heuristics;

import exceptions.IllegalMoveException;
import logic.Board;

public class WeightsEvaluator implements Evaluator{
	
	private AttributeWeigths attributesWeigths;
	
	public WeightsEvaluator(AttributeWeigths attributesWeigths)
	{
		this.attributesWeigths = attributesWeigths;
	}
	
	public double evaluate(Board board) throws IllegalMoveException {
		StateAttributes analyzer = StateAttributes.extendedStateCalculation(board);
		double[] weights = attributesWeigths.getWeightArray();
		double score =  0.0;
		for (int i = 0; i < weights.length; i++) {
			score += weights[i] * (double)analyzer.getDataAnalyersArray()[i];
		}
		//return (double)analyzer.getDataAnalyersArray()[weights.length-1];
		return score;
	}
	
}
