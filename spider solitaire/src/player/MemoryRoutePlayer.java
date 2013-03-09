package player;

import heuristics.AttributeWeigths;
import heuristics.Evaluator;

import java.util.List;

import logic.Board;
import logic.Card;
import logic.Move;
import search.DFS;
import search.GenericSearch;
import search.MemoryOutput;
import search.SimpleStop;
import exceptions.IllegalMoveException;

public class MemoryRoutePlayer extends Player{
	
	GenericSearch search;
	MemoryOutput outputSearch;
	SimpleStop stopCond;
	DFS pathes;
	Evaluator evaluator1; // TODO: change...after moshe commit.
	Evaluator evaluator2; // TODO: change...after moshe commit.

	public MemoryRoutePlayer(Board board) {
		super(board);
		
		outputSearch = new MemoryOutput();
		stopCond = new SimpleStop();
		pathes = new DFS();
		
		//TODO: change when evaluator turn into an interface.
		double numOfDistributions = 0;
		double numOfPosiableMoves = 0.5;
		double numOfRemovableCards = 1;
		double numOfVisibleCards = 1;
		double numOfEmptyPiles = 0;
		double numOfCardsOnBoard = 0;
		double stateGameGrade = 1;
		double numOfInSequenceCards = 3;
		double numOfInSequenceCardsShapeUnSensetive = 1;
		double maxNumOfInSequenceCards = 0;
		double[] weights1 = {numOfDistributions,numOfPosiableMoves,	numOfRemovableCards, numOfVisibleCards,
				numOfEmptyPiles ,numOfCardsOnBoard ,stateGameGrade, numOfInSequenceCards, numOfInSequenceCardsShapeUnSensetive, maxNumOfInSequenceCards};
		
		numOfDistributions = 2;
		numOfPosiableMoves = 0;
		numOfRemovableCards = 0;
		numOfVisibleCards = 1;
		numOfEmptyPiles = 0;
		numOfCardsOnBoard = 0;
		stateGameGrade = 0;
		numOfInSequenceCards = 1;
		numOfInSequenceCardsShapeUnSensetive = 0;
		maxNumOfInSequenceCards = 0;
		double[] weights2 = {numOfDistributions,numOfPosiableMoves,	numOfRemovableCards, numOfVisibleCards,
				numOfEmptyPiles ,numOfCardsOnBoard ,stateGameGrade, numOfInSequenceCards, numOfInSequenceCardsShapeUnSensetive, maxNumOfInSequenceCards};
		
		evaluator1 = null;
		evaluator2 = null;
		try {
			evaluator1 = new Evaluator(new AttributeWeigths(weights1));
			evaluator2 = new Evaluator(new AttributeWeigths(weights2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		search = new GenericSearch(pathes, stopCond, outputSearch, evaluator1);
	}

	@Override
	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
		if(Math.random() > 0)
		{
			search.setEvaluator(evaluator1);
		}
		else
		{
			search.setEvaluator(evaluator2);
		}
		
		Move bestMove = null;
		if(outputSearch.isMemoryMove()){
			bestMove = outputSearch.getMemoryMove();
		}
		else
		{
			search.searchMove(board);
			bestMove = outputSearch.findBestMove();
		}
		if(bestMove != null)
		{
			this.board.move(bestMove, false);
		}
		return bestMove;
	}

	@Override
	public void update(Card[] upperCards) {
				
	}

	@Override
	public void terminatePlayer() {
		
	}
}
