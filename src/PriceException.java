
public class PriceException extends RuntimeException { // we made an exception if the price input is negative or not natural
	public PriceException(String message) {
		super(message);
	}
	public PriceException() {
		super();
	}
}
