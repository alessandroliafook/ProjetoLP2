package exceptions;

public class CadastroPacienteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CadastroPacienteException(String message){
		super("Nao foi possivel cadastrar o paciente. " + message);
	}
}
