package exceptions;

public class AtualizaFuncionarioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8390064411035193740L;

	/**
	 * Lanca Exception personalizada com a mensagem "Erro ao atualizar funcionario. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */

	public AtualizaFuncionarioException(String motivo){
		super("Erro ao atualizar funcionario. " + motivo);
	}
	
}
