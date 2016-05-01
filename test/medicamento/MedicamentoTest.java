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

			Set<CategoriasEnum> list1 = new TreeSet<CategoriasEnum>();
			list1.add(CategoriasEnum.ANALGESICO);

			Set<CategoriasEnum> list2 = new TreeSet<CategoriasEnum>();
			list2.add(CategoriasEnum.ANALGESICO);
			list2.add(CategoriasEnum.ANTIEMETICO);
			
			// testando gets e sets
			assertEquals("Valium", valium.getNome());
			assertEquals("Generico", valium.getTipo());
			assertTrue(valium.getPreco() == 12.90);
			assertTrue(valium.getQuantidade() == 45);
			assertFalse(valium.equals(metamizol));
			assertTrue(valium.getCategorias().equals(list1));
			assertFalse(valium.getCategorias().equals(list2));

			
			// testando toString
			String string = "Medicamento Generico: Valium - Preco: R$ 12.90 - Disponivel: 45 - Categorias: analgesico";
			assertEquals(string, valium.informacoes());

			String string2 = "Medicamento de Referencia: Metamizol - Preco: R$ 58.30 - Disponivel: 466 - Categorias: analgesico,antitermico";
			assertEquals(string2, metamizol.informacoes());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("nao deve lancar excecao.");
		}

	}

	@Test
	public void testExceptions(){
		
		try{
			Medicamento medicamentoInvalido = new Medicamento("", "generico", 21.50, 45, "analgesico");

		}catch(Exception e){
			assertEquals("Nome do medicamento nao pode ser vazio.", e.getMessage());
			
		}

		try{
			Medicamento medicamentoInvalido = new Medicamento(" ", "generico", 21.50, 45, "analgesico");

		}catch(Exception e){
			assertEquals("Nome do medicamento nao pode ser vazio.", e.getMessage());
			
		}

		try{

			Medicamento medicamentoInvalido = new Medicamento("Valium", "", 21.50, 45, "analgesico");

		}catch(Exception e){
		
			assertEquals("Tipo de medicamento invalido.", e.getMessage());

		}

		try{

			Medicamento medicamentoInvalido = new Medicamento("Valium", "experimental", 21.50, 45, "analgesico");

		}catch(Exception e){
		
			assertEquals("Tipo de medicamento invalido.", e.getMessage());

		}
		
		try{
			Medicamento medicamentoInvalido = new Medicamento("Valium", "generico", -1, 45, "analgesico");

		}catch(Exception e){
			assertEquals("Preco do medicamento nao pode ser negativo.", e.getMessage());

		}
		try{
			Medicamento medicamentoInvalido = new Medicamento("Valium", "generico", 21.50, -1, "analgesico");

		}catch(Exception e){
			assertEquals("Quantidade do medicamento nao pode ser negativo.", e.getMessage());

		}
		try{
			Medicamento medicamentoInvalido = new Medicamento("Valium", "generico", 21.50, 45, "");

		}catch(Exception e){
			assertEquals("Categoria invalida.", e.getMessage());
		}

		try{
			Medicamento medicamentoInvalido = new Medicamento("Valium", "generico", 21.50, 45, "antidepressivo");

		}catch(Exception e){
			assertEquals("Categoria invalida.", e.getMessage());
		}
		
	}
	
}
