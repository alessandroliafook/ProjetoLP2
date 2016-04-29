package factory;

import medicamento.MedicamentoGenerico;
import medicamento.MedicamentoReferencia;

public class FactoryTipoMedicamento {

	/**
	 * Metodo responsavel que faz o fowarding do metodo responsavel por criar
	 * uma instancia do Medicamento de Generico se nao existir, ou retornar a
	 * instancia existente acaso jah tenha sido criada, usando a estrategia do
	 * singleton.
	 * 
	 * @return A instancia do Medicamento de Generico existente no sistema.
	 */
	public static MedicamentoGenerico criaMedicamentoGenerico() {

		return MedicamentoGenerico.getInstance();

	}

	/**
	 * Metodo responsavel que faz o fowarding do metodo responsavel por criar
	 * uma instancia do Medicamento de Referencia se nao existir, ou retornar a
	 * instancia existente acaso jah tenha sido criada, usando a estrategia do
	 * singleton.
	 * 
	 * @return A instancia do Medicamento de Referencia existente no sistema.
	 */
	public static MedicamentoReferencia criaMedicamentoReferencia() {

		return MedicamentoReferencia.getInstance();

	}

}
