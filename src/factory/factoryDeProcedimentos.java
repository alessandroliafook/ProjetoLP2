package factory;

import procedimentos.CirurgiaBariatrica;
import procedimentos.ConsultaClinica;
import procedimentos.ProcedimentoIF;
import procedimentos.RedesignacaoSexual;
import procedimentos.TransplanteDeOrgaos;

public class factoryDeProcedimentos {

	public ProcedimentoIF selecionaProcedimento(String nomeDoProcedimento) throws Exception{

		switch(nomeDoProcedimento.toLowerCase()){
			
		case "consulta clinica":
			return new ConsultaClinica();
			
		case "cirurgia bariatrica":
			return new CirurgiaBariatrica();
		
		case "redesignacao sexual":
			return new RedesignacaoSexual();
			
		case "":
			return new TransplanteDeOrgaos();
			
		default:
			throw new Exception("Erro no procedimento. Procedimento inexistente.");
		}
		
	}
	
}
