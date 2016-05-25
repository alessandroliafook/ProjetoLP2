package exceptions;

public class NaoAutorizadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5013837963122643993L;

	public NaoAutorizadoException(String nomeFuncionario, String acaoRealizada){
		super("O funcionario " + nomeFuncionario + " nao tem permissao para " + acaoRealizada);
	}
	
}
