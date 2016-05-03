package pessoal;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;


public class FuncionarioTest {
	
	@Test
	public void testConstrutor() {
		/*Teste para Funcionario() construido corretamente*/
		try {
			Funcionario funcionario = new Funcionario("paulo", "15/09/1995", "12016001", "19951201");
			
		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
		
		/*Testes para Funcionario() construido incorretamente*/
		try {
			Funcionario funcionario = new Funcionario("", "15/09/1995", "12016001", "19951201");
			fail();
		} catch (Exception e) {
			assertEquals("Nome do funcionario nao pode ser vazio.", e.getMessage());
		}
		
		try {
			Funcionario funcionario = new Funcionario("pedro", "15/19/1995", "12016001", "19951201");
			fail();
		} catch (Exception e) {
			assertEquals("Data invalida.", e.getMessage());
		}
		
	}

	@Test
	public void testGetCargo(){
		try {
			/*O primeiro digito da matricula indica o cargo do funcionario*/
			Funcionario funcionario1 = new Funcionario("paulo", "15/09/1995", "12016001", "19951201");
			Funcionario funcionario2 = new Funcionario("pedro", "15/09/1995", "22016002", "19952201");
			Funcionario funcionario3 = new Funcionario("joao", "15/09/1995", "32016003", "19953201");
			assertEquals("Diretor Geral", funcionario1.getCargo());
			assertEquals("Medico", funcionario2.getCargo());
			assertEquals("Tecnico Administrativo", funcionario3.getCargo());
		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
	}
	
	@Test
	public void testEquals(){
		try {
			/*Funcionarios sao iguais caso tenham a mesma matricula*/
			Funcionario funcionario1 = new Funcionario("paulo", "15/09/1995", "12016001", "19951201");
			Funcionario funcionario2 = new Funcionario("pedro", "15/09/1995", "22016002", "19952201");
			Funcionario funcionario3 = new Funcionario("joao", "15/09/1995", "32016003", "19953201");
			Funcionario funcionario4 = new Funcionario("carlao", "11/11/1955", "32016003", "19553201");
			assertNotEquals(funcionario1, funcionario2);
			assertNotEquals(funcionario1, funcionario3);
			assertNotEquals(funcionario3, funcionario2);
			assertEquals(funcionario3, funcionario4);
		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
	}
	
	@Test
	public void testCompareTo(){
		try {
			/*Funcionarios sao ordenados pela ordem alfabetica de seus nomes*/
			ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
			listaFuncionarios.add(new Funcionario("ape", "13/03/1993", "1", "1"));
			listaFuncionarios.add(new Funcionario("dio", "13/03/1993", "1", "1"));
			listaFuncionarios.add(new Funcionario("cac", "13/03/1993", "1", "1"));
			listaFuncionarios.add(new Funcionario("boc", "13/03/1993", "1", "1"));
			Collections.sort(listaFuncionarios);
			assertEquals("ape", listaFuncionarios.get(0).getNome());
			assertEquals("boc", listaFuncionarios.get(1).getNome());
			assertEquals("cac", listaFuncionarios.get(2).getNome());
			assertEquals("dio", listaFuncionarios.get(3).getNome());
		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
	}
}
