package hospital;

import static org.junit.Assert.*;
import hospital.ComiteGestor;

import org.junit.Before;
import org.junit.Test;

public class ComiteGestorTest {
	
	private ComiteGestor comite;
	
	
	public ComiteGestorTest(){
		this.comite = new ComiteGestor();
	}

	@Test
	public void testLiberaSistema() {

		String matriculaDiretor = "";

		try {
			
			matriculaDiretor = comite.liberaSistema("c041ebf8", "paulo", "01/01/2001");

			assertEquals("12016001", matriculaDiretor);
		} catch (Exception e) {
			fail("Nao deve lancar excecao");
		}

		try {
			assertEquals(comite.getInfoFuncionario(matriculaDiretor, "Nome"), "paulo");
			assertEquals(comite.getInfoFuncionario(matriculaDiretor, "Data"), "2001-01-01");
			assertEquals(comite.getInfoFuncionario(matriculaDiretor, "Cargo"), "Diretor Geral");
			comite.liberaSistema("c041ebf8", "joao", "02/03/2011");
			fail("Nao deve lancar excecao");
		} catch (Exception e) {
			assertEquals("Erro ao liberar o sistema. Sistema liberado anteriormente.", e.getMessage());
		}

		try {
			comite.liberaSistema("chaveErrada", "pedro", "15/07/1998");
			fail("Nao deve lancar excecao");
		} catch (Exception e) {
			assertEquals("Erro ao liberar o sistema. Sistema liberado anteriormente.", e.getMessage());
		}
	}
	
	@Test
	public void testLogin() {
		
		
		
	}

	

}
