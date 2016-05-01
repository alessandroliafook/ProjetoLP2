package exceptions;

public class NomeMedicamentoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7734897174413726849L;

	public NomeMedicamentoException(){
		super("Nome do medicamento nao pode ser vazio.");
	}
	
}
