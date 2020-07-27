package lasdException;

public class InvalidEntryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidEntryException(String err) {
            super (err);
    }
}
