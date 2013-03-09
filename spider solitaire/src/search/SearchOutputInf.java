package search;

import java.util.List;

import logic.Move;

public interface SearchOutputInf {
	
	public Move findBestMove();
	
	public void setNodes(List<Node> nodes);
	
}
