package cartao;

public class CartaoFidelidade {

	private Fidelidade tipoCartao;
	private int pontosAcumulados;

	public CartaoFidelidade() {
		this.tipoCartao = new Padrao();
		this.pontosAcumulados = 0;
	}
	
}
