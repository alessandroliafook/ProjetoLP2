package util;

import exceptions.NaoAutorizadoException;
import pessoal.Funcionario;

public class VerificaAutorizacaoFarmacia {

	public static void validaPermissao(Funcionario funcLogado, String acaoRealizada) throws NaoAutorizadoException{
		if(!isDiretorOuTecnico(funcLogado)){
			throw new NaoAutorizadoException(funcLogado.getNome(), acaoRealizada);
		}
	}
	
	private static boolean isDiretorOuTecnico(Funcionario funcLogado) {
		char caractere = funcLogado.getMatricula().charAt(0);
		return (caractere == '1' || caractere == '3');
	}
	
}
