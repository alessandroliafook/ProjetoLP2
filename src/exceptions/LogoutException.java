package exceptions;

public class LogoutException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lanca Exception personalizada com a mensagem "Nao foi possivel realizar o logout. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */
	public LogoutException(String motivo){
		super("Nao foi possivel realizar o logout. " + motivo);
	}
	
}
	

