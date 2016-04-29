package util;

import exceptions.NumeroInvalidoException;
import exceptions.ObjetoInvalidoException;
import exceptions.StringInvalidaException;

public class Verificacao {

	/**
	 * Metodo que verifica se o numero inserido eh menor que zero.
	 * 
	 * @param numero
	 *            - Numero real a ser verificado.
	 * @param parametro
	 *            - Nome do parametro que esta sendo verificado.
	 * @throws NumeroInvalidoException
	 *             - Lanca excecao de Numero Invalido acaso o numero seja menor
	 *             que zero.
	 */
	public static void validaNumero(double numero, String parametro) throws NumeroInvalidoException {
		if (numero < 0) {
			String motivo = "eh menor que zero";
			throw new NumeroInvalidoException(parametro, motivo);
		}
	}

	/**
	 * Metodo que verifica se a String inserida eh igual a null ou vazia.
	 * 
	 * @param string
	 *            - String a ser verificada.
	 * @param parametro
	 *            - Nome do parametro que esta sendo verificado.
	 * @throws StringInvalidaException
	 *             - Lanca excecao de String Invalida caso a String seja igual a
	 *             null ou vazia, personalizando a mensagem para cada caso.
	 */
	public static void validaString(String string, String parametro) throws StringInvalidaException {

		if (string.equals(null)) {
			String motivo = "igual a null";
			throw new StringInvalidaException(parametro, motivo);

		} else if (string.trim().equals("")) {
			String motivo = "vazio";
			throw new StringInvalidaException(parametro, motivo);

		}
	}

	/**
	 * Metodo que verifica se o Objeto eh igual a null.
	 * 
	 * @param obj
	 *            - Objeto a ser verificado.
	 * @param parametro
	 *            - Nome do parametro que esta sendo verificado.
	 * @throws ObjetoInvalidoException
	 *             - Lanca excecao de ObjetoInvalido caso o objeto seja igual a
	 *             null
	 */
	public static void validaObjeto(Object obj, String parametro) throws ObjetoInvalidoException {

		if (obj == null) {
			String motivo = "igual a null";
			throw new ObjetoInvalidoException(parametro, motivo);
		}

	}

}
