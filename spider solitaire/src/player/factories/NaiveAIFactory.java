package player.factories;

import heuristics.AttributeWeigths;
import heuristics.Evaluator;
import heuristics.WeightsEvaluator;

import java.util.List;

import player.abstructPlayers.EvaluationAIPlayer;
import player.players.DeepAIPlayer;

import logic.Board;
import logic.Card;
import logic.Move;
import exceptions.IllegalMoveException;

public class NaiveAIFactory extends EvaluationAIFactory {

	@Override
	public EvaluationAIPlayer createPlayer(Board board, AttributeWeigths attributeWeigths) throws IllegalMoveException {
		board.startGame();
		return new DeepAIPlayer(board, new WeightsEvaluator(attributeWeigths),1);
		//return new NaiveAIPlayer(board.clone(), new Evaluator(attributeWeigths));
	}


}
