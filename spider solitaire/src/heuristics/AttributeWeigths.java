package heuristics;

public class AttributeWeigths {
	
	private double[] weightArray;
	
	public AttributeWeigths(){
		this.weightArray = new double[StateAttributes.ExtendedHeuristicsAttributes.values().length];
	}

	public AttributeWeigths(double[] weightArray) throws Exception{
		this.weightArray = new double[StateAttributes.ExtendedHeuristicsAttributes.values().length];
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
		weightArray[StateAttributes.ExtendedHeuristicsAttributes.numOfCardsOnBoard.ordinal()] = 0.3;
		weightArray[StateAttributes.ExtendedHeuristicsAttributes.numOfRemovableCards.ordinal()] = 0.2;
		weightArray[StateAttributes.ExtendedHeuristicsAttributes.numOfEmptyPiles.ordinal()] = 0.2;
		weightArray[StateAttributes.ExtendedHeuristicsAttributes.numOfPosiableMoves.ordinal()] = 0.2;
		weightArray[StateAttributes.ExtendedHeuristicsAttributes.numOfVisibleCards.ordinal()] = 0.2;
		weightArray[StateAttributes.ExtendedHeuristicsAttributes.stateGameGrade.ordinal()] = 0;
	}
	
}
