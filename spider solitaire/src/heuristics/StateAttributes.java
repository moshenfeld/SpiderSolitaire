package heuristics;

import java.util.Arrays;

import exceptions.IllegalMoveException;
import logic.Board;

/**
 * class StateEvaluator:
 * represent the state by the terms we introduce.
 */
public class StateAttributes {
	
	int[] dataAnalyersArray;

	/**
	 * private constructor.
	 * 
	 * @param analyerArray
	 */
	private StateAttributes(int[] analyerArray){
		this.dataAnalyersArray = analyerArray;
	}
	
	/**
	 * factory for this class, given the board, create a state data analyzer.
	 * 
	 * @param board
	 * @return the state data analyzer.
	 * @throws IllegalMoveException
	 */
	public static StateAttributes calculateState(Board board) throws IllegalMoveException
	{
		int[] analyerArray = new int[AttributeWeigths.HeuristicsAttributes.values().length];
		analyerArray[AttributeWeigths.HeuristicsAttributes.numOfDistributions.ordinal()] = board.getNumOfDistributions();
		analyerArray[AttributeWeigths.HeuristicsAttributes.numOfInSequenceCardsShapeUnSensetive.ordinal()] = board.getnumOfInSequenceCardsShapeUnSensetive();
		analyerArray[AttributeWeigths.HeuristicsAttributes.numOfInSequenceCards.ordinal()] = board.getnumOfInSequenceCards();
		analyerArray[AttributeWeigths.HeuristicsAttributes.maxNumOfInSequenceCards.ordinal()] = board.maxNumOfInSequenceCards();
		analyerArray[AttributeWeigths.HeuristicsAttributes.numOfCardsOnBoard.ordinal()] = board.getNumOfCardsOnBoard(); 
		analyerArray[AttributeWeigths.HeuristicsAttributes.numOfRemovableCards.ordinal()] = board.getNumOfRemovableCards();
		analyerArray[AttributeWeigths.HeuristicsAttributes.numOfEmptyPiles.ordinal()] = board.getNumOfEmptyPiles();
		analyerArray[AttributeWeigths.HeuristicsAttributes.numOfPosiableMoves.ordinal()] = board.getLegalMoves().size();
		analyerArray[AttributeWeigths.HeuristicsAttributes.numOfVisibleCards.ordinal()] = board.getNumOfVisibleCards();
		analyerArray[AttributeWeigths.HeuristicsAttributes.stateGameGrade.ordinal()] = board.getScore();
		
		return new StateAttributes(analyerArray);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(dataAnalyersArray);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateAttributes other = (StateAttributes) obj;
		if (!Arrays.equals(dataAnalyersArray, other.dataAnalyersArray))
			return false;
		return true;
	}

	/**
	 * @return the dataAnalyersArray
	 */
	public int[] getDataAnalyersArray() {
		return dataAnalyersArray;
	}
	

	

}
