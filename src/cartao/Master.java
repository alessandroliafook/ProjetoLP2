package cartao;

public class Master implements Fidelidade {
	
	private final double PORCENTAGEM_DESCONTO_SERVICO = 0.15;
	private final double PORCENTAGEM_CREDITO_BONUS = 0.05;

	@Override
	public double getDescontoEmServicos(double precoServico) {
		return PORCENTAGEM_DESCONTO_SERVICO * precoServico;
	}

	@Override
	public int getCreditoBonus(int creditoGanho) {
		return (int)PORCENTAGEM_CREDITO_BONUS * creditoGanho;
	}

}
