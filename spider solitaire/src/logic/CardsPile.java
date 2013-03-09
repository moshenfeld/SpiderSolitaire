package logic;

import java.util.ArrayList;
import java.util.List;

import exceptions.IllegalMoveException;


public class CardsPile  implements Cloneable{

	private List<Card> cards;
	
	public CardsPile(){
		cards = new ArrayList<Card>();
	}
	
	public void addCards(List<Card> newCards, boolean isUndo) throws IllegalMoveException{
		for (int i = 1; i < newCards.size(); i++) {
			if(!newCards.get(i).type.equals(newCards.get(i - 1).type)){
				throw new IllegalMoveException("the moved cards are not all of the same type");
			}
			if(newCards.get(i).number.ordinal() != (newCards.get(i - 1).number.ordinal() - 1)){
				throw new IllegalMoveException("the moved cards are not organized in the right order");
			}
		}
		if(!isUndo && cards.size() != 0 && newCards.get(0).number.ordinal() != (cards.get(cards.size() - 1).number.ordinal() - 1)){
			throw new IllegalMoveException("the number of the card at the bottom of the moved cards is not one above the number of the top card in the pile");
		}
		cards.addAll(newCards);
	}

	public void addCard(Card newCard){
		cards.add(newCard);
	}

	public List<Card> removeCards(int numOfCards) throws IllegalMoveException{
		if(numOfCards > findNumOfRemovableCards()){
			System.out.println(numOfCards + ", " + this);
			throw new IllegalMoveException("cannot move that meny cards, they are not of the same type");
		}
		if(numOfCards <= 0){
			throw new IllegalMoveException("cannot move less then one card");
		}
		List<Card> output = new ArrayList<Card>();
		output.addAll(cards.subList(cards.size() - numOfCards, cards.size()));
		for (int i = 0; i < numOfCards; i++) {
			cards.remove(cards.size() - 1);
		}
		return output;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardsPile other = (CardsPile) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}

	public int findNumOfRemovableCardsShapeUnSensetive() {
		if(cards.isEmpty()){
			return 0;
		}
		int output = 1;
		for (int i = cards.size() - 2; i >= 0; i--) {
			if(cards.get(i)== null || !cards.get(i).isVisible() || 
					cards.get(i).number.ordinal() != cards.get(i + 1).number.ordinal() + 1){
				break;
			}
			output++;
		}
		return output;
	}

	public int findNumOfRemovableCards() {
		if(cards.isEmpty()){
			return 0;
		}
		int output = 1;
		for (int i = cards.size() - 2; i >= 0; i--) {
			if(cards.get(i)== null || !cards.get(i).type.equals(cards.get(cards.size() - 1).type) || !cards.get(i).isVisible() || 
					cards.get(i).number.ordinal() != cards.get(i + 1).number.ordinal() + 1){
				break;
			}
			output++;
		}
		return output;
	}

	public int getSize() {
		return cards.size();
	}

	public Card getCard(int index) throws IllegalMoveException {
		if(index < 0 || index >= cards.size()){
			throw new IllegalMoveException("no soch index: " + index);
		}
		return cards.get(index);
	}

	public boolean setTopToVisible(){
		if((cards.size() > 0) && (!cards.get(cards.size() - 1).isVisible())){
			cards.get(cards.size() - 1).setVisible(true);
			return true;
		}
		return false;
	}
	
	public CardsPile clone(){
		CardsPile newPile = new CardsPile();
		for (int i = 0; i < cards.size(); i++) {
			newPile.cards.add(cards.get(i).clone());
		}
		return newPile;
	}
	
	public String toString(){
		String output = "[";
		for (int i = 0; i < cards.size() - 1; i++) {
			output += cards.get(i) + ", ";
		}
		if(!cards.isEmpty()){
			output += cards.get(cards.size() - 1);
		}
		output += "]";
		return output;
	}
	
}
