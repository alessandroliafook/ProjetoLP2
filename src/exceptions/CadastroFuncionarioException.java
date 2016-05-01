package exceptions;

public class CadastroFuncionarioException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5373300271880215368L;

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
