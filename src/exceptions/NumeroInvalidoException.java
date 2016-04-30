package exceptions;

public class NumeroInvalidoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8265165624835915512L;

	public NumeroInvalidoException(String parametro){
		super(parametro + " nao pode ser negativo.");
	}

}
