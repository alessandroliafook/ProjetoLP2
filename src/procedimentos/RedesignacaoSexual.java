package procedimentos;

import java.io.Serializable;

import pessoal.Paciente;

public class RedesignacaoSexual implements ProcedimentoIF, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3127060742079774171L;
	
	public final double PRECO = 9300.00;
	private final int PONTOS_BONUS = 130;
	
	/**
	 * Metodo que recebe 
	 */
	public double realizaProcedimento(Paciente paciente, double gastosComMedicamento){
		
		String generoAtual = paciente.getGenero();
		String novoGenero = (generoAtual.equalsIgnoreCase("masculino")) ? "feminino" : "masculino";
		paciente.setGenero(novoGenero);
		
		return this.PRECO;
	}

	@Override
	public int getPontosBonus() {
		return PONTOS_BONUS;
	}
}
