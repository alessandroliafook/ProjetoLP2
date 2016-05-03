package medicamento;

import static org.junit.Assert.*;

import org.junit.Test;

public class MedicamentoReferenciaTest {

	@Test
	public void test() {
		// testando o singleton
		TipoMedicamentoIF medicamentoDeReferencia = new MedicamentoReferencia();
		
		// testando a instancia
		assertTrue(medicamentoDeReferencia instanceof TipoMedicamentoIF);
		assertTrue(medicamentoDeReferencia instanceof MedicamentoReferencia);

		// testando o toString
		assertNotEquals("Generico", medicamentoDeReferencia.toString());
		assertEquals("de Referencia", medicamentoDeReferencia.toString());
		
		// testando o desconto
		assertFalse(medicamentoDeReferencia.calculaPreco(10) == 6.00);
		assertTrue(medicamentoDeReferencia.calculaPreco(10) == 10.00);
	}

}
