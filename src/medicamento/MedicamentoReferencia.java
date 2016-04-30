package medicamento;

public class MedicamentoReferencia implements TipoMedicamentoIF {

	public final double DESCONTO = 1;

	private static TipoMedicamentoIF INSTANCE;

	/**
	 * Construtor privado para inicializar uma unica instancia de medicamento de
	 * referencia, aplicando o padrao de projeto singleton.
	 */
	private MedicamentoReferencia() {
	}

	/**
	 * Metodo responsavel por criar uma instancia do Medicamento de Referencia
	 * se nao existir, ou retornar a instancia existente acaso jah tenha sido
	 * criada, usando a estrategia do singleton.
	 * 
	 * @return A instancia do Medicamento de Referencia existente no sistema.
	 */
	public static TipoMedicamentoIF getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new MedicamentoReferencia();
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
		return "de Referencia";
	}

}
