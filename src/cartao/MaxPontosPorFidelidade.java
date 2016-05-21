package cartao;

import java.io.Serializable;

public enum MaxPontosPorFidelidade implements Serializable{
	
	/**
	 * Os tipos de cartao sao definidos pela quantidade de pontos.
	 * Entre 0 e 149 pontos - Padrao
	 * Entre 150 e 350 - Master
	 * Mais do que 350 - VIP
	 */
	
	PADRAO(149), MASTER(350);
	private int value;
	
	private MaxPontosPorFidelidade(int maximoPontos){
		this.value = maximoPontos;
	}
	
	public int getValue(){
		return this.value;
	}
}
