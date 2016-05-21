package factory;

import java.io.Serializable;

import procedimentos.CirurgiaBariatrica;
import procedimentos.ConsultaClinica;
import procedimentos.ProcedimentoIF;
import procedimentos.RedesignacaoSexual;
import procedimentos.TransplanteDeOrgaos;

public class factoryDeProcedimentos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2446489558634138858L;
	
	/**
	 * Cria um novo objeto ProcedimentoIF cujo nome seja igual a string
	 * fornecida
	 * 
	 * @param nomeDoProcedimento
	 *            Nome do ProcedimentoIF a ser criado. Nomes aceitos(consulta
	 *            clinica/cirurgia bariatrica/redesignacao sexual/transplante de
	 *            orgaos)
	 * @return Um objeto do tipo ProcedimentoIF de nome igual a string fornecida
	 * @throws Exception
	 *             Caso o nome do procedimento fornecido nao esteja entra os
	 *             nomes aceitos
	 */
	public ProcedimentoIF selecionaProcedimento(String nomeDoProcedimento)
			throws Exception {

		switch (nomeDoProcedimento.toLowerCase()) {

		case "consulta clinica":
			return new ConsultaClinica();

		case "cirurgia bariatrica":
			return new CirurgiaBariatrica();

		case "redesignacao sexual":
			return new RedesignacaoSexual();

		case "transplante de orgaos":
			return new TransplanteDeOrgaos();

		default:
			throw new Exception("Procedimento invalido.");
		}

	}

}
