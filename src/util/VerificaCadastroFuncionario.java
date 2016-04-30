package util;

import exceptions.CadastroFuncionarioException;

public class VerificaCadastroFuncionario {

	/**
	 * Metodo que verifica se o nome do medicamento eh vazio.
	 * 
	 * @param nomeMedicamento
	 *            - String contendo o nome do medicamento.
	 * @throws CadastroMedicamentoException
	 *             - Lanca excecao caso a String com o nome do medicamento seja
	 *             vazia.
	 */
	public static void validaNomeFuncionario(String nomeFuncionario) throws CadastroFuncionarioException {
		if (nomeFuncionario.trim().equals("")) {
			String motivo = "Nome do funcionario nao pode ser vazio.";
			throw new CadastroFuncionarioException(motivo);
		}
	}

	/**
	 * Metodo que verifica se o nome do medicamento eh vazio.
	 * 
	 * @param nomeMedicamento
	 *            - String contendo o nome do medicamento.
	 * @throws CadastroMedicamentoException
	 *             - Lanca excecao caso a String com o nome do medicamento seja
	 *             vazia.
	 */
	public static void validaDataFuncionario(String dataNascimento) throws CadastroFuncionarioException {
		
	}

}
