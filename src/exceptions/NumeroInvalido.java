package exceptions;

public class NumeroInvalido extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lanca Exception personalizada para numero com a mensagem - "X " +
	 * parametro + " eh invalidx, pois eh " + motivo + ".".
	 * 
	 * @param parametro
	 *            - indica qual o parametro que eh invalido.
	 * @param motivo
	 *            - indica qual o motivo que tornou o parametro invalido.
	 */
	public NumeroInvalido(String parametro, String motivo) {
		super("X " + parametro + " eh invalidx, pois eh " + motivo + ".");
	}
}
