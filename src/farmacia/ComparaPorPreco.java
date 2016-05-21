package farmacia;

import java.io.Serializable;
import java.util.Comparator;

import farmacia.Medicamento;

public class ComparaPorPreco implements Comparator<Medicamento>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4900232050622435188L;

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
