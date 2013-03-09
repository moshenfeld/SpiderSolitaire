package game;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import exceptions.IllegalMoveException;

import heuristics.RewardCalculator;
import heuristics.StateAttributes;
import logic.Board;
import logic.Move;
import player.ApproximationAIPlayer;
import player.ApproximationAIFactory;

public class ApproximationGameManager {
	public static final int maxNumOfMoves = 1000;
	ApproximationAIFactory factory;

	public ApproximationGameManager(ApproximationAIFactory factory) {
		this.factory = factory;
	}

	public double runGame(Map<StateAttributes, Double> approximations, boolean withLearning, double epsilon, double gamma, double alpha)
							throws Exception{
		Board board = new Board();
		ApproximationAIPlayer player = factory.createPlayer(board, approximations);
		while(board.getNumOfCardsOnBoard() > 0 && board.getNumOfMoves() <= maxNumOfMoves){
			List<Move> legalMoves = board.getLegalMoves();
			Move move = player.getNextMove(legalMoves);
			if(Math.random() < epsilon){
				Collections.shuffle(legalMoves);
				move = legalMoves.get(0);
			}
			player.update(move, board.move(move, true));
			if(withLearning){
				updateApproximation(player, board, approximations, gamma, alpha);
			}
		}
		return board.getScore();
	}
	
	private void updateApproximation(ApproximationAIPlayer player, Board board, Map<StateAttributes, Double> approximations, double gamma,
									double alpha) throws IllegalMoveException{
		StateAttributes oldAttributes = StateAttributes.calculateState(player.getBoard()), newAttributes;
		double reward = RewardCalculator.getReward(board), oldValue = 0, newValue = 0;
		if(approximations.containsKey(oldAttributes)){
			oldValue = approximations.get(oldAttributes);
		}
		player.getNextMove(board.getLegalMoves());
		newAttributes = StateAttributes.calculateState(player.getBoard());
		if(approximations.containsKey(newAttributes)){
			newValue = approximations.get(newAttributes);
		}
		approximations.put(oldAttributes, oldValue - alpha * (reward + gamma * newValue - oldValue));
	}

}
