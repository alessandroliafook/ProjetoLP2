package medicamento;

import static org.junit.Assert.*;

import org.junit.Test;

public class MedicamentoGenericoTest {

	@Test
	public void test() {

		// testando o singleton
		TipoMedicamentoIF medicamentoGenerico = MedicamentoGenerico.getInstance();
		
		// testando a instancia
		assertTrue(medicamentoGenerico instanceof TipoMedicamentoIF);
		assertTrue(medicamentoGenerico instanceof MedicamentoGenerico);
		assertFalse(medicamentoGenerico.getClass().equals(MedicamentoReferencia.getInstance().getClass()));

		// testando o toString
		assertEquals("Generico", medicamentoGenerico.toString());
		assertNotEquals("de Referencia", medicamentoGenerico.toString());
		
		// testando o desconto
		assertTrue(medicamentoGenerico.calculaPreco(10) == 6.00);
		assertFalse(medicamentoGenerico.calculaPreco(10) == 10.00);
		
	}

}
