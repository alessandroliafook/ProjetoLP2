package procedimentos;

import pessoal.Paciente;

public interface ProcedimentoIF {

	double realizaProcedimento(Paciente paciente, double gastosComMedicamento);
	int getPontosBonus();
	
}
