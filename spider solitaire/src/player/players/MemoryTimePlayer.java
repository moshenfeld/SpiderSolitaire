package player.players;

import heuristics.AttributeWeigths;
import heuristics.Evaluator;
import heuristics.WeightsEvaluator;

import java.util.List;

import logic.Board;
import logic.Card;
import logic.Move;
import player.abstructPlayers.Player;
import search.GenericSearch;
import search.output.MemoryOutput;
import search.stop.SimpleStop;
import search.stop.TimeStop;
import search.structure.BFS;
import search.structure.DFS;
import exceptions.IllegalMoveException;

public class MemoryTimePlayer extends Player{
	
	private GenericSearch search;
	private MemoryOutput outputSearch;
	private TimeStop stopCond;
	private BFS pathes;
	private Evaluator evaluator; // TODO: change...after moshe commit.
	private int depth;
	private long timeout;

	public MemoryTimePlayer(Board board,int depth,long timeout) {
		super(board);
		this.depth = depth;
		this.timeout = timeout;
		outputSearch = new MemoryOutput();
		stopCond = new TimeStop(this.depth,this.timeout);
		pathes = new BFS();
		
		//TODO: change when evaluator turn into an interface.
		double numOfDistributions = 0;
		double numOfPosiableMoves = 0.5;
		double numOfRemovableCards = 1;
		double numOfVisibleCards = 1;
		double numOfEmptyPiles = 0;
		double numOfCardsOnBoard = 0;
		double stateGameGrade = 0;
		double numOfInSequenceCards = 3;
		double numOfInSequenceCardsShapeUnSensetive = 1;
		double maxNumOfInSequenceCards = 0;
		double[] weights = {numOfDistributions,numOfPosiableMoves,	numOfRemovableCards, numOfVisibleCards,
				numOfEmptyPiles ,numOfCardsOnBoard ,stateGameGrade, numOfInSequenceCards, numOfInSequenceCardsShapeUnSensetive, maxNumOfInSequenceCards};
		
		
		evaluator = null;
		try {
			evaluator = new WeightsEvaluator(new AttributeWeigths(weights));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		search = new GenericSearch(pathes, stopCond, outputSearch, evaluator);
	}

	@Override
	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
		//Check if we have moves in memory:
		Move bestMove = null;
		if(outputSearch.isMemoryMove()){
			bestMove = outputSearch.getMemoryMove();
		}
		else
		{
			this.stopCond.setStartTime();
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
