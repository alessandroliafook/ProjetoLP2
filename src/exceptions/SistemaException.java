package exceptions;

public class SistemaException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lanca Exception personalizada com a mensagem "Nao foi possivel fechar o sistema. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */
	public SistemaException(String motivo){
		super("Nao foi possivel fechar o sistema. " + motivo);
	}
	
}
	

