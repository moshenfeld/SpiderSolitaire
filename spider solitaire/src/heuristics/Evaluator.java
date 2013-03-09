package heuristics;

import exceptions.IllegalMoveException;
import logic.Board;

public interface Evaluator {

	public double evaluate(Board board) throws IllegalMoveException;
	
}
