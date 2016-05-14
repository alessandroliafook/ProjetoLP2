package cartao;

public enum MaxPontosPorFidelidade {
	PADRAO(149), MASTER(350);
	private int value;
	
	private MaxPontosPorFidelidade(int maximoPontos){
		this.value = maximoPontos;
	}
	
	public int getValue(){
		return this.value;
	}
}
