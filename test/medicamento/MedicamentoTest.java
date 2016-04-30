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
	
		}catch(Exception e){
			System.out.println(e.getMessage());
			fail("nao deve falhar");
		}
		
		
		
		try {

			// testando construtor
			Medicamento valium = new Medicamento("Valium", "generico", 21.50, 45, "analgesico");
			Medicamento metamizol = new Medicamento("Metamizol", "referencia", 58.30, 466, "analgesico,antitermico");

			try {


				Set<CategoriasEnum> list1 = new TreeSet<CategoriasEnum>();
				list1.add(CategoriasEnum.ANALGESICO);
				System.out.println(list1);

				Set<CategoriasEnum> list2 = new TreeSet<CategoriasEnum>();
				list2.add(CategoriasEnum.ANALGESICO);
				list2.add(CategoriasEnum.ANTIMETICO);
				System.out.println(list2);

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


		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("nao deve lancar excecao.");
		}

	}

}
