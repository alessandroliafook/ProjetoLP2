package exceptions;

public class NaoAutorizadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lanca Exception personalizada para numero com a mensagem - "X " +
	 * parametro + " eh invalidx, pois " + motivo + " para realizar esta tarefa.".
	 * 
	 * @param parametro
	 *            - indica qual o parametro que eh invalido.
	 * @param motivo
	 *            - indica qual o motivo que tornou o parametro invalido.
	 */
	public NaoAutorizadoException(String parametro, String motivo) {
		super("X " + parametro + " eh invalidx, pois " + motivo + " para realizar esta tarefa.");
	}
}
