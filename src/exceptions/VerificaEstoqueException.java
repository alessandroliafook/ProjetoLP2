package exceptions;

public class VerificaEstoqueException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8281941237519187060L;

	public VerificaEstoqueException(){
		super("Medicamento nao cadastrado.");
	}
	
}
