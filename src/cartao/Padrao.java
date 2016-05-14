package cartao;

public class Padrao implements Fidelidade {

	private final double PORCENTAGEM_DESCONTO_SERVICO = 0;
	private final double PORCENTAGEM_CREDITO_BONUS = 0;

	@Override
	public double getDescontoEmServicos(double precoServico) {
		return PORCENTAGEM_DESCONTO_SERVICO * precoServico;
	}

	@Override
	public int getPontosBonus(int creditoGanho) {
		return (int)PORCENTAGEM_CREDITO_BONUS * creditoGanho;
	}

}
