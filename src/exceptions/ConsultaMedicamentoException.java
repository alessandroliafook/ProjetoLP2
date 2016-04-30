package exceptions;

public class ConsultaMedicamentoException extends Exception{

	/**
	 * Lanca Exception personalizada com a mensagem "Erro na consulta de medicamentos. " + motivo.
	 * 
	 * @param motivo
	 *            - Deve ser uma frase indicando qual o motivo que motivou a excecao.
	 */
	public ConsultaMedicamentoException(String motivo){
		super("Erro na consulta de medicamentos. " + motivo);
	}
	
}
