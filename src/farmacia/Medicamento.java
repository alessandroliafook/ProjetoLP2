package farmacia;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import java.io.Serializable;

import exceptions.CategoriaMedicamentoInvalidaException;
import exceptions.NomeMedicamentoException;
import exceptions.NumeroInvalidoException;
import exceptions.TipoMedicamentoException;
import factory.FactoryTipoMedicamento;

import util.VerificaCadastroMedicamento;

public class Medicamento implements Comparable<Medicamento>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3829508154930744712L;

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
	 * @throws CategoriaMedicamentoInvalidaException
	 * 			   Caso a categoria seja invalida
	 * @throws NumeroInvalidoException
	 *             Lanca excecao acaso seja inserido algum numero, seja real ou
	 *             inteiro, menor que zero.
	 * @throws TipoMedicamentoException
	 *             Caso o tipo seja invalido
	 * @throws NomeMedicamentoException
	 *             Caso o nome seja invalido
	 */
	public Medicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws NomeMedicamentoException, CategoriaMedicamentoInvalidaException, NumeroInvalidoException,
			TipoMedicamentoException {

		VerificaCadastroMedicamento.validaNomeMedicamento(nome);
		VerificaCadastroMedicamento.validaPrecoMedicamento(preco);
		VerificaCadastroMedicamento.validaQuantidadeMedicamento(quantidade);

		this.tipo = FactoryTipoMedicamento.selecionaTipo(tipo);
		this.nome = nome;
		this.preco = this.tipo.calculaPreco(preco);
		this.quantidade = quantidade;
		this.categorias = new TreeSet<CategoriasEnum>();

		List<String> listCategorias = Arrays.asList(categorias.split(","));

		for (int i = 0; i < listCategorias.size(); i++) {

			try {

				String categoria = listCategorias.get(i).toUpperCase();
				this.categorias.add(CategoriasEnum.valueOf(categoria));

			} catch (IllegalArgumentException e) {

				throw new CategoriaMedicamentoInvalidaException();
			}
		}

	}

	@Override
	/**
	 * Metodo que implementa a comparacao entre dois objetos retornando de
	 * acordo com a ordem alfabetica.
	 */
	public int compareTo(Medicamento outroMedicamento) {

		return this.getNome().compareTo(outroMedicamento.getNome());
	}

	/**
	 * Metodo que retorna uma String com as informacoes principais do
	 * medicamento.
	 * 
	 * @return String com as informacoes
	 */
	protected String informacoes() {

		String preco = String.format("%.2f", getPreco());
		String precoFormatado = preco.replace(".", ",");

		String string = "Medicamento " + getTipo() + ": " + getNome() + " - Preco: R$ " + precoFormatado
				+ " - Disponivel: " + getQuantidade() + " - Categorias: ";

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
	 * Metodo que retorna o nome do objeto.
	 */
	public String toString() {
		return getNome();
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
	 * Metodo que informa o tipo de medicamento.
	 * 
	 * @return String com o tipo do medicamento.
	 */
	public String getTipo() {
		return tipo.toString();
	}

	/**
	 * Metodo que modifica a quantidade de medicamentos. O total passa a ser
	 * aquele informado no parametro.
	 * 
	 * @param quantidade
	 *            Inteiro que passara a ser o total de medicamentos existente.
	 */
	protected void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Metodo que modifica o preco de medicamentos. O total passa a ser aquele
	 * informado no parametro.
	 * 
	 * @param preco
	 *            Double que passara a ser o total de medicamentos existente.
	 */
	protected void setPreco(double preco) {
		this.preco = tipo.calculaPreco(preco);
	}

	/**
	 * Metodo default do eclipse que compara dois objetos do tipo medicamento
	 * considerando o nome associado.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Metodo default do eclipse que compara dois objetos do tipo medicamento
	 * considerando o nome associado.
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
		return true;
	}

}
