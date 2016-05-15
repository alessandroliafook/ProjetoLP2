package exceptions;

public class RemoveOrgaoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -341610972009795071L;

	/**
	 * Lanca Exception personalizada com a mensagem "Erro na retirada de orgaos. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */

	public RemoveOrgaoException(String motivo){
		super("Erro na retirada de orgaos. " + motivo);
	}
	
}
