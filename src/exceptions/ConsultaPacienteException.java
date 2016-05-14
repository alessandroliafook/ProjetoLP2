package exceptions;

public class ConsultaPacienteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7569300268379936384L;

	public ConsultaPacienteException(){
		super("Erro de consulta. O paciente nao esta cadastrado no sistema.");
	}
	
}
