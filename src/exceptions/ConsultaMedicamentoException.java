package exceptions;

public class ConsultaMedicamentoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9207143006820112353L;

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
