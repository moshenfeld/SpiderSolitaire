package search.output;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import search.Node;

import logger.Log;
import logic.Move;

public class MemoryOutput implements SearchOutputInf {
	
	List<Node> nodes;
	Queue<Move> memoryMoves;
	
	public MemoryOutput()
	{
		memoryMoves = new LinkedList<Move>();
	}
	
	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public boolean isMemoryMove(){
		return !memoryMoves.isEmpty();
	}
	
	public Move getMemoryMove(){
		return memoryMoves.poll();
	}

	@Override
	public Move findBestMove() {
		// find if we know what to do.
	 	
		double maxScore = Double.NEGATIVE_INFINITY;
		Node outputNode = null;
		for (Node node : nodes) {
			Log.log(node);
			if(node.getScore() > maxScore)
			{
				maxScore = node.getScore();
				outputNode = node;		
			}
		}
		
		// feel the queue for memory.
		for (int i = 0; i < outputNode.getMoves().size(); i++) {
			memoryMoves.offer(outputNode.getMoves().get(i));
		}
		
		Move move = memoryMoves.poll();
		Log.log(move);
		return move;
	}

}
