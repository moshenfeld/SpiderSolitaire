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
	
	private GenericSearch search;
	private MemoryOutput outputSearch;
	private SimpleStop stopCond;
	private DFS pathes;
	private int depth;
	private Evaluator evaluator; // TODO: change...after moshe commit.

	public MemoryRoutePlayer(Board board,int depth) {
		super(board);
		this.depth = depth;
		outputSearch = new MemoryOutput();
		stopCond = new SimpleStop(depth);
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
		double[] weights = {numOfDistributions,numOfPosiableMoves,	numOfRemovableCards, numOfVisibleCards,
				numOfEmptyPiles ,numOfCardsOnBoard ,stateGameGrade, numOfInSequenceCards, numOfInSequenceCardsShapeUnSensetive, maxNumOfInSequenceCards};
		
		evaluator = null;
		try {
			evaluator = new Evaluator(new AttributeWeigths(weights));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		search = new GenericSearch(pathes, stopCond, outputSearch, evaluator);
	}

	@Override
	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
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
