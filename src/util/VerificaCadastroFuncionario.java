package util;

import exceptions.CadastroFuncionarioException;

public class VerificaCadastroFuncionario {

	/**
	 * Metodo que verifica se o nome do medicamento eh vazio.
	 * 
	 * @param nomeFuncionario
	 *            - String contendo o nome do funcionario.
	 * @throws CadastroFuncionarioException
	 *             - Lanca excecao caso a String com o nome do funcionario seja
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
	 * @param cargo
	 *            - String contendo o nome do cargo do funcionario.
	 * @throws CadastroFuncionarioException
	 *             - Lanca excecao caso a String com o nome do cargo do funcionario seja
	 *             vazia ou invalida.
	 */
	public static void validaCargo(String cargo) throws CadastroFuncionarioException {
		
		String motivo = "";
		
		//testa se o cargo eh vazio
		if (cargo.trim().equals("")) {
			motivo = "Nome do cargo nao pode ser vazio.";
			throw new CadastroFuncionarioException(motivo);
		}
		
		//testa se o cargo eh diferente dos cargos possiveis
		if (cargo.trim().equals("Diretor Geral") && cargo.trim().equals("Medico") && 
				cargo.trim().equals("Tecnico Administrativo")) {
			motivo = "Cargo invalido";
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
