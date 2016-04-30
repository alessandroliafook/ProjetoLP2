package exceptions;

public class CadastroMedicamentoException extends Exception{


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
