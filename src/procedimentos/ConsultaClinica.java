package procedimentos;

import pessoal.Paciente;

public class ConsultaClinica implements ProcedimentoIF{

	private final double PRECO = 350.00;

	/**
	 * Metodo que recebe 
	 */
	public double realizaProcedimento(Paciente paciente, double gastosComMedicamento){
				
		return this.PRECO;
	}
	
}
