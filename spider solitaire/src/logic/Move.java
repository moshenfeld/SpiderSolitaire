package logic;

public class Move implements Cloneable{

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dest;
		result = prime * result + numOfCards;
		result = prime * result + src;
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
		Move other = (Move) obj;
		if (dest != other.dest)
			return false;
		if (numOfCards != other.numOfCards)
			return false;
		if (src != other.src)
			return false;
		return true;
	}

	public int src;
	public int dest;
	public int numOfCards;

	public Move(int src, int dest, int numOfCards) {
		this.src = src;
		this.dest = dest;
		this.numOfCards = numOfCards;
	}

	public Move clone(){
		return new Move(src, dest, numOfCards);
	}

	public String toString(){
		return "( src - " + src + ", dest - " + dest + ", num - " + numOfCards + ")";
	}
	
	public boolean isUndoMove()
	{
		return this.numOfCards == -1; 
	}
}
