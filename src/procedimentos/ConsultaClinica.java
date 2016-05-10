package procedimentos;

import pessoal.Paciente;

public class ConsultaClinica implements ProcedimentoIF{

	public String realizaProcedimento(Paciente paciente){
		
		
		return this.toString();
	}
	
	public String toString(){
		return "Consulta Clinica";
	}
}
