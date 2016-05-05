package medicamento;

import java.io.Serializable;

public interface TipoMedicamentoIF extends Serializable{

	/**
	 * Metodo polimorfico que calcula o preco associado ao tipo de medicamento.
	 * 
	 * @param preco
	 *            double associado ao preco do medicamento.
	 * @return double referente ao preco do tipo de medicamento conforme
	 *         desconto associado a cada tipo.
	 */
	double calculaPreco(double preco);
	
	/**
	 * Metodo que informa qual o tipo de medicamento.
	 * @return String contendo o nome do tipo de medicamento correspondente.
	 */
	String toString();

}
