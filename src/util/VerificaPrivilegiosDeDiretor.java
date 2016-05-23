package util;

import exceptions.CadastroFuncionarioException;
import exceptions.NaoAutorizadoException;
import pessoal.Funcionario;

public class VerificaPrivilegiosDeDiretor {

	/**
	 * Metodo que verifica se o usuario que esta logado tem permissao para
	 * realizar a tarefa
	 * 
	 * @param funcLogado
	 *            Funcionario que esta logado no sistema
	 * @param acaoRealizada
	 *            Descricao da acao realizada pelo funcionario
	 * @throws NaoAutorizadoException
	 *             Caso nao tenha permissao
	 */
	public static void validaPermissao(Funcionario funcLogado, String acaoRealizada) throws NaoAutorizadoException {

		if (funcLogado != null && !isDiretor(funcLogado)) {
			throw new NaoAutorizadoException(funcLogado.getNome(), acaoRealizada);
		}

	}

	private static boolean isDiretor(Funcionario funcLogado) {
		return (funcLogado.getMatricula().charAt(0) == '1');
	}

	/**
	 * Metodo que valida a existencia de um diretor para evitar a criacao de
	 * outro
	 * 
	 * @param cargo
	 *            Cargo a ser validado
	 * @param diretorGeral
	 *            Objeto referente ao diretor geral do sistema
	 * @throws Exception
	 *             Caso haja um diretor ja criado
	 */
	public static void verificaExistenciaDeDiretor(String cargo, Funcionario diretorGeral) throws Exception {
		if (diretorGeral != null && cargo.equals("diretor geral")) {
			throw new Exception("Nao eh possivel criar mais de um Diretor Geral.");
		}
	}

}
