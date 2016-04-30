package exceptions;

public class LiberaSistemaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8390064411035193740L;

	/**
	 * Lanca Exception personalizada com a mensagem "Erro ao liberar o sistema. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */

	public LiberaSistemaException(String motivo){
		super("Erro ao liberar o sistema. " + motivo);
	}
	
}
