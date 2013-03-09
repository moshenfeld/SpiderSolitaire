package heuristics;

public class AttributeWeigths {
	
	public static enum HeuristicsAttributes {
		numOfDistributions,numOfPosiableMoves,	numOfRemovableCards, numOfVisibleCards,
		numOfEmptyPiles ,numOfCardsOnBoard ,stateGameGrade, numOfInSequenceCards, numOfInSequenceCardsShapeUnSensetive, maxNumOfInSequenceCards 
		}
	public static final double[] minValues = {-100 ,-100, -100, -100, -100, -100, -100, -100, -100,-100};
	public static final double[] maxValues = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
	
//	public static final double[] minValues = {-100, -100, -100, -100, -100, -100, -100, -100,-100};
//	public static final double[] maxValues = {100, 100, 100, 100, 100, 100, 100, 100,100};

	private double[] weightArray;
	
	public AttributeWeigths(){
		this.weightArray = new double[HeuristicsAttributes.values().length];
	}

	public AttributeWeigths(double[] weightArray) throws Exception{
		this.weightArray = new double[HeuristicsAttributes.values().length];
		if(weightArray.length != this.weightArray.length){
			throw new Exception("wrong num of weights");
		}
		this.weightArray = weightArray;
	}
	
	/**
	 * @return the weightArray
	 */
	public double[] getWeightArray() {
		return weightArray;
	}

	public void initDefualtWeight() {
		weightArray[HeuristicsAttributes.numOfCardsOnBoard.ordinal()] = 0.3;
		weightArray[HeuristicsAttributes.numOfRemovableCards.ordinal()] = 0.2;
		weightArray[HeuristicsAttributes.numOfEmptyPiles.ordinal()] = 0.2;
		weightArray[HeuristicsAttributes.numOfPosiableMoves.ordinal()] = 0.2;
		weightArray[HeuristicsAttributes.numOfVisibleCards.ordinal()] = 0.2;
		
		weightArray[HeuristicsAttributes.stateGameGrade.ordinal()] = 0;
	}
	
}
