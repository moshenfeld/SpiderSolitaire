package search.structure;

import java.util.LinkedList;
import java.util.Queue;

import search.Node;

public class BFS implements SearchStructureInf{

	private Queue<Node> innerQueue;
	
	public BFS() {
		this.innerQueue = new LinkedList<Node>();
	}

	@Override
	public boolean isEmpty() {
		return this.innerQueue.isEmpty();
	}

	@Override
	public void push(Node node) {
		this.innerQueue.add(node);
	}

	@Override
	public Node pop() {
		return this.innerQueue.poll();
	}
}
