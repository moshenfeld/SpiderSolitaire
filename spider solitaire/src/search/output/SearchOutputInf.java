package search.output;

import java.util.List;

import search.Node;

import logic.Move;

public interface SearchOutputInf {
	
	public Move findBestMove();
	
	public void setNodes(List<Node> nodes);
	
}
