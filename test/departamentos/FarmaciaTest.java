package departamentos;

import static org.junit.Assert.*;

import org.junit.Test;

public class FarmaciaTest {

	@Test
	public void test() {

		try {

			// testando construtor

			Farmacia farmacia = new Farmacia();

			// testando cadastro

			assertEquals("Valium", farmacia.cadastraMedicamento("Valium", "generico", 21.50, 45, "analgesico"));
			assertEquals("Metamizol",
					farmacia.cadastraMedicamento("Metamizol", "referencia", 58.30, 466, "analgesico,antitermico"));
			assertEquals("Morfina", farmacia.cadastraMedicamento("Morfina", "referencia", 150, 600, "analgesico"));
			assertEquals("Medroxyprogesterona",
					farmacia.cadastraMedicamento("Medroxyprogesterona", "generico", 285.50, 200, "hormonal"));
			assertEquals("Duraston", farmacia.cadastraMedicamento("Duraston", "generico", 112.50, 150, "hormonal"));
			assertEquals("Nimesulida", farmacia.cadastraMedicamento("Nimesulida", "referencia", 12.50, 150,
					"antiinflamatorio,antitermico,analgesico"));
			assertEquals("Penicilina",
					farmacia.cadastraMedicamento("Penicilina", "referencia", 80.00, 150, "antibiotico"));

			// teste que pega informacoes dos medicamentos

			assertEquals("Generico", farmacia.getInfoMedicamento("tipo", "Valium"));
			assertEquals("12.9", farmacia.getInfoMedicamento("preco", "Valium"));
			assertEquals("45", farmacia.getInfoMedicamento("quantidade", "Valium"));
			assertEquals("analgesico", farmacia.getInfoMedicamento("categorias", "Valium"));

			assertEquals("Generico", farmacia.getInfoMedicamento("tipo", "Medroxyprogesterona"));
			assertEquals("171.3", farmacia.getInfoMedicamento("preco", "Medroxyprogesterona"));
			assertEquals("200", farmacia.getInfoMedicamento("quantidade", "Medroxyprogesterona"));
			assertEquals("hormonal", farmacia.getInfoMedicamento("categorias", "Medroxyprogesterona"));

			assertEquals("de Referencia", farmacia.getInfoMedicamento("tipo", "Nimesulida"));
			assertEquals("12.5", farmacia.getInfoMedicamento("preco", "Nimesulida"));
			assertEquals("150", farmacia.getInfoMedicamento("quantidade", "Nimesulida"));
			assertEquals("analgesico,antiinflamatorio,antitermico",
					farmacia.getInfoMedicamento("categorias", "Nimesulida"));

			// teste metodo que atualiza informacoes dos medicamentos

			farmacia.atualizaMedicamento("Nimesulida", "preco", "15.0");
			farmacia.atualizaMedicamento("Nimesulida", "quantidade", "200");
			assertEquals("15.0", farmacia.getInfoMedicamento("preco", "Nimesulida"));
			assertEquals("200", farmacia.getInfoMedicamento("quantidade", "Nimesulida"));

			farmacia.atualizaMedicamento("Valium", "preco", "20.0");
			assertEquals("12.0", farmacia.getInfoMedicamento("preco", "Valium"));
			assertEquals("45", farmacia.getInfoMedicamento("quantidade", "Valium"));

			// teste de busca de medicamentos por categoria

			assertEquals("Valium,Nimesulida,Metamizol,Morfina", farmacia.consultaMedCategoria("analgesico"));
			assertEquals("Duraston,Medroxyprogesterona", farmacia.consultaMedCategoria("hormonal"));
			assertEquals("Nimesulida", farmacia.consultaMedCategoria("antiinflamatorio"));
			assertEquals("Penicilina", farmacia.consultaMedCategoria("antibiotico"));

			// testes de metodo que busca o medicamento por nome
			assertEquals(
					"Medicamento de Referencia: Metamizol - Preco: R$ 58,30 - Disponivel: 466 - Categorias: analgesico,antitermico",
					farmacia.consultaMedNome("Metamizol"));
			assertEquals(
					"Medicamento Generico: Medroxyprogesterona - Preco: R$ 171,30 - Disponivel: 200 - Categorias: hormonal",
					farmacia.consultaMedNome("Medroxyprogesterona"));

			// acrescentando a Hioscina
			farmacia.cadastraMedicamento("Hioscina", "referencia", 10, 300, "antiemetico");
			assertEquals(
					"Medicamento de Referencia: Hioscina - Preco: R$ 10,00 - Disponivel: 300 - Categorias: antiemetico",
					farmacia.consultaMedNome("Hioscina"));

			// testes de consulta de todos os medicamentos
			assertEquals("Hioscina,Valium,Nimesulida,Metamizol,Duraston,Penicilina,Morfina,Medroxyprogesterona",
					farmacia.getEstoqueFarmacia("preco"));
			assertEquals("Duraston,Hioscina,Medroxyprogesterona,Metamizol,Morfina,Nimesulida,Penicilina,Valium",
					farmacia.getEstoqueFarmacia("alfabetica"));

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testExceptions() {

		try {

			// repetindo as condicoes do teste anterior

			Farmacia farmacia = new Farmacia();

			farmacia.cadastraMedicamento("Valium", "generico", 21.50, 45, "analgesico");
			farmacia.cadastraMedicamento("Metamizol", "referencia", 58.30, 466, "analgesico,antitermico");
			farmacia.cadastraMedicamento("Morfina", "referencia", 150, 600, "analgesico");
			farmacia.cadastraMedicamento("Medroxyprogesterona", "generico", 285.50, 200, "hormonal");
			farmacia.cadastraMedicamento("Duraston", "generico", 112.50, 150, "hormonal");
			farmacia.cadastraMedicamento("Nimesulida", "referencia", 12.50, 150,
					"antiinflamatorio,antitermico,analgesico");
			farmacia.cadastraMedicamento("Penicilina", "referencia", 80.00, 150, "antibiotico");

			// excecoes de cadastro

			try {
				farmacia.cadastraMedicamento("", "generico", 21.50, 45, "analgesico");
				fail();

			} catch (Exception e) {
				assertEquals("Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio.",
						e.getMessage());

			}

			try {
				farmacia.cadastraMedicamento(" ", "generico", 21.50, 45, "analgesico");
				fail();

			} catch (Exception e) {
				assertEquals("Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio.",
						e.getMessage());

			}

			try {

				farmacia.cadastraMedicamento("Valium", "", 21.50, 45, "analgesico");
				fail();

			} catch (Exception e) {

				assertEquals("Erro no cadastro de medicamento. Tipo de medicamento invalido.", e.getMessage());

			}

			try {

				farmacia.cadastraMedicamento("Valium", "experimental", 21.50, 45, "analgesico");
				fail();

			} catch (Exception e) {

				assertEquals("Erro no cadastro de medicamento. Tipo de medicamento invalido.", e.getMessage());

			}

			try {
				farmacia.cadastraMedicamento("Valium", "generico", -1, 45, "analgesico");
				fail();

			} catch (Exception e) {
				assertEquals("Erro no cadastro de medicamento. Preco do medicamento nao pode ser negativo.",
						e.getMessage());

			}
			try {
				farmacia.cadastraMedicamento("Valium", "generico", 21.50, -1, "analgesico");
				fail();

			} catch (Exception e) {
				assertEquals("Erro no cadastro de medicamento. Quantidade do medicamento nao pode ser negativo.",
						e.getMessage());

			}
			try {
				farmacia.cadastraMedicamento("Valium", "generico", 21.50, 45, "");
				fail();

			} catch (Exception e) {
				assertEquals("Erro no cadastro de medicamento. Categoria invalida.", e.getMessage());
			}

			try {
				farmacia.cadastraMedicamento("Valium", "generico", 21.50, 45, "antidepressivo");
				fail();

			} catch (Exception e) {
				assertEquals("Erro no cadastro de medicamento. Categoria invalida.", e.getMessage());
			}

			// excecoes de atualizacao

			try {
				farmacia.atualizaMedicamento("Nimesulida", "nome", "Nimsulida");
				fail();

			} catch (Exception e) {
				assertEquals("Erro ao atualizar medicamento. Nome do medicamento nao pode ser alterado.",
						e.getMessage());
			}

			try {
				farmacia.atualizaMedicamento("Nimesulida", "tipo", "generico");
				fail();

			} catch (Exception e) {
				assertEquals("Erro ao atualizar medicamento. Tipo do medicamento nao pode ser alterado.",
						e.getMessage());
			}

			try {
				farmacia.atualizaMedicamento("Dorflex", "preco", "17.00");
				fail();

			} catch (Exception e) {
				assertEquals("Erro ao atualizar medicamento. Medicamento nao cadastrado.", e.getMessage());
			}

			// excecoes de busca de medicamento por categoria

			try {
				farmacia.consultaMedCategoria("antiemetico");
				fail();

			} catch (Exception e) {
				assertEquals("Erro na consulta de medicamentos. Nao ha remedios cadastrados nessa categoria.",
						e.getMessage());
			}

			try {
				farmacia.consultaMedCategoria("antialergico");
				fail();

			} catch (Exception e) {
				assertEquals("Erro na consulta de medicamentos. Categoria invalida.", e.getMessage());
			}

			// Efetua adicao de remedio antiemetico

			farmacia.cadastraMedicamento("Hioscina", "referencia", 10, 300, "antiemetico");
			assertEquals("Hioscina", farmacia.consultaMedCategoria("antiemetico"));

			// excecos de busca por nome

			try {
				farmacia.consultaMedNome("Opium");
				fail();
			} catch (Exception e) {
				assertEquals("Erro na consulta de medicamentos. Medicamento nao cadastrado.", e.getMessage());
			}

			// excecos de consulta de todos os medicamentos
			try {
				farmacia.getEstoqueFarmacia("tipo");
				fail();
			} catch (Exception e) {
				assertEquals("Erro na consulta de medicamentos. Tipo de ordenacao invalida.", e.getMessage());
			}

		} catch (Exception e) {
			fail();
		}

	}

}
