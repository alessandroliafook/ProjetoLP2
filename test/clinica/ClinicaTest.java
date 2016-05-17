package clinica;

import static org.junit.Assert.*;
import farmacia.Farmacia;
import hospital.ComiteGestor;

import org.junit.Test;

import clinica.Clinica;

public class ClinicaTest {

	@Test
	public void testCadastraPaciente() {
		/* Teste para o caso em que o paciente eh cadastrado corretamente */
		try {
			Clinica clinica = new Clinica();
			assertEquals(0, clinica.getNumeroCadastros());
			String id1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9,
					"masculino", "masculino", "AB-");
			assertEquals(1, clinica.getNumeroCadastros());
			assertEquals("pedro", clinica.getInfoPaciente(id1, "Nome"));
			assertEquals("1978-09-15", clinica.getInfoPaciente(id1, "Data"));

		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}

		/*
		 * Testes pra os casos em que o paciente nao eh cadastrado com sucesso
		 */
		try {
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("", "15/09/1978", 45.9,
					"masculino", "masculino", "AB-");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Nao foi possivel cadastrar o paciente. Nome do paciente nao pode ser vazio.",
					e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("pedro", "11/28/2000", 45.9,
					"masculino", "masculino", "AB-");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Nao foi possivel cadastrar o paciente. Data invalida.",
					e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("pedro", "15-09-1978", 45.9,
					"masculino", "masculino", "AB-");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Nao foi possivel cadastrar o paciente. Data invalida.",
					e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9,
					"masculino", "masculino", "X+");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Nao foi possivel cadastrar o paciente. Tipo sanguineo invalido.",
					e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9,
					"masculino", "masculino", "");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Nao foi possivel cadastrar o paciente. Tipo sanguineo invalido.",
					e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9,
					"masculino", "masculino", "A+");
			String id2 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9,
					"masculino", "masculino", "A+");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Nao foi possivel cadastrar o paciente. Paciente ja cadastrado.",
					e.getMessage());
		}
	}

	@Test
	public void testGetNumeroCadastros() {
		try {
			Clinica clinica = new Clinica();
			assertEquals(0, clinica.getNumeroCadastros());
			String id1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9,
					"masculino", "masculino", "A+");
			assertEquals(1, clinica.getNumeroCadastros());
			String id2 = clinica.cadastraPaciente("paulo", "21/12/1932", 98.9,
					"masculino", "masculino", "A+");
			assertEquals(2, clinica.getNumeroCadastros());
		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
	}

	@Test
	public void testGetInfoPaciente() {
		try {
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("pedro", "15/09/1978", 45.9,
					"masculino", "masculino", "A+");
			assertEquals("pedro", clinica.getInfoPaciente(id1, "Nome"));
			assertEquals("1978-09-15", clinica.getInfoPaciente(id1, "Data"));
			assertEquals("masculino", clinica.getInfoPaciente(id1, "Sexo"));
			assertEquals("masculino", clinica.getInfoPaciente(id1, "Genero"));
			assertEquals("A+", clinica.getInfoPaciente(id1, "TipoSanguineo"));
			assertEquals("45.9", clinica.getInfoPaciente(id1, "Peso"));
			assertEquals("37", clinica.getInfoPaciente(id1, "Idade"));

		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}
	}

	@Test
	public void testGetProntuario() {

		try {
			final double MAXERROR = 0.01;
			Clinica clinica = new Clinica();
			clinica.cadastraPaciente("pedro", "15/09/1978", 45.9, "masculino",
					"masculino", "A+");
			String idFromProntuario = clinica.getProntuario(0);
			assertEquals("pedro",
					clinica.getInfoPaciente(idFromProntuario, "Nome"));
			assertEquals("1978-09-15",
					clinica.getInfoPaciente(idFromProntuario, "Data"));
			assertEquals("masculino",
					clinica.getInfoPaciente(idFromProntuario, "Genero"));
			assertEquals("masculino",
					clinica.getInfoPaciente(idFromProntuario, "Sexo"));
			assertEquals("A+",
					clinica.getInfoPaciente(idFromProntuario, "TipoSanguineo"));
			assertEquals("45.9",
					clinica.getInfoPaciente(idFromProntuario, "Peso"));
			assertEquals("37",
					clinica.getInfoPaciente(idFromProntuario, "Idade"));

		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}

		try {
			/* Testa a ordenacao dos prontuarios pelo nome do paciente */
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("Caio", "13/09/1980", 90.0,
					"masculino", "masculino", "A+");
			String id2 = clinica.cadastraPaciente("Bern", "15/09/1978", 59.9,
					"masculino", "feminino", "A+");
			String id3 = clinica.cadastraPaciente("Zedro", "15/09/1978", 45.9,
					"masculino", "masculino", "A+");
			String id4 = clinica.cadastraPaciente("Ron", "23/11/1991", 76.9,
					"masculino", "masculino", "A+");
			assertEquals(id2, clinica.getProntuario(0)); // Bern
			assertEquals(id1, clinica.getProntuario(1)); // Caio
			assertEquals(id4, clinica.getProntuario(2)); // Ron
			assertEquals(id3, clinica.getProntuario(3)); // Zedro
			assertNotEquals(id4, clinica.getProntuario(0));
			assertNotEquals(id2, clinica.getProntuario(3));

		} catch (Exception e) {
			fail("Nao deveria lancar excecao");
		}

		try {
			Clinica clinica = new Clinica();
			clinica.getProntuario(-1);
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro ao consultar prontuario. Indice do prontuario nao pode ser negativo.",
					e.getMessage());
		}

		try {
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("Caio", "13/09/1980", 90.0,
					"masculino", "masculino", "A+");
			String id2 = clinica.cadastraPaciente("Bern", "15/09/1978", 59.9,
					"masculino", "feminino", "A+");
			String id3 = clinica.cadastraPaciente("Zedro", "15/09/1978", 45.9,
					"masculino", "masculino", "A+");
			clinica.getProntuario(5);
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro ao consultar prontuario. Nao ha prontuarios suficientes (max = 3).",
					e.getMessage());
		}
	}

	@Test
	public void testRealizaProcedimento() {

		try {

			// cadastrando os pacientes
			Clinica clinica = new Clinica();
			String id1 = clinica.cadastraPaciente("Hedwig", "02/01/1985",
					60.00, "masculino", "feminino", "B-");
			String id2 = clinica.cadastraPaciente("Mitridates", "05/12/1987",
					72.00, "feminino", "masculino", "AB+");
			String id3 = clinica.cadastraPaciente("Plutarco", "19/01/1979",
					85.00, "masculino", "masculino", "O+");
			String id4 = clinica.cadastraPaciente("Safo", "19/07/1990", 63.00,
					"feminino", "feminino", "A+");

			// cadastro dos orgaos
			clinica.cadastraOrgao("Pulmao", "A+");
			clinica.cadastraOrgao("Pulmao", "AB+");
			clinica.cadastraOrgao("Rim", "B-");
			clinica.cadastraOrgao("Coracao", "O-");
			clinica.cadastraOrgao("Figado", "O+");
			clinica.cadastraOrgao("Figado", "B+");
			clinica.cadastraOrgao("Pancreas", "A-");
			clinica.cadastraOrgao("Intestino", "A+");
			clinica.cadastraOrgao("Rim", "B-");
			clinica.cadastraOrgao("Figado", "B+");

			// criando a farmacia e cadastrando os medicamentos

			Farmacia farmacia = new Farmacia();

			farmacia.cadastraMedicamento("Valium", "generico", 21.50, 45,
					"analgesico");
			farmacia.cadastraMedicamento("Metamizol", "referencia", 58.30, 466,
					"analgesico,antitermico");
			farmacia.cadastraMedicamento("Morfina", "referencia", 150, 600,
					"analgesico");
			farmacia.cadastraMedicamento("Medroxyprogesterona", "generico",
					285.50, 200, "hormonal");
			farmacia.cadastraMedicamento("Duraston", "generico", 112.50, 150,
					"hormonal");
			farmacia.cadastraMedicamento("Nimesulida", "referencia", 12.50,
					150, "antiinflamatorio,antitermico,analgesico");
			farmacia.cadastraMedicamento("Penicilina", "referencia", 80.00,
					150, "antibiotico");

			// testes do metodo que realiza o procedimento
			double custoMedicamentos;

			custoMedicamentos = farmacia.verificaEstoque("Valium,Morfina");
			clinica.realizaProcedimento("Consulta clinica", id4,
					custoMedicamentos);

			custoMedicamentos = farmacia
					.verificaEstoque("Duraston,Morfina,Medroxyprogesterona");
			clinica.realizaProcedimento("Redesignacao sexual", id1,
					custoMedicamentos);

			custoMedicamentos = farmacia.verificaEstoque("Nimesulida,Morfina");
			clinica.realizaProcedimento("Transplante de orgaos", id3,
					"Coracao", custoMedicamentos);

			custoMedicamentos = farmacia
					.verificaEstoque("Valium,Metamizol,Nimesulida");
			clinica.realizaProcedimento("Consulta clinica", id1,
					custoMedicamentos);

			custoMedicamentos = farmacia.verificaEstoque("Morfina,Nimesulida");
			clinica.realizaProcedimento("Cirurgia bariatrica", id2,
					custoMedicamentos);

			custoMedicamentos = farmacia.verificaEstoque("Penicilina,Valium");
			clinica.realizaProcedimento("Consulta clinica", id2,
					custoMedicamentos);

			custoMedicamentos = farmacia
					.verificaEstoque("Metamizol,Nimesulida");
			clinica.realizaProcedimento("Consulta clinica", id1,
					custoMedicamentos);

			// verificando as alteracoes resultantes do metodo que realiza
			// procedimentos

			assertEquals("Hedwig", clinica.getInfoPaciente(id1, "Nome"));
			assertEquals("1985-01-02", clinica.getInfoPaciente(id1, "Data"));
			assertEquals("masculino", clinica.getInfoPaciente(id1, "Sexo"));
			assertEquals("masculino", clinica.getInfoPaciente(id1, "Genero"));
			assertEquals("B-", clinica.getInfoPaciente(id1, "TipoSanguineo"));
			assertEquals("60.0", clinica.getInfoPaciente(id1, "Peso"));

			assertEquals("Mitridates", clinica.getInfoPaciente(id2, "Nome"));
			assertEquals("1987-12-05", clinica.getInfoPaciente(id2, "Data"));
			assertEquals("feminino", clinica.getInfoPaciente(id2, "Sexo"));
			assertEquals("masculino", clinica.getInfoPaciente(id2, "Genero"));
			assertEquals("AB+", clinica.getInfoPaciente(id2, "TipoSanguineo"));
			assertEquals("64.8", clinica.getInfoPaciente(id2, "Peso"));

			assertEquals(3, clinica.getTotalProcedimento(id1));
			assertEquals(2, clinica.getTotalProcedimento(id2));
			assertEquals(1, clinica.getTotalProcedimento(id3));
			assertEquals(1, clinica.getTotalProcedimento(id4));

			try {
				custoMedicamentos = farmacia.verificaEstoque("Valium,Harvoni");
				clinica.realizaProcedimento("Consulta clinica", id4,
						custoMedicamentos);
			} catch (Exception e) {

				assertEquals(
						"Erro na realizacao de procedimentos. Medicamento nao cadastrado.",
						e.getMessage());
			}

			try {
				custoMedicamentos = farmacia.verificaEstoque("Valium,Harvoni");
				clinica.realizaProcedimento("Consulta clinica", "",
						custoMedicamentos);
			} catch (Exception e) {

				assertEquals(
						"Erro na realizacao de procedimentos. ID do paciente nao pode ser vazio.",
						e.getMessage());
			}

			try {
				custoMedicamentos = farmacia.verificaEstoque("Valium,Harvoni");
				clinica.realizaProcedimento("Consulta clinica", " ",
						custoMedicamentos);
			} catch (Exception e) {

				assertEquals(
						"Erro na realizacao de procedimentos. ID do paciente nao pode ser vazio.",
						e.getMessage());
			}

			
			try {
				custoMedicamentos = farmacia.verificaEstoque("Nimesulida,Morfina");
				clinica.realizaProcedimento("Transplante de orgaos", id2, "Coracao",
						custoMedicamentos);
			} catch (Exception e) {

				assertEquals(
						"Erro na realizacao de procedimentos. Banco nao possui o orgao especificado.",
						e.getMessage());
			}

			try {
				custoMedicamentos = farmacia.verificaEstoque("Nimesulida,Morfina");
				clinica.realizaProcedimento("Transplante de orgaos", id2, "",
						custoMedicamentos);
			} catch (Exception e) {

				assertEquals(
						"Erro na realizacao de procedimentos. Nome do orgao nao pode ser vazio.",
						e.getMessage());
			}
			
			
		} catch (Exception e) {
			fail("nao deve lancar excecao");
		}

	}

}
