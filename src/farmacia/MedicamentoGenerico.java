package farmacia;

import java.io.Serializable;

public class MedicamentoGenerico implements TipoMedicamentoIF, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8903791003464155716L;
	
	public final double DESCONTO = 0.6;

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
