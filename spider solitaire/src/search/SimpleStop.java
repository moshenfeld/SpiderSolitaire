package search;

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
		
		if(node.getMoves().size() == depth)
		{
			return true;
		}
		
		return false;
	}
}
