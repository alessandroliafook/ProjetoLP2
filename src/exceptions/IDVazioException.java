package exceptions;

public class IDVazioException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8019552735466427441L;

	public IDVazioException(){
		super("ID do paciente nao pode ser vazio.");
		
	}
	
}
