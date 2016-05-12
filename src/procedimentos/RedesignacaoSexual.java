package procedimentos;

import pessoal.Paciente;

public class RedesignacaoSexual implements ProcedimentoIF {

	public final double PRECO = 9300.00;
	
	/**
	 * Metodo que recebe 
	 */
	public double realizaProcedimento(Paciente paciente, double gastosComMedicamento){
		
		String generoAtual = paciente.getGenero();
		String novoGenero = (generoAtual.equalsIgnoreCase("masculino")) ? "feminino" : "masculino";
		paciente.setGenero(novoGenero);
		
		return this.PRECO;
	}
}
