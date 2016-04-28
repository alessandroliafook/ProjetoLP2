package exceptions;

public class ObjetoInvalido extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lanca RuntimeException.
	 * @param parametro - indica qual o parametro que eh invalido.
	 * @param motivo - indica qual o motivo que tornou o parametro invalido.
	 */
	public ObjetoInvalido(String parametro, String motivo){
		super("X " + parametro + " eh invalidx, pois eh " + motivo + ".");
	}
	
}
