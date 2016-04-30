package medicamento;

public class MedicamentoGenerico implements TipoMedicamentoIF {

	public final double DESCONTO = 0.6;

	private static MedicamentoGenerico INSTANCE;

	/**
	 * Construtor privado para inicializar uma unica instancia de medicamento de
	 * generico, aplicando o padrao de projeto singleton.
	 */
	private MedicamentoGenerico() {
	}

	/**
	 * Metodo responsavel por criar uma instancia do Medicamento de Generico se
	 * nao existir, ou retornar a instancia existente acaso jah tenha sido
	 * criada, usando a estrategia do singleton.
	 * 
	 * @return A instancia do Medicamento de Generico existente no sistema.
	 */
	public static MedicamentoGenerico getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new MedicamentoGenerico();
		}

		return INSTANCE;
	}

	/**
	 * Metodo que aplica o desconto relativo ao preco de medicamento generico.
	 * 
	 * @param preco
	 *            double sobre o qual vai ser aplicado o desconto.
	 */
	public double calculaPreco(double preco) {

		return preco * DESCONTO;

	}

	/**
	 * Metodo que informa qual o nome associado ao tipo de medicamento.
	 * 
	 * @return String contendo o tipo de medicamento.
	 */
	public String toString() {
		return "Generico";
	}

}
