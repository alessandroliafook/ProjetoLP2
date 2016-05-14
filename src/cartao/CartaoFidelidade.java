package cartao;

public class CartaoFidelidade {

	private Fidelidade tipoCartao;
	private int pontosFidelidade;

	public CartaoFidelidade() {
		this.tipoCartao = new Padrao();
		this.pontosFidelidade = 0;
	}
	
	public int getPontosFidelidade(){
		return this.pontosFidelidade;
	}
	
}
