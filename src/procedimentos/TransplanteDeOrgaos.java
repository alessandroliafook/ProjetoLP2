package procedimentos;

import pessoal.Paciente;

public class TransplanteDeOrgaos implements ProcedimentoIF{

	
	private final double PRECO = 12500.00;
	private final int PONTOS_BONUS = 160;

	/**
	 * Metodo que recebe 
	 */
	public double realizaProcedimento(Paciente paciente, double gastosComMedicamento){
				
		return this.PRECO;
	}

	@Override
	public int getPontosBonus() {
		return PONTOS_BONUS;
	}

	
}
