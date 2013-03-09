package heuristics;

import java.util.Arrays;

import exceptions.IllegalMoveException;
import logic.Board;

/**
 * class StateEvaluator:
 * represent the state by the terms we introduce.
 */
public class StateAttributes {

	public static enum BasicHeuristicsAttributes {numOfDistributions, numOfPosiableMoves, numOfRemovableCards, numOfVisibleCards, 
		numOfEmptyPiles ,numOfCardsOnBoard, numOfInSequenceCards, numOfInSequenceCardsShapeUnSensetive, maxNumOfInSequenceCards}
	
	public static enum ExtendedHeuristicsAttributes {numOfDistributions, numOfPosiableMoves, numOfRemovableCards, 
		numOfVisibleCards, numOfEmptyPiles ,numOfCardsOnBoard ,stateGameGrade, numOfInSequenceCards,
		numOfInSequenceCardsShapeUnSensetive, maxNumOfInSequenceCards}
	public static final double[] MIN_VALUES = {-100 ,0, 0, 0, 0, 0, -100, -100, -100,-100};
	public static final double[] MAX_VALUES = {100,0, 0, 0, 0, 0, 100, 100, 100,100};

	/**
	 * factory for this class, given the board, create a state data analyzer.
	 * 
	 * @param board
	 * @return the state data analyzer.
	 * @throws IllegalMoveException
	 */
	public static StateAttributes extendedStateCalculation(Board board) throws IllegalMoveException{
		int[] analyzerArray = new int[ExtendedHeuristicsAttributes.values().length];
		analyzerArray[ExtendedHeuristicsAttributes.numOfDistributions.ordinal()] = board.getNumOfDistributions();
		analyzerArray[ExtendedHeuristicsAttributes.numOfInSequenceCardsShapeUnSensetive.ordinal()] = board.getnumOfInSequenceCardsShapeUnSensetive();
		analyzerArray[ExtendedHeuristicsAttributes.numOfInSequenceCards.ordinal()] = board.getnumOfInSequenceCards();
		analyzerArray[ExtendedHeuristicsAttributes.maxNumOfInSequenceCards.ordinal()] = board.maxNumOfInSequenceCards();
		analyzerArray[ExtendedHeuristicsAttributes.numOfCardsOnBoard.ordinal()] = board.getNumOfCardsOnBoard(); 
		analyzerArray[ExtendedHeuristicsAttributes.numOfRemovableCards.ordinal()] = board.getNumOfRemovableCards();
		analyzerArray[ExtendedHeuristicsAttributes.numOfEmptyPiles.ordinal()] = board.getNumOfEmptyPiles();
		analyzerArray[ExtendedHeuristicsAttributes.numOfPosiableMoves.ordinal()] = board.getLegalMoves().size();
		analyzerArray[ExtendedHeuristicsAttributes.numOfVisibleCards.ordinal()] = board.getNumOfVisibleCards();
		analyzerArray[ExtendedHeuristicsAttributes.stateGameGrade.ordinal()] = board.getScore();
		return new StateAttributes(analyzerArray);
	}

	public static StateAttributes basicStateCalculation(Board board) throws IllegalMoveException {
		int[] analyzerArray = new int[BasicHeuristicsAttributes.values().length];
		analyzerArray[ExtendedHeuristicsAttributes.numOfDistributions.ordinal()] = board.getNumOfDistributions();
		analyzerArray[ExtendedHeuristicsAttributes.numOfInSequenceCardsShapeUnSensetive.ordinal()] = board.getnumOfInSequenceCardsShapeUnSensetive();
		analyzerArray[ExtendedHeuristicsAttributes.numOfInSequenceCards.ordinal()] = board.getnumOfInSequenceCards();
		analyzerArray[ExtendedHeuristicsAttributes.maxNumOfInSequenceCards.ordinal()] = board.maxNumOfInSequenceCards();
		analyzerArray[ExtendedHeuristicsAttributes.numOfCardsOnBoard.ordinal()] = board.getNumOfCardsOnBoard(); 
		analyzerArray[ExtendedHeuristicsAttributes.numOfRemovableCards.ordinal()] = board.getNumOfRemovableCards();
		analyzerArray[ExtendedHeuristicsAttributes.numOfEmptyPiles.ordinal()] = board.getNumOfEmptyPiles();
		analyzerArray[ExtendedHeuristicsAttributes.numOfPosiableMoves.ordinal()] = board.getLegalMoves().size();
		analyzerArray[ExtendedHeuristicsAttributes.numOfVisibleCards.ordinal()] = board.getNumOfVisibleCards();
		analyzerArray[ExtendedHeuristicsAttributes.stateGameGrade.ordinal()] = board.getScore();
		return new StateAttributes(analyzerArray);
	}
	
//***********************************************************************************************************	
//***     HERE STARTS THE CODE OF THE CLASS
//***********************************************************************************************************	
	
	int[] dataAnalyzersArray;

	/**
	 * private constructor.
	 * 
	 * @param analyzerArray
	 */
	private StateAttributes(int[] analyzerArray){
		this.dataAnalyzersArray = analyzerArray;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(dataAnalyzersArray);
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
		if (!Arrays.equals(dataAnalyzersArray, other.dataAnalyzersArray))
			return false;
		return true;
	}

	/**
	 * @return the dataAnalyersArray
	 */
	public int[] getDataAnalyersArray() {
		return dataAnalyzersArray;
	}
	
}
