package player.players;

import java.util.List;

import logic.Board;
import logic.Card;
import logic.Move;
import exceptions.IllegalMoveException;
import player.abstructPlayers.Player;

public class CombinationAiPlayer extends Player{

	Player PlayerA,playerB;

	public CombinationAiPlayer(Board board, Player playerA, Player playerB) {
		super(board);
		PlayerA = playerA;
		this.playerB = playerB;
	}

	@Override
	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
		Move move = null;
		if(Math.random() > 0.5)
		{
			PlayerA.setBoard(this.board);
			move = PlayerA.getNextMove(legalMoves);
		}
		else{
			playerB.setBoard(this.board);
			move = playerB.getNextMove(legalMoves);
		}
		if(move != null)
		{
			this.board.move(move, false);
		}
		return move;
	}

	@Override
	public void update(Card[] upperCards) {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminatePlayer() {
		// TODO Auto-generated method stub

	}

}