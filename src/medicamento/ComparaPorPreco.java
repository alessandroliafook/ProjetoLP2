package medicamento;

import java.util.Comparator;

import medicamento.Medicamento;

public class ComparaPorPreco implements Comparator<Medicamento> {

	@Override
	/**
	 * Comparador que determina a precedencia de dois medicamentos considerando
	 * o menor preco.
	 */
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {

		if (medicamento.getPreco() > outroMedicamento.getPreco()) {
			return 1;

		} else if (medicamento.getPreco() == outroMedicamento.getPreco()) {
			return 0;

		} else {
			return -1;
		}

	}

}
