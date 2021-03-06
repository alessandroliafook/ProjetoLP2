package util;

import pessoal.Funcionario;
import exceptions.NaoAutorizadoException;

public class VerificaAutorizacaoClinica {

	/**
	 * Metodo que verifica se o usuario que esta logado eh um medico
	 * 
	 * @param funcLogado
	 *            O funcionario que esta logado no sistema
	 * @param acaoRealizada
	 *            String com a descricao da acao que o funcionario tenta
	 *            realizar
	 * @throws NaoAutorizadoException
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
	 * @param funcLogado
	 *            O funcionario que esta logado no sistema
	 * @return True se for o diretor que esta logado, False do contrario
	 */
	private static boolean isMedico(Funcionario funcLogado) {
		return (funcLogado.getMatricula().charAt(0) == '2');
	}

}
