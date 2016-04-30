package exceptions;

public class CadastroFuncionarioException extends Exception{


	/**
	 * Lanca Exception personalizada com a mensagem "Erro no cadastro de funcionario. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */
	public CadastroFuncionarioException(String motivo){
		super("Erro no cadastro de funcionario. " + motivo);
	}
}
