package medicamento;
import java.util.Comparator;

import medicamento.Medicamento;


public class OrdenaPorNome implements Comparator<Medicamento>{

	@Override
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		
		return medicamento.getNome().compareTo(outroMedicamento.getNome());
		
	}

}
