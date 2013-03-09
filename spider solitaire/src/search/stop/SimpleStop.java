package search.stop;

import search.Node;
import logic.Board;

public class SimpleStop implements StopConditionInf {
	private int depth;
	
	
	public SimpleStop(int depth) {
		this.depth = depth;
	}


	@Override
	public boolean isStop(Node node) {
		if(node.getBoard().isGameOver())
		{
			return true;
		}
		
		if(node.getMoves().size() == this.depth)
		{
			return true;
		}
		
		return false;
	}
}
