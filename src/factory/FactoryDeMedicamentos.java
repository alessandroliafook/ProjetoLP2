package factory;

import java.util.Set;

import exceptions.NumeroInvalido;
import exceptions.StringInvalida;
import medicamento.Medicamento;

public class FactoryDeMedicamentos {

	private static FactoryDeMedicamentos INSTANCE;

	private FactoryDeMedicamentos() {
	}

	public static FactoryDeMedicamentos getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new FactoryDeMedicamentos();
		}

		return INSTANCE;
	}

	public Medicamento criaMedicamento(String nome, double preco, int quantidade, Set<String> categorias, String tipo)
			throws StringInvalida, NumeroInvalido {

		Medicamento medicamento = new Medicamento(nome, preco, quantidade, categorias, tipo);

		return medicamento;
	}

}
