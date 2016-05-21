package procedimentos;

import java.io.Serializable;

import pessoal.Paciente;

public interface ProcedimentoIF extends Serializable {

	/**
	 * Metodo que realiza as modificacoes no paciente conforme o tipo de
	 * procedimento realizado
	 * 
	 * @param paciente
	 *            Objeto do tipo Paciente que deve ser modificado
	 * @param gastosComMedicamento
	 *            Total de custos com medicamentos
	 * @return O custo do procedimento
	 */
	double realizaProcedimento(Paciente paciente, double gastosComMedicamento);

	/**
	 * Metodo que retorna a quantidade de pontos fidelidade de acordo com o tipo
	 * de procedimento realizado
	 * 
	 * @return A quantidade de pontos fidelidade adquiridos
	 */
	int getPontosBonus();

}
