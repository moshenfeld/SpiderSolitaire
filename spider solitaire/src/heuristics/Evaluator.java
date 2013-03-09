package heuristics;

import exceptions.IllegalMoveException;
import logic.Board;

public class Evaluator{
	
	private AttributeWeigths attributesWeigths;
	
	public Evaluator(AttributeWeigths attributesWeigths)
	{
		this.attributesWeigths = attributesWeigths;
	}
	
	
	public double getValue(Board board) throws IllegalMoveException {
		StateAttributes analyzer = StateAttributes.calculateState(board);
		double[] weights = attributesWeigths.getWeightArray();
				
		double score =  0.0;
		for (int i = 0; i < weights.length; i++) {
			score += weights[i] * (double)analyzer.getDataAnalyersArray()[i];
		}
		//return (double)analyzer.getDataAnalyersArray()[weights.length-1];
		return score;
	}
}
