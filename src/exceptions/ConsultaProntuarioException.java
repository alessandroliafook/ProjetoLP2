package exceptions;

public class ConsultaProntuarioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaProntuarioException(String message){
		super("Erro ao consultar prontuario. " + message);
	}
	
}


