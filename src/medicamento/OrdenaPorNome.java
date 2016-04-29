package medicamento;

import java.util.Comparator;

import medicamento.Medicamento;

public class OrdenaPorNome implements Comparator<Medicamento> {

	@Override
	/**
	 * Comparador que determina a precedencia de dois medicamentos considerando
	 * a ordem lexica de seus nomes.
	 */
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {

		return medicamento.getNome().compareTo(outroMedicamento.getNome());

	}

}
