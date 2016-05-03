package util;

import exceptions.LiberaSistemaException;

public class VerificacaoLiberaSistema {

	/**
	 * Metodo que verifica se a validez da chave inserida para liberar o
	 * sistema.
	 * 
	 * @param chaveInserida
	 *            - String contendo a chave inserida.
	 * @param chave
	 *            - String contendo a chave correta.
	 * @throws LiberaSistemaException
	 *             - Lanca excecao caso a String com a chave seja invalida.
	 */
	public static void validaChave(String chaveInserida, String chave) throws LiberaSistemaException {
		if (chaveInserida == null || chaveInserida.trim().equals("") || !chaveInserida.trim().equals(chave)) {
			String motivo = "Chave invalida.";
			throw new LiberaSistemaException(motivo);
		}

	}

	/**
	 * Metodo que verifica se o sistema ja foi liberado
	 * 
	 * @param primeiroAcesso
	 *            - Boolean que informa se o sistema ja foi liberado.
	 * @throws LiberaSistemaException
	 *             - Lanca excecao caso o sistema ja tenha sido liberado.
	 */
	public static void validaAcesso(boolean primeiroAcesso) throws LiberaSistemaException {
		if (primeiroAcesso == true) {
			String motivo = "Sistema liberado anteriormente.";
			throw new LiberaSistemaException(motivo);
		}

	}

}
