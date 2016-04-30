package medicamento;

import java.util.HashSet;
import java.util.Set;

import exceptions.CadastroMedicamentoException;
import exceptions.NumeroInvalidoException;
import exceptions.StringInvalidaException;

import factory.FactoryTipoMedicamento;

import util.VerificaCadastro;

public class Medicamento implements Comparable<Medicamento> {

	private String nome;
	private double preco;
	private int quantidade;
	private Set<CategoriasEnum> categorias;
	private TipoMedicamentoIF tipo;

	/**
	 * Construtor do objeto Medicamento.
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
	public Medicamento(String nome, double preco, int quantidade, Set<String> categorias, String tipo)
			throws CadastroMedicamentoException {

		VerificaCadastro.validaNomeMedicamento(nome);
		VerificaCadastro.validaPrecoMedicamento(preco);
		VerificaCadastro.validaQuantidadeMedicamento(quantidade);

		selecionaTipo(tipo);
		this.nome = nome;
		this.preco = this.tipo.calculaPreco(preco);
		this.quantidade = quantidade;
		this.categorias = new HashSet<CategoriasEnum>();

		for (String categoria : categorias) {

			this.categorias.add(CategoriasEnum.valueOf(categoria));
		}

	}

	/**
	 * Metodo que escolhe qual objeto de TipoMedicamentoIF sera associado a
	 * instancia tipo.
	 * 
	 * @param tipo
	 *            String com o nome do tipo a ser escolhido dentre as
	 *            opcoes(referencia, generico).
	 */
	private void selecionaTipo(String tipo) {
		switch (tipo) {
		case "referencia":
			this.tipo = FactoryTipoMedicamento.criaMedicamentoReferencia();
		case "generico":
			this.tipo = FactoryTipoMedicamento.criaMedicamentoGenerico();
		}
	}

	@Override
	/**
	 * Metodo que implementa a comparacao entre dois objetos retornando o mais
	 * barato como precedente ao mais caro.
	 */
	public int compareTo(Medicamento outroMedicamento) {

		if (this.preco < outroMedicamento.getPreco()) {
			return 1;

		} else if (this.preco == outroMedicamento.getPreco()) {
			return 0;

		} else {
			return -1;
		}
	}

	/**
	 * Metodo que retorna uma String com as informacoes principais do
	 * medicamento.
	 */
	public String toString() {

		String string = "Medicamento de Referencia: " + getNome() + " - Preco: R$ " + getPreco() + " - Disponivel: "
				+ getQuantidade() + " - Categorias: ";

		StringBuilder builder = new StringBuilder(string);

		for (CategoriasEnum categoria : categorias) {

			builder.append(categoria.name().toLowerCase()).append(",");

		}

		if (categorias.size() > 0) {
			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}

	/**
	 * Metodo que informa o nome do medicamento.
	 * 
	 * @return String associada ao nome do medicamento.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo que informa o preco do medicamento.
	 * 
	 * @return double associado ao preco do medicamento.
	 */
	public double getPreco() {
		return this.preco;
	}

	/**
	 * Metodo que informa a lista de categorias do medicamento.
	 * 
	 * @return Set de CategoriasEnum com as categorias associadas ao objeto.
	 */
	public Set<CategoriasEnum> getCategorias() {
		return this.categorias;
	}

	/**
	 * Metodo que informa a quantidade de medicamentos existente.
	 * 
	 * @return Inteiro assoaciado a quantidade de medicamentos.
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Metodo que modifica a quantidade de medicamentos. O total passa a ser
	 * aquele informado no parametro.
	 * 
	 * @param quantidade
	 *            Inteiro que passara a ser o total de medicamentos existente.
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Metodo default do eclipse que compara dois objetos do tipo medicamento
	 * considerando o nome e o tipo associados.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	/**
	 * Metodo default do eclipse que compara dois objetos do tipo medicamento
	 * considerando o nome e o tipo associados.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
