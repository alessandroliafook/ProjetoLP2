package exceptions;

public class ExclusaoFuncionarioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9207143006820112353L;

	/**
	 * Lanca Exception personalizada com a mensagem "Erro ao excluir funcionario. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */
	public ExclusaoFuncionarioException(String motivo){
		super("Erro ao excluir funcionario. " + motivo);
	}
	
}
