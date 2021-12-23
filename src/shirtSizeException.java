
public class shirtSizeException extends RuntimeException{ // this Exception will help us catch the bad inputs of shirt sizes
	public shirtSizeException(String message) {
		super(message);
	}
	public shirtSizeException() {
		super();
	}
}
