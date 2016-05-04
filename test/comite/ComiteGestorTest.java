package comite;

import static org.junit.Assert.*;

import org.junit.Test;

import admin.ComiteGestor;

public class ComiteGestorTest {

	@Test
	public void testLiberaSistema() {
		try {
			ComiteGestor comite = new ComiteGestor();
			String matriculaDiretor = comite.liberaSistema("c041ebf8", "paulo", "01/01/2001");
			assertEquals(comite.getInfoFuncionario(matriculaDiretor, "Nome"), "paulo");
			assertEquals(comite.getInfoFuncionario(matriculaDiretor, "Data"), "2001-01-01");
			assertEquals(comite.getInfoFuncionario(matriculaDiretor, "Cargo"), "Diretor Geral");
			comite.liberaSistema("c041ebf8", "joao", "02/03/2011");
			fail();
		} catch (Exception e) {
			assertEquals("Erro ao liberar o sistema. Sistema liberado anteriormente.", e.getMessage());
		}
		
		try {
			ComiteGestor comiteGestor = new ComiteGestor();
			comiteGestor.liberaSistema("chaveErrada", "pedro", "15/07/1998");
			fail();
		} catch (Exception e) {
			assertEquals("Erro ao liberar o sistema. Chave invalida.", e.getMessage());
		}
	}

}
