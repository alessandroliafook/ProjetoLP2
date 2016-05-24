package hospital;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;

import hospital.ComiteGestor;

import org.junit.Before;
import org.junit.Test;

public class ComiteGestorTest {

	private ComiteGestor comite;

	public ComiteGestorTest() {
		this.comite = new ComiteGestor();
	}

	@Before
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
	public void testLoginLogout() {

		String matricula = "12016001";
		String senha = "20011201";

		try {
			comite.login(matricula, senha);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		try {

			String matricula2 = comite.cadastraFuncionario("nome", "Medico", "05/05/1995");
			String senha2 = "1995" + matricula2.substring(0, 4);

			comite.logout();

			comite.login(matricula2, senha2);

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testFechaSistema() {

		String matricula = "12016001";
		String senha = "20011201";

		try {
			comite.login(matricula, senha);

			comite.fechaSistema();
			fail("nao deve chegar aqui");

		} catch (Exception e) {
			assertEquals("Nao foi possivel fechar o sistema. Um funcionario ainda esta logado: paulo.", e.getMessage());
		}

		try {
			comite.logout();
			comite.fechaSistema();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	public void testExportaFichaPaciente() {

		String matricula = "12016001";
		String senha = "20011201";

		try {
			comite.login(matricula, senha);
			String mat = comite.cadastraFuncionario("Medico", "Medico", "01/01/1991");
			String id = comite.cadastraPaciente("paciente1", "01/01/1991", 78, "masculino", "masculino", "A+");
			comite.cadastraMedicamento("remedio1", "generico", 15.2, 100, "ANTIBIOTICO");
			comite.logout();
			comite.login(mat, "1991" + mat.substring(0, 4));

			comite.realizaProcedimento("cirurgia bariatrica", id, "remedio1");
			comite.realizaProcedimento("cirurgia bariatrica", id, "remedio1");
			comite.realizaProcedimento("cirurgia bariatrica", id, "remedio1");
			comite.realizaProcedimento("cirurgia bariatrica", id, "remedio1");

			comite.logout();

			comite.login(matricula, senha);
			System.out.println("ta aqui1");
			comite.exportaFichaPaciente(id);
			System.out.println("ta aqui2");

			String nomeArquivo = comite.getInfoPaciente(id, "Nome");
			nomeArquivo = nomeArquivo.replace(" ", "_");
			nomeArquivo += LocalDate.now().getYear() + "_" + LocalDate.now().getMonth() + "_"
					+ LocalDate.now().getDayOfMonth() + ".txt";

			String ficha = comite.getFicha(id);

			File file = new File("fichas_pacientes", nomeArquivo);

			if (!file.exists()) {
				fail("falhou");
			} else {

				String compareFicha = "";

				FileReader fr = new FileReader(file);
				BufferedReader bw = new BufferedReader(fr);

				while (bw.ready()) {
					compareFicha += bw.readLine();
				}

				if (!ficha.equals(compareFicha)) {
					fail("falhou aqui");
				}

			}

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
