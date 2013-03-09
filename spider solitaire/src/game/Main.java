package game;

import heuristics.AttributeWeigths;
import heuristics.AttributesEvaluator;
import heuristics.Evaluator;
import heuristics.StateAttributes;
import heuristics.WeightsEvaluator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import exceptions.IllegalMoveException;
import player.abstructPlayers.Player;
import player.players.CombinationAiPlayer;
import player.players.DeepAIPlayer;
import player.players.MemoryRoutePlayer;
import player.players.MemoryTimePlayer;
import player.players.NaiveAIPlayer;
import player.players.RandomPlayer;
import player.players.SimeplePlayer;
import player.players.UserPlayer;
import logic.Board;

public class Main {


	
	public static final long UPDATE_TIME = 100;
	public static final boolean UPDATE_VIEW = true;
	public static final boolean UPDATE_AT_KEY_PRESS = true;

	public static Window window;
	public static GameManager engine;

	public static void main(String args[]) throws Exception{
		// call viewer calc
		Board board = new Board();
		window = new Window(board);		
		window.addKeyListener(new KeyHandler());
		Player player;
		//player = runRandomPlayer(board);
		//player = runNaiveAIPlayer(board);
		//player = runUserPlayer(board);
		//player = runDeepAIPlayer(board);
		//player = runSimplePlayer(board);
		//player = runMemoryPlayer(board);
		player = runMemoryTimePlayer(board);
		
		
		//player = runCombinationPlayer(board);
		engine = new GameManager(board, player, window, UPDATE_VIEW, UPDATE_AT_KEY_PRESS);
		engine.run();
	}


	private static Player runQlearningAIPlayer(Board board) throws Exception 
	{
		double[] weights = {0, 0, 0, 0, 1, 1, 1,1};
		Evaluator evaluator = new AttributesEvaluator(new HashMap<StateAttributes, Double>());
		board.startGame();
		return new NaiveAIPlayer(board,evaluator);
	}
	
	private static Player runDeepAIPlayer(Board board) throws Exception{
		//double[] weights = {-25.010186269472666, 62.90206931842048, 1.6070191224846324, -7.543254185575145, -15.01901371327604, 16.338956225595176};
		double[] weights = {2,0, 0, 0, 0,0, 1, 3,0,0};
		Evaluator evaluator = new WeightsEvaluator(new AttributeWeigths(weights));
		board.startGame();
		return new DeepAIPlayer(board.clone(),evaluator,4);
	}

	private static Player runNaiveAIPlayer(Board board) throws Exception 
	{
		double[] weights = {0, 0, 0, 0, 1, 1, 1,1};
		Evaluator evaluator = new WeightsEvaluator(new AttributeWeigths(weights));
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
	
	private static Player runMemoryPlayer(Board board) throws IllegalMoveException
	{
		board.startGame();
		return new MemoryRoutePlayer(board,3);
	}
	
	private static Player runMemoryTimePlayer(Board board) throws IllegalMoveException
	{
		board.startGame();
		return new MemoryTimePlayer(board,5,200);
	}
	
	private static Player runCombinationPlayer(Board board) throws IllegalMoveException
	{
		
		board.startGame();
		Player a = new MemoryTimePlayer(board,5,500);
		Player b = new MemoryTimePlayer(board,7,5000);
		return new CombinationAiPlayer(board,a,b);
	}
	
	
	
	public static class KeyHandler extends KeyAdapter {
		
		@Override
        public void keyPressed(KeyEvent e){
        	int k = e.getKeyCode();		
        	if(k == KeyEvent.VK_ESCAPE)
        		System.exit(0);
        	if(k == KeyEvent.VK_ENTER)
        		engine.setUpdateAtKeyPress(!engine.isUpdateAtKeyPress());
    		else if(k == KeyEvent.VK_SPACE){
    			engine.setKeyPressed(true);
    		}
        }
		
		@Override
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_SPACE)
    			engine.setKeyPressed(false);
		}
    }
	
}