package exceptions;

public class BancoDeOrgaosException extends Exception{

	/**
	 * Lanca Exception personalizada com a mensagem "O banco de orgaos apresentou um erro. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */

	public BancoDeOrgaosException(String motivo){
		super("O banco de orgaos apresentou um erro. " + motivo);
	}
	
}
