package medicamento;

import java.io.Serializable;

public class MedicamentoReferencia implements TipoMedicamentoIF, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 368050718251307242L;
	public final double DESCONTO = 1;

	/**
	 * Construtor para inicializar uma unica instancia de medicamento de
	 * referencia.
	 */
	public MedicamentoReferencia() {
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
