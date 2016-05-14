package procedimentos;

import pessoal.Paciente;

public class CirurgiaBariatrica implements ProcedimentoIF{

	public final double PRECO = 7600.00;
	private final int PONTOS_BONUS = 100;
	
	@Override
	public double realizaProcedimento(Paciente paciente,
			double gastosComMedicamento) {
		
		double novoPeso = paciente.getPeso() * 0.85;
		paciente.setPeso(novoPeso);
		
		return this.PRECO;
	}

	@Override
	public int getPontosBonus() {
		return PONTOS_BONUS;
	}

	
}
