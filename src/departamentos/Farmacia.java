package departamentos;

import java.util.Set;
import java.util.TreeSet;

import exceptions.NumeroInvalido;
import exceptions.StringInvalida;
import factory.FactoryDeMedicamentos;
import medicamento.CategoriasEnum;
import medicamento.Medicamento;

public class Farmacia {

	public static Farmacia INSTANCE;
	Set<Medicamento> estoqueDeMedicamentos;
	FactoryDeMedicamentos farmaceutico;

	private Farmacia() {

		this.estoqueDeMedicamentos = new TreeSet<Medicamento>();
		farmaceutico = FactoryDeMedicamentos.getInstance();

	}

	public Farmacia getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new Farmacia();
		}

		return INSTANCE;
	}

	public void cadastraMedicamento(String tipo, int quantidade, double preco, String nome, Set<String> categorias)
			throws StringInvalida, NumeroInvalido {

		Medicamento medicamento = farmaceutico.criaMedicamento(nome, preco, quantidade, categorias, tipo);

		if (estoqueDeMedicamentos.contains(medicamento)) {
			estocaMedicamento(nome, quantidade);

		} else {

			estoqueDeMedicamentos.add(medicamento);
		}

	}

	public void estocaMedicamento(String nomeMedicamento, int quantidadeASerEstocada) {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equals(nomeMedicamento)) {

				int total = medicamento.getQuantidade() + quantidadeASerEstocada;

				medicamento.setQuantidade(total);
			}

		}

	}

	public Medicamento forneceMedicamento(String nomeMedicamento, int quantidadeFornecida)
			throws NumeroInvalido, StringInvalida {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equals(nomeMedicamento)) {

				if (medicamento.getQuantidade() > quantidadeFornecida) {

					int total = medicamento.getQuantidade() - quantidadeFornecida;

					medicamento.setQuantidade(total);

					return medicamento;
				}

				throw new NumeroInvalido("quantidade de medicamento solicitada",
						"existe apenas" + medicamento.getQuantidade() + "no estoque");
			}

		}

		throw new StringInvalida("medicamento solicitado", "nao existe no estoque");

	}

	public String consultaPorCategoria(String categoria) {

		CategoriasEnum enumCategoria = CategoriasEnum.valueOf(categoria);
		String listaDeMedicamentos = "";

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getCategorias().contains(enumCategoria)) {
				listaDeMedicamentos = listaDeMedicamentos + medicamento.toString() + "\n";
			}

		}

		return listaDeMedicamentos;

	}

	public String consultaPorNome(String nomeDoRemedio) throws StringInvalida {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equals(nomeDoRemedio)) {
				return medicamento.toString();
			}

		}

		throw new StringInvalida("nome do remedio", "nao cadastrado no sistema");

	}

}
