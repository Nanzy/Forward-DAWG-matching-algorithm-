package lasdException;

public class EmptyTreeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmptyTreeException(String err) {
            super (err);
    }
}
