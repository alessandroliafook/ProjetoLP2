package medicamento;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComparaPorNomeTest {

	@Test
	public void test() {

		try {

			Medicamento valium = new Medicamento("Valium", "generico", 21.50, 45, "analgesico");
			Medicamento metamizol = new Medicamento("Metamizol", "referencia", 58.30, 466, "analgesico,antitermico");
			Medicamento morfina = new Medicamento("Morfina", "referencia", 150, 600, "analgesico");

			// teste construtor
			ComparaPorPreco comparador = new ComparaPorPreco();
			
			// teste de comparacao
			assertTrue(comparador.compare(valium, metamizol) > 0);
			assertTrue(comparador.compare(metamizol, morfina) < 0);
			assertTrue(comparador.compare(valium, valium) == 0);
			
		}catch(Exception e){
			fail("nao deve lancar excecao");
		}
	
	
	}

}
