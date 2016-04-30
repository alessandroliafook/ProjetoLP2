package util;

import java.awt.geom.NoninvertibleTransformException;

import exceptions.NaoAutorizadoException;
import exceptions.NovoNomeInvalidoException;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoInvalidoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.StringInvalidaException;
import medicamento.CategoriasEnum;

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
	public static void validaNumeroReal(double numero, String parametro) throws NumeroInvalidoException {
		if (numero < 0) {
			String motivo = "eh menor que zero";
			throw new NumeroInvalidoException(parametro, motivo);
		}
	}

	/**
	 * Metodo que verifica se o numero inserido eh menor que zero.
	 * 
	 * @param numero
	 *            - Numero inteiro a ser verificado.
	 * @param parametro
	 *            - Nome do parametro que esta sendo verificado.
	 * @throws NumeroInvalidoException
	 *             - Lanca excecao de Numero Invalido acaso o numero seja menor
	 *             que zero.
	 */
	public static void validaNumeroInteiro(double numero, String parametro) throws NumeroInvalidoException {
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


	/**
	 * Metodo que verifica se um funcionario possui privilegios para realizar
	 * uma tarefa especifica.
	 * 
	 * @param matricula
	 *            - Matricula do funcionario a ser verificado
	 * @param parametro
	 *            - Nome da variavel
	 * @throws NaoAutorizadoException
	 *             - Caso o funcionario nao possua privilegios
	 */
	public static void validaAutorizacao(String matricula, String parametro) throws NaoAutorizadoException {

		if (matricula.charAt(0) != '1') {
			throw new NaoAutorizadoException(parametro, "nao possui autorizacao");
		}

	}

	/**
	 * Metodo que lanca excecao se o novo nome para o qual se quer atualizar eh
	 * invalido.
	 * 
	 * @param novoNome
	 *            - Nome a ser atualizado
	 * @return - True caso seja valido, False do contrario.
	 */
	public static void validaNovoNome(String novoNome) throws NovoNomeInvalidoException, StringInvalidaException {

		validaString(novoNome, "novo nome do funcionario");

		if (!(isNovoNomeValido(novoNome))) {
			throw new NovoNomeInvalidoException(novoNome, "nao eh compativel com o padrao esperado");
		}
	}
	/**
	 * Metodo que verifica se o novo nome para o qual se quer atualizar eh
	 * valido.
	 * 
	 * @param novoNome
	 *            - Nome a ser atualizado
	 * @return - True caso seja valido, False do contrario.
	 */
	private static boolean isNovoNomeValido(String novoNome) {

		boolean validadeNovoNome = true;

		if (novoNome.length() < 1 || novoNome.length() > 16) {
			validadeNovoNome = false;
		}

		for (Character c : novoNome.toCharArray()) {
			if (!Character.isAlphabetic(c)) {
				validadeNovoNome = false;
			}
		}

		return validadeNovoNome;
	}
}
