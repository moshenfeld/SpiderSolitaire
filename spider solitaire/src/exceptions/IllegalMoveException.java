package exceptions;

public class IllegalMoveException extends Exception {

	
	public String message;

	public IllegalMoveException(String message) {
		this.message = message;
	}

	private static final long serialVersionUID = 1L;
	

}
