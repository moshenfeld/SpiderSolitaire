package search;

import java.util.List;

import logger.Log;
import logic.Move;

public class SimpleOutput implements SearchOutputInf {
	
	List<Node> nodes;
	
	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public Move findBestMove() {
		double maxScore = Double.NEGATIVE_INFINITY;
		Move move = null;
		for (Node node : nodes) {
			Log.log(node);
			if(node.getScore() > maxScore)
			{
				maxScore = node.getScore();
				move = node.getMoves().get(0); 
			}
			if(node.getScore() == maxScore)
			{
				Log.log("Two Scores Are Equal");
			}
		}
		Log.log(move);
		return move;
	}

}
