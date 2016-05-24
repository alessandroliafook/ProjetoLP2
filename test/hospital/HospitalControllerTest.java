package hospital;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class HospitalControllerTest {

	private HospitalControler hc;
	private BufferedReader bw;

	public HospitalControllerTest() {
		this.hc = new HospitalControler();
	}

	@Before
	public void testLiberaSistema() {

		String matriculaDiretor = "";

		try {

			hc.iniciaSistema();
			matriculaDiretor = hc.liberaSistema("c041ebf8", "paulo", "01/01/2001");

			assertEquals("12016001", matriculaDiretor);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(hc.getInfoFuncionario(matriculaDiretor, "Nome"), "paulo");
			assertEquals(hc.getInfoFuncionario(matriculaDiretor, "Data"), "2001-01-01");
			assertEquals(hc.getInfoFuncionario(matriculaDiretor, "Cargo"), "Diretor Geral");
			hc.liberaSistema("c041ebf8", "joao", "02/03/2011");
			fail("Nao deve chegar aqui");
		} catch (Exception e) {
			assertEquals("Erro ao liberar o sistema. Sistema liberado anteriormente.", e.getMessage());
		}

		try {
			hc.liberaSistema("chaveErrada", "pedro", "15/07/1998");
			fail("Nao deve lancar excecao");
		} catch (Exception e) {
			assertEquals("Erro ao liberar o sistema. Sistema liberado anteriormente.", e.getMessage());
		}
	}

	@Test
	public void testExportaFichaPaciente() {

		String matricula = "12016001";
		String senha = "20011201";

		try {
			hc.login(matricula, senha);
			String mat = hc.cadastraFuncionario("Medico", "Medico", "01/01/1991");
			String id = hc.cadastraPaciente("paciente1", "01/01/1991", 78, "masculino", "masculino", "A+");
			hc.cadastraMedicamento("remedio1", "generico", 15.2, 100, "ANTIBIOTICO");
			hc.logout();
			hc.login(mat, "1991" + mat.substring(0, 4));

			hc.realizaProcedimento("cirurgia bariatrica", id, "remedio1");
			hc.realizaProcedimento("cirurgia bariatrica", id, "remedio1");
			hc.realizaProcedimento("cirurgia bariatrica", id, "remedio1");
			hc.realizaProcedimento("cirurgia bariatrica", id, "remedio1");

			hc.logout();

			hc.login(matricula, senha);
			hc.exportaFichaPaciente(id);

			String nomeArquivo = hc.getInfoPaciente(id, "Nome");
			nomeArquivo = nomeArquivo.replace(" ", "_");
			nomeArquivo += String.valueOf(LocalDate.now().getYear()) + "_" + String.valueOf(LocalDate.now().getMonthValue())
					+ "_" + String.valueOf(LocalDate.now().getDayOfMonth()) + ".txt";

			File file = new File("fichas_pacientes", nomeArquivo);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {
				System.out.println(br.readLine());
			}
			br.close();

			if (!file.exists()) {
				fail("Deveria ter criado o arquivo");
			}

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
