package exceptions;

public class NomeFuncionarioVazioException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public NomeFuncionarioVazioException(){
		super("Nome do funcionario nao pode ser vazio.");
	}
	
}
