package factory;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import farmacia.CategoriasEnum;
import farmacia.Medicamento;

public class FactoryDeMedicamentosTest {

	@Test
	public void test() {

		try {

			Medicamento valium = new Medicamento("Valium", "generico", 21.50, 45, "analgesico");
			Medicamento metamizol = new Medicamento("Metamizol", "referencia", 58.30, 466, "analgesico,antitermico");

			Set<CategoriasEnum> list1 = new TreeSet<CategoriasEnum>();
			list1.add(CategoriasEnum.ANALGESICO);

			Set<CategoriasEnum> list2 = new TreeSet<CategoriasEnum>();
			list2.add(CategoriasEnum.ANALGESICO);
			list2.add(CategoriasEnum.ANTIEMETICO);

			
			// teste construtor
			FactoryDeMedicamentos farmaceutico = new FactoryDeMedicamentos();

			// teste funcao de criar medicamentos
			Medicamento valium2 = farmaceutico.criaMedicamento("Valium", "generico", 21.50, 45, "analgesico");
			Medicamento metamizol2 = farmaceutico.criaMedicamento("Metamizol", "referencia", 58.30, 466, "analgesico,antitermico");

			assertTrue(valium.equals(valium2));
			assertTrue(metamizol.equals(metamizol2));
			assertFalse(valium2.equals(metamizol2));
			
			// testando os atributos
			assertEquals("Valium", valium2.getNome());
			assertEquals("Generico", valium2.getTipo());
			assertTrue(valium2.getPreco() == 12.90);
			assertTrue(valium2.getQuantidade() == 45);
			assertFalse(valium2.equals(metamizol));
			assertTrue(valium2.getCategorias().equals(list1));
			assertFalse(valium2.getCategorias().equals(list2));
			
			
		} catch (Exception e) {
			fail("nao deve falhar.");
		}

	}

	@Test
	public void testExceptions(){

		FactoryDeMedicamentos farmaceutico = new FactoryDeMedicamentos();

		try{
			Medicamento medicamentoInvalido = farmaceutico.criaMedicamento("", "generico", 21.50, 45, "analgesico");

		}catch(Exception e){
			assertEquals("Nome do medicamento nao pode ser vazio.", e.getMessage());
			
		}

		try{
			Medicamento medicamentoInvalido = farmaceutico.criaMedicamento(" ", "generico", 21.50, 45, "analgesico");

		}catch(Exception e){
			assertEquals("Nome do medicamento nao pode ser vazio.", e.getMessage());
			
		}

		try{

			Medicamento medicamentoInvalido = farmaceutico.criaMedicamento("Valium", "", 21.50, 45, "analgesico");

		}catch(Exception e){
		
			assertEquals("Tipo de medicamento invalido.", e.getMessage());

		}

		try{

			Medicamento medicamentoInvalido = farmaceutico.criaMedicamento("Valium", "experimental", 21.50, 45, "analgesico");

		}catch(Exception e){
		
			assertEquals("Tipo de medicamento invalido.", e.getMessage());

		}
		
		try{
			Medicamento medicamentoInvalido = farmaceutico.criaMedicamento("Valium", "generico", -1, 45, "analgesico");

		}catch(Exception e){
			assertEquals("Preco do medicamento nao pode ser negativo.", e.getMessage());

		}
		try{
			Medicamento medicamentoInvalido = farmaceutico.criaMedicamento("Valium", "generico", 21.50, -1, "analgesico");

		}catch(Exception e){
			assertEquals("Quantidade do medicamento nao pode ser negativo.", e.getMessage());

		}
		try{
			Medicamento medicamentoInvalido = farmaceutico.criaMedicamento("Valium", "generico", 21.50, 45, "");

		}catch(Exception e){
			assertEquals("Categoria invalida.", e.getMessage());
		}

		try{
			Medicamento medicamentoInvalido = farmaceutico.criaMedicamento("Valium", "generico", 21.50, 45, "antidepressivo");

		}catch(Exception e){
			assertEquals("Categoria invalida.", e.getMessage());
		}
		
	}

	
}
