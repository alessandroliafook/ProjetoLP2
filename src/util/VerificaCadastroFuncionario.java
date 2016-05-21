package util;

import exceptions.AtualizaFuncionarioException;
import exceptions.CadastroFuncionarioException;
import exceptions.ConsultaFuncionarioException;
import exceptions.SenhaInvalidaException;

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
	 *             - Lanca excecao caso a String com o nome do cargo do
	 *             funcionario seja vazia ou invalida.
	 */
	public static void validaCargo(String cargo) throws CadastroFuncionarioException {

		String motivo = "";

		// testa se o cargo eh vazio
		if (cargo.trim().equals("")) {
			motivo = "Nome do cargo nao pode ser vazio.";
			throw new CadastroFuncionarioException(motivo);
		}

		// testa se o cargo eh diferente dos cargos possiveis
		if (cargo.trim().equals("Diretor Geral") && cargo.trim().equals("Medico")
				&& cargo.trim().equals("Tecnico Administrativo")) {
			motivo = "Cargo invalido";
			throw new CadastroFuncionarioException(motivo);
		}
	}

	/**
	 * Metodo que verifica se a matrimedicamentocula do funcionario esta
	 * seguindo o padrao adotado e se este esta realmente matriculado
	 * 
	 * @param matricula
	 *            Matricula a ser verificada
	 * @throws ConsultaFuncionarioException
	 *             Caso nao esteja seguiundo o padrao, lanca excecao
	 */
	public static void validaMatricula(String matricula) throws Exception {
		boolean apenasNumeros = true;
		boolean tamanhoCorreto = true;

		for (Character c : matricula.toCharArray()) {
			if (!Character.isDigit(c))
				apenasNumeros = false;
		}
		if (!(tamanhoCorreto && apenasNumeros)) {
			throw new Exception("A matricula nao segue o padrao.");
		}
	}

	/**
	 * Metodo que valida a senha a ser atualizada
	 * 
	 * @param novaSenha
	 *            Senha a ser verificada
	 * @throws AtualizaFuncionarioException
	 *             Caso a senha nao siga o padrao estabelecido
	 */
	public static void validaSenha(String novaSenha) throws Exception {

		// A nova senha deve ter entre 8 e 12 caracteres alfanumericos.
		boolean tamanho = (novaSenha.length() >= 8 && novaSenha.length() <= 12);
		boolean alfanum = true;

		for (Character caractere : novaSenha.toCharArray()) {
			if (!Character.isAlphabetic(caractere) && !Character.isDigit(caractere)) {
				alfanum = false;
			}
		}

		if (!(tamanho && alfanum)) {
			throw new Exception("A nova senha deve ter entre 8 - 12 caracteres alfanumericos.");
		}
	}

	/**
	 * Confirma se a senha repassada eh realmente a senha do funcionario logado
	 * 
	 * @param senhaDoFuncionario
	 *            Senha do funcionario
	 * @param supostaSenha
	 *            Senha repassada
	 * @throws Exception
	 *             Caso a senha repessada nao seja de fato a senha do
	 *             funcionario
	 */
	public static void confirmaSenha(String senhaDoFuncionario, String supostaSenha) throws Exception {
		if (!senhaDoFuncionario.equals(supostaSenha)) {
			throw new SenhaInvalidaException();
		}
	}

}
