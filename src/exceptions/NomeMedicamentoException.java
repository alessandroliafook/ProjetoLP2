package exceptions;

public class NomeMedicamentoException extends Exception{

	public NomeMedicamentoException(){
		super("Nome do medicamento nao pode ser vazio.");
	}
	
}
