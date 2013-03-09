package player.players;

import heuristics.StateAttributes;
import java.util.Map;

import player.abstructPlayers.AIPlayer;
import logic.Board;
import logic.Card;
import logic.Move;
import exceptions.IllegalMoveException;

public class ApproximationAIPlayer extends AIPlayer {

	Map<StateAttributes, Double> approximations;
	
	public ApproximationAIPlayer(Board board, Map<StateAttributes, Double> approximations) {
		super(board);
		this.approximations = approximations;
	}

//	@Override
//	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
//		Move bestMove = legalMoves.get(0);
//		board.move(bestMove);
//		Double temp = approximations.get(StateAttributes.calculateState(board));
//		double bestValue = (temp == null)? 0: temp, value;
//		board.move(new Move(0, 0, -1));
//		for (Move move : legalMoves) {
//			board.move(bestMove);
//			temp = approximations.get(StateAttributes.calculateState(board));
//			value = (temp == null)? 0: temp;
//			if(value > bestValue){
//				bestMove = move;
//				bestValue = value;
//			}
//			board.move(new Move(0, 0, -1));
//		}
//		board.move(bestMove);
//		return bestMove;
//	}

	@Override
	public void update(Card[] upperCards) {
//		for (int i = 0; i < Board.numOfPiles; i++) {
//			try {
//				board.getCardPiles()[i].setTopToVisible();
//				board.getCardPiles()[i].removeCards(1);
//			} catch (IllegalMoveException e){}
//			board.getCardPiles()[i].addCard(upperCards[i]);
//		}
		System.out.println("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	@Override
	public void terminatePlayer() {
		// TODO what?????????????
	}

	@Override
	protected double evaluateState(Board board) throws IllegalMoveException {
		Double temp = approximations.get(StateAttributes.extendedStateCalculation(board));
		return (temp == null)? 0: temp;
	}

}
