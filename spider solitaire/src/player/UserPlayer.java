package player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import logic.Board;
import logic.Card;
import logic.Move;
import exceptions.IllegalMoveException;

public class UserPlayer extends Player{
	private BufferedReader br;

	public UserPlayer(Board board)
	{
		super(board);
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public Move getNextMove(List<Move> legalMoves) throws IllegalMoveException {
		System.out.println(board);
		for (int i = 0; i < legalMoves.size(); i++) {
			System.out.println("move("+i+"): "+legalMoves.get(i));
		}

		System.out.println("Pick a move:");
		String input ="";

		try {
			input = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int choice = Integer.parseInt(input);
		if(choice <= legalMoves.size()-1 && choice >= 0)
		{
			return legalMoves.get(choice); 
		}
		return legalMoves.get(0);
	}

	@Override
	public void update(Card[] upperCards) {
		// TODO Auto-generated method stub

	}


	@Override
	public void terminatePlayer() {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Move move, Card[] upperCards)
			throws IllegalMoveException {
		// TODO Auto-generated method stub
		
	}


}
