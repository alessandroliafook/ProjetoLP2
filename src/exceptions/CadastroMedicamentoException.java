package exceptions;

public class CadastroMedicamentoException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8697099391305750827L;

	/**
	 * Lanca Exception personalizada com a mensagem "Erro no cadastro de medicamento. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */
	public CadastroMedicamentoException(String motivo){
		super("Erro no cadastro de medicamento. " + motivo);
	}
}
