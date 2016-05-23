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

	public void testLogin() {

		try {
			comite.login("10000666", "20011201");
			fail("Nao deve lancar excecao");

		} catch (Exception e) {
			assertEquals("Nao foi possivel realizar o login. Funcionario nao cadastrado.", e.getMessage());
		}

		try {
			comite.login("12016001", "20011201");

		} catch (Exception e) {
			fail("Nao deve lancar excecao aqui");
		}

		try {
			comite.login("12016001", "20011201");
			fail("Nao deve lancar excecao");

		} catch (Exception e) {
			assertEquals("Nao foi possivel realizar o login. Um funcionario ainda esta logado: paulo.", e.getMessage());
		}

	}

	public void testLogout() {

		try {
			comite.logout();

		} catch (Exception e) {
			fail("Nao deve lancar excecao aqui");
		}

		try {
			comite.logout();
			fail("Nao deve lancar excecao");

		} catch (Exception e) {
			assertEquals("Nao foi possivel realizar o logout. Nao ha um funcionario logado.", e.getMessage());
		}
	}

	public void testCadastraFuncionario() {

		try {
			comite.login("12016001", "20011201");
		} catch (Exception e) {
			fail("Nao deve lancar excecao");
		}

		try {
			String teste = comite.cadastraFuncionario("Oiabaxogum", "Medico", "10/10/2010");
			assertEquals(teste, "22016002");
		} catch (Exception e) {
			fail("Nao deve lancar excecao");
		}

		try {
			comite.logout();
			comite.login("22016002", "20102201");
			comite.cadastraFuncionario("Kirby", "Medico", "12/04/1087");
			fail("Nao deve lancar excecao");

		} catch (Exception e) {
			assertEquals(
					"Erro no cadastro de funcionario. O funcionario Oiabaxogum nao tem permissao para cadastrar funcionarios.",
					e.getMessage());
		}

	}
	
	public void fechaSistema() {
		
		try {
			comite.fechaSistema();
			fail("Nao deve chegar aqui");
		} catch(Exception e) {
			assertEquals("Nao foi possivel fechar o sistema. Um funcionario ainda esta logado: Oiabaxogum.", e.getMessage() );
		}
		
		try {
			comite.logout();
			comite.fechaSistema();
		} catch(Exception e) {
			fail("Nao deve lancar excecao aqui");
		}
		
	}

}
