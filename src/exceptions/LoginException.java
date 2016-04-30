package exceptions;

public class LoginException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lanca Exception personalizada com a mensagem "Erro no cadastro de funcionario. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */
	public LoginException(String motivo){
		super("Nao foi possivel realizar o login. " + motivo);
	}
	
}
	

