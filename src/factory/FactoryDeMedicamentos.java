package factory;

import java.util.Set;

import exceptions.CadastroMedicamentoException;
import exceptions.NumeroInvalidoException;
import exceptions.StringInvalidaException;
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
	 * @throws StringInvalidaException
	 *             Lanca excecao acaso seja inserida alguma String(nome,
	 *             categoria, tipo) igual a null ou vazia.
	 * @throws NumeroInvalidoException
	 *             Lanca excecao acaso seja inserido algum numero, seja real ou
	 *             inteiro, menor que zero.
	 */
	public Medicamento criaMedicamento(String nome, double preco, int quantidade, Set<String> categorias, String tipo)
			throws CadastroMedicamentoException {

		Medicamento medicamento = new Medicamento(nome, preco, quantidade, categorias, tipo);

		return medicamento;
	}

}
