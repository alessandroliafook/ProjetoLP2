package departamentos;

import java.awt.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import exceptions.NumeroInvalidoException;
import exceptions.StringInvalidaException;
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
			throws StringInvalidaException, NumeroInvalidoException {

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
			throws NumeroInvalidoException, StringInvalidaException {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equals(nomeMedicamento)) {

				if (medicamento.getQuantidade() > quantidadeFornecida) {

					int total = medicamento.getQuantidade() - quantidadeFornecida;

					medicamento.setQuantidade(total);

					return medicamento;
				}

				throw new NumeroInvalidoException("quantidade de medicamento solicitada",
						"existe apenas" + medicamento.getQuantidade() + "no estoque");
			}

		}

		throw new StringInvalidaException("medicamento solicitado", "nao existe no estoque");

	}

	public ArrayList<String> consultaPorCategoria(String categoria) {

		ArrayList<String> listaDeMedicamentos = new ArrayList<String>();
		
		CategoriasEnum enumCategoria = CategoriasEnum.valueOf(categoria);
		
		 		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getCategorias().contains(enumCategoria)) {
				listaDeMedicamentos.add(medicamento.getNome());
			}

		}

		return listaDeMedicamentos;

	}

	public String consultaPorNome(String nomeDoRemedio) throws StringInvalidaException {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equals(nomeDoRemedio)) {
				return medicamento.toString();
			}

		}

		throw new StringInvalidaException("nome do remedio", "nao cadastrado no sistema");

	}

}
