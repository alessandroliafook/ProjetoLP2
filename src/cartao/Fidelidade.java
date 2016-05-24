package cartao;

import java.io.Serializable;

public interface Fidelidade extends Serializable{
	/**
	 * Dado um valor real gasto o metodo retorna o desconto sobre esse valor de
	 * acordo com o tipo do cartao 0/15/30 por cento de desconto para os cartoes
	 * Padrao/Master/Vip respectivamente
	 * 
	 * @param precoServico
	 *            O preco do servico prestado
	 * @return O desconto sobre o preco fornecido
	 */
	double getDescontoEmServicos(double precoServico);

	/**
	 * Dado uma quantidade de pontos fidelidade ganhos o metodo retorna uma
	 * quantidade bonus de creditos a serem adicionados no cartao de acordo com
	 * o tipo do cartao 0/5/10 por cento de bonus para os cartoes
	 * Padrao/Master/Vip respectivamente
	 * 
	 * @param creditoGanho
	 *            A quantidade de pontos fidelidade recebidos
	 * @return A quantidade bonus de pontos fidelidade
	 */
	int getPontosBonus(int creditoGanho);
}