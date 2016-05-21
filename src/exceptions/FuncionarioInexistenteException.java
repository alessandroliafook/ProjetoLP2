package exceptions;

public class FuncionarioInexistenteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3513700383411064210L;

	public FuncionarioInexistenteException(){
		super("Funcionario nao cadastrado.");
	}
	
}
