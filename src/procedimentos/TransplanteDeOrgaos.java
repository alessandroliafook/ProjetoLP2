package procedimentos;

import pessoal.Paciente;

public class TransplanteDeOrgaos implements ProcedimentoIF{

	
	private final double PRECO = 12500.00;

	/**
	 * Metodo que recebe 
	 */
	public double realizaProcedimento(Paciente paciente, double gastosComMedicamento){
				
		return this.PRECO;
	}

	
}
