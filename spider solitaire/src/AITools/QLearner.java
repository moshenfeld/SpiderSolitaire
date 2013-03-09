package AITools;

import game.ApproximationGameManager;
import heuristics.StateAttributes;
import java.util.HashMap;
import java.util.Map;

import player.factories.ApproximationAIFactory;

public class QLearner {

	public static Map<StateAttributes, Double> qLearning(ApproximationGameManager manager, int numOfEpisodes, double gamma, double decayRate)
														throws Exception{
		Map<StateAttributes, Double> approximations = new HashMap<StateAttributes, Double>();
		for (int i = 0; i < numOfEpisodes; i++) {
//			System.out.println("episode " + i);
			manager.runGame(approximations, true, Math.pow(decayRate, i), gamma, 1.0 / (i + 1));
		}
		return approximations;
	}
	
	private static double test(ApproximationGameManager manager, Map<StateAttributes, Double> map, int numOfEpisodes) throws Exception{
		double result = 0;
		for (int i = 0; i < numOfEpisodes; i++) {
			result += manager.runGame(map, false, 0, 0, 0);
		}
		return result / numOfEpisodes;
	}

	public static void main(String[] args) throws Exception{
		ApproximationAIFactory factory = new ApproximationAIFactory();
		ApproximationGameManager manager = new ApproximationGameManager(factory);
		System.out.println("result befor learning = " + test(manager, new HashMap<StateAttributes, Double>(), 100));
		Map<StateAttributes, Double> result = qLearning(manager, 2000, 0.8, 0.95);
		System.out.println("result befor learning = " + test(manager, result, 100));
	}
	
	
}
