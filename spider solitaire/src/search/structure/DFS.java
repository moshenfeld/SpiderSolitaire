package search.structure;

import java.util.Stack;

import search.Node;



public class DFS implements SearchStructureInf{

	private Stack<Node> innerStack;
	
	public DFS() {
		this.innerStack = new Stack<Node>();
	}

	@Override
	public boolean isEmpty() {
		return this.innerStack.isEmpty();
	}

	@Override
	public void push(Node node) {
		this.innerStack.push(node);
	}

	@Override
	public Node pop() {
		return this.innerStack.pop();
	}
}
