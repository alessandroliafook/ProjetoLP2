package factory;

import java.util.Set;

import exceptions.NumeroInvalidoException;
import exceptions.StringInvalidaException;
import medicamento.Medicamento;

public class FactoryDeMedicamentos {

	private static FactoryDeMedicamentos INSTANCE;

	private FactoryDeMedicamentos() {
	}

	/**
	 * Metodo responsavel por criar uma instancia do Factory de Medicamentos se
	 * nao existir, ou retornar a instancia existente acaso jah tenha sido
	 * criada, usando a estrategia do singleton.
	 * 
	 * @return A instancia do Factory de Medicamentos existente no sistema.
	 */
	public static FactoryDeMedicamentos getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new FactoryDeMedicamentos();
		}

		return INSTANCE;
	}

	public Medicamento criaMedicamento(String nome, double preco, int quantidade, Set<String> categorias, String tipo)
			throws StringInvalidaException, NumeroInvalidoException {

		Medicamento medicamento = new Medicamento(nome, preco, quantidade, categorias, tipo);

		return medicamento;
	}

}
