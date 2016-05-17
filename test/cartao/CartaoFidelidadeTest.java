package cartao;

import static org.junit.Assert.*;

import org.junit.Test;

public class CartaoFidelidadeTest {
	
	private final double MAX_ERROR = 0.01;
	
	
	@Test
	public void testGetPontosFidelidade(){
		try {
			CartaoFidelidade cartao = new CartaoFidelidade();
			/* Quando criado o cartao deve comecar com 0 pontos fidelidade */
			
			assertEquals(0, cartao.getPontosFidelidade());
			
			cartao.adicionaPontosFidelidade(150);
			/* Agora o cartao deve ser do tipo Master e fornecer 5 porcento pontos extras a mais nos PROXIMOS pontos fidelidades adicionados */
			assertEquals(150, cartao.getPontosFidelidade());
			
			cartao.adicionaPontosFidelidade(1000); // 150 pontos que ja existiam + 1000 pontos adicionados + 5 porcento de 1000 = 150 + 1000 + 50 = 1200
			assertEquals(1200, cartao.getPontosFidelidade());
			
			/* Agora o cartao deve ser do tipo Vip e fornecer 10 porcento pontos extras a mais nos PROXIMOS pontos fidelidades adicionados */
			cartao.adicionaPontosFidelidade(1000); //1200 que ja existiam + 1000 + 1000 * 0.1 = 2300
			assertEquals(2300, cartao.getPontosFidelidade());
			
			
		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
	}
	
	
	@Test
	public void testGetDesconto() {
		
		try {
			CartaoFidelidade cartao = new CartaoFidelidade();
			/* O cartao comeca com Fidelidade Padrao portanto nao deve fornecer desconto */
			assertEquals(0, cartao.getDesconto(50), MAX_ERROR);
			
			cartao.adicionaPontosFidelidade(150);
			/* Agora o cartao deve ser do tipo Master e fornecer 15 porcento de desconto */
			assertEquals(50 * 0.15, cartao.getDesconto(50), MAX_ERROR);
			
			cartao.adicionaPontosFidelidade(1000);
			/* Agora o cartao deve ser do tipo Master e fornecer 30 porcento de desconto */
			assertEquals(50 * 0.3, cartao.getDesconto(50), MAX_ERROR);
			
			
		} catch(Exception e){
			fail("Nao deveria lancar excecao");
		}		
		
	}
	
	
	

}
