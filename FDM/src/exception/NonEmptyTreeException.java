package exception;

public class NonEmptyTreeException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NonEmptyTreeException(String err) {
            super (err);
    }
}
