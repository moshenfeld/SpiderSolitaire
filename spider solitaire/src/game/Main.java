package game;

import heuristics.AttributeWeigths;
import heuristics.Evaluator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import exceptions.IllegalMoveException;
import player.DeepAIPlayer;
import player.MemoryRoutePlayer;
import player.NaiveAIPlayer;
import player.Player;
import player.RandomPlayer;
import player.SimeplePlayer;
import player.UserPlayer;
import logic.Board;

public class Main {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	public static Window window;
	public static GameManager engine;
		
	public static void main(String args[]) throws Exception{
		Board board = new Board();
		window = new Window(board, WIDTH, HEIGHT);		
		window.addKeyListener(new KeyHandler());
		Player player;
		//player = runRandomPlayer(board);
		//player = runNaiveAIPlayer(board);
		//player = runUserPlayer(board);
		//player = runDeepAIPlayer(board);
		//player = runSimplePlayer(board);
		player = runMemoryPlayer(board);
		
		engine = new GameManager(board, player, window, true, true);
		new Thread(engine).start();
	}

	private static Player runQlearningAIPlayer(Board board) throws Exception 
	{
		double[] weights = {0, 0, 0, 0, 1, 1, 1,1};
		Evaluator evaluator = new Evaluator(new AttributeWeigths(weights));
		board.startGame();
		return new NaiveAIPlayer(board,evaluator);
	}
	
	private static Player runDeepAIPlayer(Board board) throws Exception{
		//double[] weights = {-25.010186269472666, 62.90206931842048, 1.6070191224846324, -7.543254185575145, -15.01901371327604, 16.338956225595176};
		double[] weights = {2,0, 0, 0, 0,0, 1, 3,0,0};
		Evaluator evaluator = new Evaluator(new AttributeWeigths(weights));
		board.startGame();
		return new DeepAIPlayer(board.clone(),evaluator,4);
	}

	private static Player runNaiveAIPlayer(Board board) throws Exception 
	{
		double[] weights = {0, 0, 0, 0, 1, 1, 1,1};
		Evaluator evaluator = new Evaluator(new AttributeWeigths(weights));
		board.startGame();
		return new NaiveAIPlayer(board,evaluator);
	}
	
	private static Player runRandomPlayer(Board board) throws IllegalMoveException{
		return new RandomPlayer(board.startGame());	
	}
	
	private static Player runUserPlayer(Board board) throws IllegalMoveException{
		board.startGame();
		return new UserPlayer(board);	
	}
	
	private static Player runSimplePlayer(Board board) throws IllegalMoveException
	{
		board.startGame();
		return new SimeplePlayer(board);
	}
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	private static Player runMemoryPlayer(Board board) throws IllegalMoveException
	{
		board.startGame();
		return new MemoryRoutePlayer(board,3);
	}
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	
	public static class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
        	if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        		System.exit(0);
            engine.keyPressed(e);
        }
    }
}