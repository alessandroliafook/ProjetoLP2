package medicamento;

public interface TipoMedicamentoIF {

	/**
	 * Metodo polimorfico que calcula o preco associado ao tipo de medicamento.
	 * 
	 * @param preco
	 *            double associado ao preco do medicamento.
	 * @return double referente ao preco do tipo de medicamento conforme
	 *         desconto associado a cada tipo.
	 */
	double calculaPreco(double preco);

}
