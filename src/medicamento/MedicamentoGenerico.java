package medicamento;

public class MedicamentoGenerico implements TipoMedicamentoIF {

	public final double DESCONTO = 0.6;

	private static MedicamentoGenerico INSTANCE;

	/**
	 * Construtor para inicializar uma unica instancia de medicamento de
	 * generico.
	 */
	public MedicamentoGenerico() {
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
