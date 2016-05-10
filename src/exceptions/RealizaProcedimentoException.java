package exceptions;

public class RealizaProcedimentoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo que lanca excecao acaso ocorra um erro de procedimento.
	 * @param motivo - Motivo que ensejou o erro de procedimento.
	 */
	public RealizaProcedimentoException(String motivo){
		super("Erro de procedimento. " + motivo);
	}
	
}
