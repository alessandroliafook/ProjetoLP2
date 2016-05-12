package factory;

import procedimentos.CirurgiaBariatrica;
import procedimentos.ConsultaClinica;
import procedimentos.ProcedimentoIF;
import procedimentos.RedesignacaoSexual;
import procedimentos.TransplanteDeOrgaos;

public class factoryDeProcedimentos {

	public ProcedimentoIF selecionaProcedimento(String nomeDoProcedimento) throws Exception{
		
		switch(nomeDoProcedimento.toLowerCase()){
		
		case "consultaclinica":
			return new ConsultaClinica();
			
		case "cirurgiabariatrica":
			return new CirurgiaBariatrica();
		
		case "redesignacaosexual":
			return new RedesignacaoSexual();
			
		case "":
			return new TransplanteDeOrgaos();
			
		default:
			throw new Exception("Erro no procedimento. Procedimento inexistente.");
		}
		
	}
	
}
