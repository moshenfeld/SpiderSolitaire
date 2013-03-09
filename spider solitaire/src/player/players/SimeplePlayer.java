package player.players;

import heuristics.AttributeWeigths;
import heuristics.Evaluator;
import heuristics.WeightsEvaluator;

import java.util.List;

import player.abstructPlayers.Player;

import search.GenericSearch;
import search.output.SearchOutputInf;
import search.output.SimpleOutput;
import search.stop.SimpleStop;
import search.stop.StopConditionInf;
import search.structure.BFS;
import search.structure.SearchStructureInf;

import logic.Board;
import logic.Card;
import logic.Move;
import exceptions.IllegalMoveException;

public class SimeplePlayer extends Player{
	
	GenericSearch search;
	SimpleOutput outputSearch;
	SimpleStop stopCond;
	BFS pathes;
	Evaluator evaluator; // TODO: change...after moshe commit.

	public SimeplePlayer(Board board) {
		super(board);
		
		outputSearch = new SimpleOutput();
		stopCond = new SimpleStop(1);
		pathes = new BFS();
		
		//TODO: change when evaluator turn into an interface.
		double numOfDistributions = -1;
		double numOfPosiableMoves = 0.5;
		double numOfRemovableCards = 1;
		double numOfVisibleCards = 1;
		double numOfEmptyPiles = 0.5;
		double numOfCardsOnBoard = -1;
		double stateGameGrade = 0;
		double numOfInSequenceCards = 3;
		double numOfInSequenceCardsShapeUnSensetive = 1;
		double maxNumOfInSequenceCards = 1;
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
		search.searchMove(board);
		Move bestMove = outputSearch.findBestMove();
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
