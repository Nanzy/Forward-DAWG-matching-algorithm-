package map;

public class InvalidKeyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidKeyException(String err) {
            super (err);
    }
}
