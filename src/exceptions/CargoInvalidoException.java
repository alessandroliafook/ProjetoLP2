package exceptions;

public class CargoInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CargoInvalidoException(String message){
		super(message);
	}
	
}
