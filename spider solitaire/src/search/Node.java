package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import logic.Board;
import logic.Move;

public class Node {


	private Board board;
	private List<Move> moves;
	private double score;

	public Node(Board board, List<Move> moves, double score) {
		this.board = board;
		this.score = score;
		this.moves = moves;
		if(moves == null)
		{
			this.moves = new ArrayList<Move>();
		}
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @return the move
	 */
	public List<Move> getMoves() {
		return moves;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "************************\n" +"Node:\n" + "score: "+ score+"\n" +
				"moves: \n"+Arrays.toString(this.moves.toArray())+ board;
	}







}
