package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import logger.Log;
import exceptions.IllegalMoveException;

public class Board  implements Cloneable{

	public static final int numOfPiles = 8;
	public static final int numOfRounds = 2;
	public static final int numOfDecks = 4;
	private CardsPile[] cardPiles;
	private Card[][] extraCards;
	private int numOfDistributions;
	

	private Stack<Move> moves;
	private Stack<Card> series;
	private int numOfMoves;
	private int numOfFinishedSeries;
	private int numOfVisibleCards;

	public Board(){
		cardPiles = new CardsPile[numOfPiles];
		for (int i = 0; i < numOfPiles; i++) {
			cardPiles[i] = new CardsPile();
		}
		extraCards = new Card[numOfRounds][numOfPiles];
		moves = new Stack<Move>();
		series = new Stack<Card>();
		numOfDistributions = 0;
		numOfVisibleCards = 0;
	}

	public Board startGame() throws IllegalMoveException{
		Board output = new Board();
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < numOfDecks ; i++) {
			for (Card.Type type: Card.Type.values()) {
				for (Card.Number num: Card.Number.values()) {
					cards.add(new Card(type, num, false));
				}
			}
		}
		Collections.shuffle(cards);
		for (int i = 0; i < numOfRounds; i++) {
			for (int j = 0; j < numOfPiles; j++) {
				extraCards[i][j] = cards.remove(0);
				output.extraCards[i][j] = null;
			}
		}
		while(!cards.isEmpty()){
			for (int i = 0; i < cardPiles.length; i++) {
				if(cards.isEmpty()){
					break;
				}
				cardPiles[i].addCard(cards.remove(0));
				output.cardPiles[i].addCard(null);
			}
		}
		for (int i = 0; i < cardPiles.length; i++) {
			if(cardPiles[i].setTopToVisible()){
				numOfVisibleCards++;
			}
			output.cardPiles[i].removeCards(1);
			output.cardPiles[i].addCard(cardPiles[i].getCard(cardPiles[i].getSize() - 1).clone());
		}
		numOfMoves = 0;
		numOfFinishedSeries = 0;
		return output;
	}
	
	public void  fakeMoveResetData(int numOfMovesDiff){
		numOfMoves -= numOfMovesDiff;
	}

	public Card[] move(Move move, boolean exposeTopcards) throws IllegalMoveException{
		numOfMoves++;
		if(move.numOfCards == -1){//undo move
			if(moves.isEmpty()){
				throw new IllegalMoveException("there are no moves to undo");
			}
			Move lastMove = moves.pop();
			Card serie;
			if(lastMove.numOfCards == 0){// undo a distribution
				for (int i = numOfPiles - 1; i >= 0; i--) {
					serie = series.pop();
					if(serie != null){
						for (int j = Card.Number.values().length - 1; j >= 0; j--) {
							cardPiles[i].addCard(new Card(serie.type, Card.Number.values()[j], true));
						}
						numOfFinishedSeries--;
					}
					extraCards[numOfDistributions - 1][i] = cardPiles[i].removeCards(1).get(0);
				}
				numOfDistributions--;				
			}
			else{// undo a regular move
				serie = series.pop();
				if(serie != null){
					for (int j = Card.Number.values().length - 1; j >= 0; j--) {
						cardPiles[lastMove.dest].addCard(new Card(serie.type, Card.Number.values()[j], true));
					}
					numOfFinishedSeries--;
				}
				cardPiles[lastMove.src].addCards(cardPiles[lastMove.dest].removeCards(lastMove.numOfCards), true);				
			}
			//numOfMoves++;
			return null;
		}
		if(move.numOfCards == 0){// distribute cards
			if(numOfDistributions >= numOfRounds){
				throw new IllegalMoveException("all cards were distributed");
			}
			for (int i = 0; i < numOfPiles; i++) {
				cardPiles[i].addCard(extraCards[numOfDistributions][i]);
				if(exposeTopcards && cardPiles[i].setTopToVisible()){
					numOfVisibleCards++;
				}
				extraCards[numOfDistributions][i] = null;
				checkForSerie(i);
			}
			numOfDistributions++;
		}
		else{// move grope of cards
			if(move.src < 0 || move.src >= numOfPiles || move.dest < 0 || move.dest >= numOfPiles){
				throw new IllegalMoveException("the pile does not exist");
			}
			cardPiles[move.dest].addCards(cardPiles[move.src].removeCards(move.numOfCards), false);
			if(cardPiles[move.src].getSize() > 0){
				if(exposeTopcards && cardPiles[move.src].setTopToVisible()){
					numOfVisibleCards++;
				}
			}
			checkForSerie(move.dest);
		}	
		moves.push(move);
		//numOfMoves++;
		Card[] output = new Card[numOfPiles];
		for (int i = 0; i < output.length; i++) {
			if(cardPiles[i].getSize() > 0){
				output[i] = cardPiles[i].getCard(cardPiles[i].getSize() - 1).clone();
			}
		}
		return output;
	}

	private void checkForSerie(int index) throws IllegalMoveException {
		int removable = cardPiles[index].findNumOfRemovableCards();
		if(removable == Card.Number.values().length){
			series.push(new Card(cardPiles[index].getCard(cardPiles[index].getSize() - 1).type, Card.Number.ACE, false));
			cardPiles[index].removeCards(removable);
			numOfFinishedSeries++;
		}
		else{
			series.push(null);
		}
	}

	/**
	 * @return the numOfFinishedSeries
	 */
	public int getNumOfFinishedSeries() {
		return numOfFinishedSeries;
	}

	public List<Move> getLegalMoves() throws IllegalMoveException{
		List<Move> legalMoves = new ArrayList<Move>();
		if(!moves.empty()){
			legalMoves.add(new Move(0, 0, -1));
		}
		if(numOfDistributions < numOfRounds){
			legalMoves.add(new Move(0, 0, 0));
		}
		int numOfRemovableCards, srcSize, destSize;
		for (int i = 0; i < numOfPiles; i++) {
			srcSize = cardPiles[i].getSize();
			numOfRemovableCards = cardPiles[i].findNumOfRemovableCards();
			for (int j = 1; j <= numOfRemovableCards; j++) {
				for (int k = 0; k < numOfPiles; k++) {
					destSize = cardPiles[k].getSize();
					if(k != i){
						if(cardPiles[k].getSize() > 0 && cardPiles[k].getCard(destSize - 1) == null){
							System.out.println(cardPiles[k]);
							System.out.println("cardPiles[k].getSize() = " + cardPiles[k].getSize() + ", k = " + k);
						}
						if(cardPiles[k].getSize() == 0 || 
								cardPiles[i].getCard(srcSize - j).number.ordinal() == (cardPiles[k].getCard(destSize - 1).number.ordinal() - 1)){
							legalMoves.add(new Move(i, k, j));
						}
					}
				}
			}
		}
		return legalMoves;
	}

	public int getNumOfMoves() {
		return numOfMoves;
	}

	public int getScore() {
		if(getNumOfCardsOnBoard() == 0){
			//return (100 * numOfFinishedSeries) + 100;
			//return 500 - numOfMoves + (100 * numOfFinishedSeries) + 100;
			return 500 - numOfMoves +(int)((100 * numOfFinishedSeries) +((((double)(numOfMoves+1))/(double)numOfMoves))*(100));
		}
		//return  100 * numOfFinishedSeries;
		return 500 - numOfMoves + 100 * numOfFinishedSeries;
	}

	public int getNumOfVisibleCards() {
		return numOfVisibleCards;
	}

	public int getNumOfRemovableCards() {
		int output = 0;
		for (int i = 0; i < cardPiles.length; i++) {
			output += cardPiles[i].findNumOfRemovableCards();
		}
		return output;
	}

	public int getNumOfEmptyPiles() {
		int output = 0;
		for (int i = 0; i < cardPiles.length; i++) {
			output += (cardPiles[i].getSize() == 0)? 1: 0;
		}
		return output;
	}

	public int getNumOfCardsOnBoard() {
		return (numOfDecks * Card.Type.values().length - numOfFinishedSeries) * Card.Number.values().length;
	}
	
	public boolean isGameOver()
	{
		return getNumOfCardsOnBoard() == 0;
	}

	/**
	 * return the last move.
	 * 
	 * @return the last move from the moves stack.
	 */
	public Move getLastMove()
	{
		if(moves.isEmpty())
		{
			return null;
		}
		return moves.peek();
	}

	public Board clone(){
		Board newBoard = new Board();
		for (int i = 0; i < cardPiles.length; i++) {
			newBoard.cardPiles[i] = cardPiles[i].clone();
		}
		for (int i = 0; i < extraCards.length; i++) {
			for (int j = 0; j < extraCards[i].length; j++) {
				if(extraCards[i][j] != null)
				{
					newBoard.extraCards[i][j] = extraCards[i][j].clone();
				}
				else
				{
					newBoard.extraCards[i][j] = null;
				}
			}
		}
		newBoard.numOfDistributions = numOfDistributions;
		newBoard.numOfMoves = numOfMoves;
		newBoard.numOfFinishedSeries = numOfFinishedSeries;
		newBoard.numOfVisibleCards = numOfVisibleCards;
		newBoard.moves = (Stack<Move>) moves.clone();
		newBoard.series = (Stack<Card>) series.clone();
		return newBoard;
	}

	public String toString(){
		String output = "piles: \n";
		for (int i = 0; i < cardPiles.length; i++) {
			output += i+": "+cardPiles[i] + ": "+cardPiles[i].findNumOfRemovableCards()+", "+cardPiles[i].findNumOfRemovableCardsShapeUnSensetive()+"\n";
		}
		output += "extra cards (" + numOfDistributions + " distributions):\n";
		for (int i = 0; i < numOfRounds; i++) {
			output += "[";
			for (int j = 0; j < numOfPiles - 1; j++) {
				output += extraCards[i][j] + ", ";
			}
			output += extraCards[i][numOfPiles - 1] + "]\n";			
		}
		output += "**********************************************************************";
		return output;
	}

	public CardsPile[] getCardPiles() {
		return cardPiles;
	}

	public Card[][] getExtraCards() {
		return extraCards;
	}

	public int getnumOfInSequenceCards() {
		int output = 0;
		for (int i = 0; i < numOfPiles; i++) {
			if(cardPiles[i].findNumOfRemovableCards() > 1)
			{
				output += cardPiles[i].findNumOfRemovableCards()-1;
			}
		}
		return output;
	}

	public int getnumOfInSequenceCardsShapeUnSensetive() {
		int output = 0;
		for (int i = 0; i < numOfPiles; i++) {
			if(cardPiles[i].findNumOfRemovableCardsShapeUnSensetive() > 1)
			{
				output += cardPiles[i].findNumOfRemovableCardsShapeUnSensetive()-1;
			}
		}
		return output;
	}

	public int maxNumOfInSequenceCards() {
		int output = 0;
		for (int i = 0; i < numOfPiles; i++) {
			output = Math.max(output,cardPiles[i].findNumOfRemovableCardsShapeUnSensetive());
		}
		return output;
	}
	
	/**
	 * @return the numOfDistributions
	 */
	public int getNumOfDistributions() {
		return numOfDistributions;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(cardPiles);
		result = prime * result + Arrays.deepHashCode(extraCards);
		
		
		result = prime * result + ((moves == null) ? 0 : moves.hashCode());
		
		
		result = prime * result + numOfDistributions;
		result = prime * result + numOfFinishedSeries;
		result = prime * result + numOfMoves;
		result = prime * result + numOfVisibleCards;
		
		result = prime * result + ((series == null) ? 0 : series.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if ( !(obj instanceof Board) )
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(cardPiles, other.cardPiles))
			return false;
		if (!Arrays.deepEquals(extraCards, other.extraCards))
			return false;
		if (moves == null) {
			if (other.moves != null)
				return false;
		} else if (!moves.equals(other.moves))
			return false;
		if (numOfDistributions != other.numOfDistributions)
			return false;
		if (numOfFinishedSeries != other.numOfFinishedSeries)
			return false;
//		if (numOfMoves != other.numOfMoves)
//			return false;
		if (numOfVisibleCards != other.numOfVisibleCards)
			return false;
		if (series == null) {
			if (other.series != null)
				return false;
			System.out.println("7");
		} else if (!series.equals(other.series))
			return false;
		return true;
	}

}