package medicamento;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import medicamento.Medicamento;
import org.junit.Test;

public class MedicamentoTest {

	@Test
	public void test() {

		try {

			// testando construtor
			Medicamento valium = new Medicamento("Valium", "generico", 21.50, 45, "analgesico");
			Medicamento metamizol = new Medicamento("Metamizol", "referencia", 58.30, 466, "analgesico,antitermico");

			try {

				CategoriasEnum analgesico = CategoriasEnum.valueOf("ANALGESICO");
				CategoriasEnum antitermico = CategoriasEnum.valueOf("ANTITERMICO");

				Set<CategoriasEnum> list1 = new TreeSet<CategoriasEnum>();
				list1.add(analgesico);

				Set<CategoriasEnum> list2 = new TreeSet<CategoriasEnum>();
				list2.add(antitermico);
				list2.add(analgesico);

				assertEquals("Valium", valium.getNome());
				assertEquals("generico", valium.getTipo());
				assertTrue(valium.getPreco() == 21.50);
				assertTrue(valium.getQuantidade() == 45);
				assertFalse(valium.equals(metamizol));
				assertTrue(valium.getCategorias().equals(list1));
				assertFalse(valium.getCategorias().equals(list2));

			} catch (Exception e) {
				fail("nao deve lancar excecao.");

			}

			Medicamento morfina = new Medicamento("Morfina", "referencia", 150, 600, "analgesico");
			Medicamento medroxyprogesterona = new Medicamento("Medroxyprogesterona", "generico", 285.50, 600,
					"hormonal");
			Medicamento duraston = new Medicamento("Duraston", "generico", 112.50, 150, "hormonal");
			Medicamento nimesulida = new Medicamento("Nimesulida", "referencia", 12.50, 150,
					"antiinflamatorio,antitermico,analgesico");
			Medicamento penicilina = new Medicamento("Penicilina", "referencia", 80.00, 150, "antibiotico");

		} catch (Exception e) {
			fail("nao deve lancar excecao.");
		}

	}

}
