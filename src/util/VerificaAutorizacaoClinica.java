package util;

import pessoal.Funcionario;
import exceptions.CadastroFuncionarioException;

public class VerificaAutorizacaoClinica {

	/**
	 * Metodo que verifica se o usuario que esta logado eh um medico
	 * 
	 * @throws CadastroFuncionarioException
	 *             Caso nao tenha permissao
	 */
	public static void validaPermissao(Funcionario funcLogado) throws CadastroFuncionarioException {
		if (funcLogado != null && !isMedico(funcLogado)) {
			throw new CadastroFuncionarioException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para realizar procedimentos.");
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
