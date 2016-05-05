package pessoal;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class PacienteTest {

	@Test
	public void testConstrutor() {
		/* Teste para construtor correto */
		try {
			Paciente paciente = new Paciente("pedro", "15/09/1995", 33.1, "A+", "masculino", "masculino", 1);
		} catch (Exception e) {
			fail();
		}

		/* Testes de lancamento de excecao */
		try {
			Paciente paciente = new Paciente(" ", "15/09/1995", 33.1, "A+", "masculino", "masculino", 1);
			fail();
		} catch (Exception e) {
			assertEquals("Nome do paciente nao pode ser vazio.", e.getMessage());
		}

		try {
			Paciente paciente = new Paciente("pedro", "15/13/1995", 33.1, "A+", "masculino", "masculino", 1);
			fail();
		} catch (Exception e) {
			assertEquals("Data invalida.", e.getMessage());
		}

		try {
			Paciente paciente = new Paciente("pedro", "15/09/1995", -1, "A+", "masculino", "masculino", 1);
			fail();
		} catch (Exception e) {
			assertEquals("Peso do paciente nao pode ser negativo.", e.getMessage());
		}

		try {
			Paciente paciente = new Paciente("pedro", "15/09/1995", 90, "H-", "masculino", "masculino", 1);
			fail();
		} catch (Exception e) {
			assertEquals("Tipo sanguineo invalido.", e.getMessage());
		}

	}

	@Test
	public void testGetData() {
		try {
			Paciente paciente = new Paciente("pedro", "15/09/1995", 90, "A-", "masculino", "masculino", 1);
			assertEquals("1995-09-15", paciente.getData());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEquals() {
		/*Dois pacientes sao iguais caso tenham o mesmo id*/
		try {
			Paciente paciente = new Paciente("pedro", "15/09/1995", 90, "B-", "masculino", "masculino", 1);
			Paciente paciente2 = new Paciente("pedro", "15/09/1995", 90, "B-", "masculino", "masculino", 2);
			Paciente paciente3 = new Paciente("carlos", "25/11/1955", 120, "A-", "masculino", "masculino", 1);
			assertEquals(paciente, paciente3);
			assertNotEquals(paciente2, paciente3);
			assertNotEquals(paciente2, paciente);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testCompareTo(){
		/*Pacientes sao ordenados pela ordem alfabetica de seus nomes*/
		try {
			List<Paciente> listaPacientes = new ArrayList<Paciente>();
			Paciente paciente1 = new Paciente("ant", "15/09/1995", 90, "B-", "masculino", "masculino", 1);
			Paciente paciente2 = new Paciente("bia", "15/09/1995", 30, "B-", "feminino", "feminino", 2);
			Paciente paciente3 = new Paciente("cal", "15/09/1995", 44, "B-", "masculino", "masculino", 3);
			Paciente paciente4 = new Paciente("dio", "15/09/1995", 55, "B-", "masculino", "masculino", 4);
			listaPacientes.add(paciente4);
			listaPacientes.add(paciente2);
			listaPacientes.add(paciente1);
			listaPacientes.add(paciente3);
			Collections.sort(listaPacientes);
			assertEquals("ant", listaPacientes.get(0).getNome());
			assertEquals("bia", listaPacientes.get(1).getNome());
			assertEquals("cal", listaPacientes.get(2).getNome());
			assertEquals("dio", listaPacientes.get(3).getNome());
		} catch (Exception e) {
			fail();
		}
	}

}
