package search;

import heuristics.Evaluator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.IllegalMoveException;

import logger.Log;
import logic.Board;
import logic.Move;

public class GenericSearch {

	public GenericSearch(SearchStructureInf pathes, StopConditionInf stopCond,
			SearchOutputInf outputSearch, Evaluator evaluator) {
		this.pathes = pathes;
		this.stopCond = stopCond;
		this.outputSearch = outputSearch;
		this.evaluator = evaluator;
	}

	private SearchStructureInf pathes;
	private StopConditionInf stopCond;
	private SearchOutputInf outputSearch;
	private Evaluator evaluator;

	public SearchOutputInf searchMove(Board board) throws IllegalMoveException
	{
		List<Node> outputNodesList = new ArrayList<Node>();
		Node node = new Node(board,null,0);
		pathes.push(node);

		Set<Board> visitedSet = new HashSet<Board>();
		while(!pathes.isEmpty())
		{
			node = pathes.pop();
			if(stopCond.isStop(node))
			{
				outputNodesList.add(node);
				continue;
			}
			board = node.getBoard();
			if(!visitedSet.contains(board))
			{
				List<Move> moves = board.getLegalMoves(); 
				for (Move move : moves) {
					Node succesorNode = makeMove(board,move,node);
					pathes.push(succesorNode);
				}
				visitedSet.add(board);
			}
			//			else
			//			{
			//				Log.log("Board visited already");
			//			}
		}
		outputSearch.setNodes(outputNodesList);
		return outputSearch; 
	}

	private Node makeMove(Board board, Move move,Node parentNode) throws IllegalMoveException {
		Board tempBoard = board.clone();

		tempBoard.move(move, false);
		double score = this.evaluator.evaluate(tempBoard);
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(parentNode.getMoves());
		moves.add(move);
		return new Node(tempBoard,moves,score);
	}

	public void setEvaluator(Evaluator evaluator) {
		this.evaluator = evaluator;	
	}

}
