package exceptions;

public class TipoMedicamentoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4995461952299065387L;

	public TipoMedicamentoException(){
		super("Tipo de medicamento invalido");
	}
}
