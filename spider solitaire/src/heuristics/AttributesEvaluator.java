package heuristics;

import java.util.Map;

import logic.Board;
import exceptions.IllegalMoveException;

public class AttributesEvaluator implements Evaluator {

	Map<StateAttributes, Double> approximations;

	public AttributesEvaluator(Map<StateAttributes, Double> approximations) {
		this.approximations = approximations;
	}
	
	@Override
	public double evaluate(Board board) throws IllegalMoveException {
		Double temp = approximations.get(StateAttributes.basicStateCalculation(board));
		return (temp == null)? 0: temp;
	}

}