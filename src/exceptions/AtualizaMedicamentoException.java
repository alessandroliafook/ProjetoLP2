package exceptions;

public class AtualizaMedicamentoException extends Exception{

	/**
	 * Lanca Exception personalizada com a mensagem "Erro ao atualizar medicamento. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */

	public AtualizaMedicamentoException(String motivo){
		super("Erro ao atualizar medicamento. " + motivo);
	}
	
}
