package exceptions;

public class MaisDeUmDiretorException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7960381269587862678L;

	public MaisDeUmDiretorException() {
		super("Nao eh possivel criar mais de um Diretor Geral.");
	}
	
}
