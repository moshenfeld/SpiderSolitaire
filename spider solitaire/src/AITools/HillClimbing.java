package AITools;

import java.util.Arrays;

import player.factories.EvaluationAIFactory;
import player.factories.NaiveAIFactory;
import game.EvaluatorGameManager;
import heuristics.AttributeWeigths;
import heuristics.StateAttributes;

public class HillClimbing {
	
	public static double[] RRhillClimbing(EvaluatorGameManager manager, double decayRate, int numOfTrips) throws Exception {
		double[] bestState = simulatedAnnealing(manager, pickState(), decayRate), newState;
		double bestValue = manager.runGame(bestState), newValue;
		for (int i = 0; i < numOfTrips; i++) {
			newState = simulatedAnnealing(manager, pickState(), decayRate);
			newValue = manager.runGame(newState);
			if(newValue > bestValue){
				bestValue = newValue;
				bestState = newState;
			}
			System.out.println("RRhillClimbing: " + bestValue);
		}
		System.out.println("RRhillClimbing - final value: " + bestValue);
		return bestState;
	}

	private static double[] pickState() {
		double[] minValues = StateAttributes.MIN_VALUES, maxValues = StateAttributes.MAX_VALUES;
		double[] state = new double[minValues.length];
		for (int i = 0; i < state.length; i++) {
			state[i] = minValues[i] + Math.random() * (maxValues[i] - minValues[i]);
		}
		return state;
	}

	public static double[] simulatedAnnealing(EvaluatorGameManager manager, double[] oldState, double decayRate) throws Exception {
		double[] newState = new double[oldState.length];
		double oldValue = manager.runGame(oldState), newValue = 0, temprature = 1, diffValue;
		int timesNotChanged = 0;
		while(timesNotChanged < 5){
			newState = mutate(oldState, temprature);
//			System.out.print("newState: [");
//			for (int i = 0; i < newState.length - 1; i++) {
//				System.out.print(newState[i] + ", ");
//			}
//			System.out.println(newState[newState.length - 1] + "]");
			newValue = manager.runGame(newState);
			diffValue = newValue - oldValue;
			temprature *= (1 - decayRate);
			//System.out.println("diff: "+diffValue+", temp"+temprature+", random: "+Math.random()+", exp:"+Math.exp(diffValue / temprature));
			double divSquare = diffValue*diffValue;
			if(diffValue > 0 || divSquare*Math.random() < divSquare*Math.exp(diffValue / temprature)){
				//System.out.println("oldval: "+oldValue+" newVal: "+newValue+" temp: "+temprature+" ^^^^^^^^^^^^^^^^ change ^^^^^^");
				
				oldState = newState;
				oldValue = newValue;
				timesNotChanged = 0;
				
			}
			else{
				//System.out.println("oldval: "+oldValue+" newVal: "+newValue+" temp: "+temprature);
				timesNotChanged++;
			}
			System.out.println("simulatedAnnealing: " + oldValue + ", timeNotChanged: " + timesNotChanged);
		}
		return newState;
	}

	private static double[] mutate(double[] oldState, double temprature) {
		double[] minValues = StateAttributes.MIN_VALUES, maxValues = StateAttributes.MAX_VALUES;
		double[] intervals = new double[minValues.length];
		for (int i = 0; i < intervals.length; i++) {
			intervals[i] = (maxValues[i] - minValues[i]) / 100;
		}
		double[] newState = new double[oldState.length];
		for (int i = 0; i < newState.length; i++) {
			newState[i] = Math.max(minValues[i], Math.min(maxValues[i], oldState[i] + intervals[i] * (2 * Math.random() - 1) * temprature));
		}
		return newState;
	}
	
	public static void main(String[] args) throws Exception{
		EvaluationAIFactory factory = new NaiveAIFactory();
		EvaluatorGameManager manager = new EvaluatorGameManager(factory);
		double[] result = RRhillClimbing(manager, 0.01, 20);
		System.out.println("result:\n"+Arrays.toString(result));
	}
}
