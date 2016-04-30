package factory;

import java.util.Set;

import exceptions.CategoriaMedicamentoInvalidaException;
import exceptions.NomeMedicamentoException;
import exceptions.NumeroInvalidoException;
import exceptions.TipoMedicamentoException;
import medicamento.Medicamento;

public class FactoryDeMedicamentos {

	public FactoryDeMedicamentos() {
	}

	/**
	 * Metodo responsavel por criar um objeto do tipo Medicamento.
	 * 
	 * @param nome
	 *            String a ser definida como o nome do medicamento.
	 * @param preco
	 *            Valor real do custo do medicamento. Existe uma chamada
	 *            polimorfica pois acaso o medicamento seja generico o valor
	 *            associado tera um desconto de 40% sobre o valor inserido.
	 * @param quantidade
	 *            Inteiro que indica a quantidade de medicamentos existentes.
	 * @param categorias
	 *            Conjunto de Strings com as categorias associadas ao
	 *            medicamento.
	 * @param tipo
	 *            String que indica o tipo de medicamento (referencia ou
	 *            generico).
	 * @throws TipoMedicamentoException
	 *             Lanca excecao acaso o tipo informado nao exista.
	 * @throws CategoriaMedicamentoInvalidaException
	 *             Lanca excecao acaso a categoria informada nao exista.
	 * @throws NomeMedicamentoException
	 *             Lanca excecao acaso seja inserido nome vazio.
	 * @throws NumeroInvalidoException
	 *             Lanca excecao acaso seja inserido algum numero, seja real ou
	 *             inteiro, menor que zero.
	 */
	public Medicamento criaMedicamento(String nome, double preco, int quantidade, Set<String> categorias, String tipo)
			throws NomeMedicamentoException, CategoriaMedicamentoInvalidaException, NumeroInvalidoException,
			TipoMedicamentoException {

		Medicamento medicamento = new Medicamento(nome, preco, quantidade, categorias, tipo);

		return medicamento;
	}

}
