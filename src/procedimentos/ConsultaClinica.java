package procedimentos;

import pessoal.Paciente;

public class ConsultaClinica implements ProcedimentoIF{

	private final double PRECO = 350.00;
	private final int PONTOS_BONUS = 50;

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
