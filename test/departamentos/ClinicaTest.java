package departamentos;

import static org.junit.Assert.*;

import org.junit.Test;

import pessoal.Paciente;

public class ClinicaTest {

	@Test
	public void testCadastraPaciente() {
		/* Teste para o caso em que o paciente eh cadastrado corretamente */
		try {
			Clinica clinica = new Clinica();
			assertEquals(0, clinica.getNumeroCadastros());
			Paciente paciente1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino", "masculino", "AB-");
			assertEquals(1, clinica.getNumeroCadastros());
			assertEquals("pedro", clinica.getInfoPaciente(paciente1, "Nome"));
			assertEquals("1978-09-15", clinica.getInfoPaciente(paciente1, "Data"));

		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}

		/*
		 * Testes pra os casos em que o paciente nao eh cadastrado com sucesso
		 */
		try {
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("", "15/09/1978", 45.9, "masculino", "masculino", "AB-");
			fail();
		} catch (Exception e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Nome do paciente nao pode ser vazio.", e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("pedro", "11/28/2000", 45.9, "masculino", "masculino", "AB-");
			fail();
		} catch (Exception e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Data invalida.", e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("pedro", "15-09-1978", 45.9, "masculino", "masculino", "AB-");
			fail();
		} catch (Exception e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Data invalida.", e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino", "masculino", "X+");
			fail();
		} catch (Exception e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Tipo sanguineo invalido.", e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino", "masculino", "");
			fail();
		} catch (Exception e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Tipo sanguineo invalido.", e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino", "masculino", "A+");
			Paciente paciente2 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino", "masculino", "A+");
			fail();
		} catch (Exception e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Paciente ja cadastrado.", e.getMessage());
		}
	}

	@Test
	public void testGetNumeroCadastros() {
		try {
			Clinica clinica = new Clinica();
			assertEquals(0, clinica.getNumeroCadastros());
			Paciente paciente1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino", "masculino", "A+");
			assertEquals(1, clinica.getNumeroCadastros());
			Paciente paciente2 = clinica.cadastraPaciente("paulo", "21/12/1932", 98.9, "masculino", "masculino", "A+");
			assertEquals(2, clinica.getNumeroCadastros());
		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
	}

	@Test
	public void testGetInfoPaciente() {
		try {
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino", "masculino", "A+");
			assertEquals("pedro", clinica.getInfoPaciente(paciente1, "Nome"));
			assertEquals("1978-09-15", clinica.getInfoPaciente(paciente1, "Data"));
			assertEquals("masculino", clinica.getInfoPaciente(paciente1, "Sexo"));
			assertEquals("masculino", clinica.getInfoPaciente(paciente1, "Genero"));
			assertEquals("A+", clinica.getInfoPaciente(paciente1, "TipoSanguineo"));
			assertEquals("45.9", clinica.getInfoPaciente(paciente1, "Peso"));
			assertEquals("37", clinica.getInfoPaciente(paciente1, "Idade"));

		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
	}

	@Test
	public void testGetProntuario() {

		try {
			final double MAXERROR = 0.01;
			Clinica clinica = new Clinica();
			clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino", "masculino", "A+");
			Paciente fromProntuario = clinica.getProntuario(0);
			assertEquals("pedro", fromProntuario.getNome());
			assertEquals("1978-09-15", fromProntuario.getData());
			assertEquals("masculino", fromProntuario.getSexo());
			assertEquals("masculino", fromProntuario.getGenero());
			assertEquals("A+", fromProntuario.getTipoSanguineo());
			assertEquals(45.9, fromProntuario.getPeso(), MAXERROR);
			assertEquals(37, fromProntuario.getIdade());
		
		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}

		try {
			/* Testa a ordenacao dos prontuarios pelo nome do paciente */
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("Caio", "13/09/1980", 90.0, "masculino", "masculino", "A+");
			Paciente paciente2 = clinica.cadastraPaciente("Bern", "15/09/1978", 59.9, "masculino", "feminino", "A+");
			Paciente paciente3 = clinica.cadastraPaciente("Zedro", "15/09/1978", 45.9, "masculino", "masculino", "A+");
			Paciente paciente4 = clinica.cadastraPaciente("Ron", "23/11/1991", 76.9, "masculino", "masculino", "A+");
			assertEquals(paciente2, clinica.getProntuario(0)); // Bern
			assertEquals(paciente1, clinica.getProntuario(1)); // Caio
			assertEquals(paciente4, clinica.getProntuario(2)); // Ron
			assertEquals(paciente3, clinica.getProntuario(3)); // Zedro
			assertNotEquals(paciente4, clinica.getProntuario(0));
			assertNotEquals(paciente2, clinica.getProntuario(3));

		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}

		try {
			Clinica clinica = new Clinica();
			clinica.getProntuario(-1);
			fail();
		} catch (Exception e) {
			assertEquals("Erro ao consultar prontuario. Indice do prontuario nao pode ser negativo.", e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			Paciente paciente1 = clinica.cadastraPaciente("Caio", "13/09/1980", 90.0, "masculino", "masculino", "A+");
			Paciente paciente2 = clinica.cadastraPaciente("Bern", "15/09/1978", 59.9, "masculino", "feminino", "A+");
			Paciente paciente3 = clinica.cadastraPaciente("Zedro", "15/09/1978", 45.9, "masculino", "masculino", "A+");
			Paciente paciente4 = clinica.cadastraPaciente("Ron", "23/11/1991", 76.9, "masculino", "masculino", "A+");
			clinica.getProntuario(5);
			fail();
		} catch (Exception e) {
			assertEquals("Erro ao consultar prontuario. Nao ha prontuarios suficientes (max = 4).", e.getMessage());
		}
	}
}
