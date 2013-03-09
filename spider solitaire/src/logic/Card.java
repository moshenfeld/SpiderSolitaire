package logic;

public class Card  implements Cloneable{

	public static enum Type{CLUBS, SPADES}//, HEARTS, DIAMONDS}
	public static enum Number{ACE, TWO, THREE, FOUR}//, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

	
	public final Type type;
	public final Number number;
	private boolean isVisible;
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Card(Type type, Number number, boolean isVisible){
		this.type = type;
		this.number = number;
		this.isVisible = isVisible;
	}
	
	public Card clone(){
		return new Card(type, number, isVisible);
	}
	
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (this.type != other.type || this.number != other.number || this.isVisible != other.isVisible)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.number.ordinal();
		return result;
	}
	
	public String toString(){
		return "(" + type + ", " + number + ", " + isVisible + ")";
	}
}
