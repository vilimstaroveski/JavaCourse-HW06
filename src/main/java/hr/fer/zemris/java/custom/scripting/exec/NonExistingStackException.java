package hr.fer.zemris.java.custom.scripting.exec;

public class NonExistingStackException extends RuntimeException{

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public NonExistingStackException(String message) {
		super(message);
	}
}
