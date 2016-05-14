package cartao;

public class CartaoFidelidade {

	private Fidelidade tipoCartao;
	private int pontosFidelidade;

	public CartaoFidelidade() {
		this.tipoCartao = new Padrao();
		this.pontosFidelidade = 0;
	}

	public int getPontosFidelidade() {
		return this.pontosFidelidade;
	}

	/**
	 * Fornece desconto de acordo com o tipo de fidelidade do cartao
	 * 
	 * @param precoServico
	 *            Preco total do servico
	 * @return O total de desconto sobre o preco do servico
	 */
	public double getDesconto(double precoServico) {
		return tipoCartao.getDescontoEmServicos(precoServico);
	}

	/**
	 * Adiciona os pontos de fidelidade ganhos mais os pontos extras que cada
	 * tipo de fidelidade fornecem
	 * 
	 * @param pontosGanhos
	 *            Pontos ganhos de fidelidade ganhos por procedimentos
	 */
	public void adicionaPontosFidelidade(int pontosGanhos) {
		pontosFidelidade += this.tipoCartao.getPontosBonus(pontosGanhos);
		realizaUpgradeCartao();
	}

	/**
	 * Verifica se o tipo de fidelidade do cartao pode ser atualizado
	 */
	private void realizaUpgradeCartao() {

		if (getPontosFidelidade() > MaxPontosPorFidelidade.MASTER.getValue()) {

			this.tipoCartao = new Vip();

		} else if (getPontosFidelidade() > MaxPontosPorFidelidade.PADRAO.getValue()) {

			this.tipoCartao = new Master();

		}

	}

}
