package heuristics;

import logic.Board;

public class RewardCalculator {
	
	public static double getReward(Board board){
		return board.getScore();
	}
}
