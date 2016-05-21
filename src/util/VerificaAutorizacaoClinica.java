package util;

import pessoal.Funcionario;
import exceptions.CadastroFuncionarioException;
import exceptions.NaoAutorizadoException;

public class VerificaAutorizacaoClinica {

	/**
	 * Metodo que verifica se o usuario que esta logado eh um medico
	 * 
	 * @throws CadastroFuncionarioException
	 *             Caso nao tenha permissao
	 */
	public static void validaPermissao(Funcionario funcLogado, String acaoRealizada) throws NaoAutorizadoException {
		if (funcLogado != null && !isMedico(funcLogado)) {
			throw new NaoAutorizadoException(funcLogado.getNome(), acaoRealizada);
		}
	}

	/**
	 * Metodo que verifica se eh o medico que esta logado.
	 * 
	 * @return - True se for o diretor que esta logado, False do contrario
	 */
	private static boolean isMedico(Funcionario funcLogado) {
		return (funcLogado.getMatricula().charAt(0) == '2');
	}

}
