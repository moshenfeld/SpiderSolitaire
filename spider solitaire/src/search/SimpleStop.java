package search;

import logic.Board;

public class SimpleStop implements StopConditionInf {
	
	
	@Override
	public boolean isStop(Node node) {
		if(node.getBoard().isGameOver())
		{
			return true;
		}
		
		if(node.getMoves().size() == 1)
		{
			return true;
		}
		
		return false;
	}
}
