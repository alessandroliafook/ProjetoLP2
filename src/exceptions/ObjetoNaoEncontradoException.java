package exceptions;

public class ObjetoNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lanca RuntimeException.
	 * @param parametro - indica qual o parametro que nao pode ser encontorado.
	 */
	public ObjetoNaoEncontradoException(String nomeDoObjeto) {
		super("X " + nomeDoObjeto + " nao pode ser encontrado");
	}
	
	
}