package departamentos;

import java.util.Set;
import java.util.TreeSet;

import exceptions.CadastroMedicamentoException;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.StringInvalidaException;

import factory.FactoryDeMedicamentos;

import medicamento.CategoriasEnum;
import medicamento.Medicamento;

import util.Verificacao;

public class Farmacia {

	public static Farmacia INSTANCE;
	Set<Medicamento> estoqueDeMedicamentos;
	FactoryDeMedicamentos farmaceutico;

	/**
	 * Construtor privado para inicializar uma unica instancia de farmacia,
	 * aplicando o padrao de projeto singleton.
	 */
	private Farmacia() {

		this.estoqueDeMedicamentos = new TreeSet<Medicamento>();
		farmaceutico = new FactoryDeMedicamentos();

	}

	/**
	 * Metodo para gerar uma instancia de Farmacia acaso nao exista, garantindo
	 * que havera apenas uma instancia de farmacia no sistema, conforme o padrao
	 * de projeto singleton.
	 * 
	 * @return A unica instancia de farmacia a ser criada no sistema.
	 */
	public Farmacia getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new Farmacia();
		}

		return INSTANCE;
	}

	/**
	 * Metodo que cadastra um medicamento no estoque da farmacia. Acaso o
	 * medicamento ja exista na farmacia, apenas adiciona a quantidade informada
	 * no objeto ja existente.
	 * 
	 * @param tipo
	 *            String que informa se o medicamento eh generico ou de
	 *            referencia.
	 * @param quantidade
	 *            Inteiro que indica a quantidade de medicamentos que se deseja
	 *            cadastrar no sistema.
	 * @param preco
	 *            Numero Real referente ao valor associado ao medicamento. Acaso
	 *            o medicamento seja do tipo generico, o preco associado sera
	 *            40% menor que o informado.
	 * @param nome
	 *            String referente ao nome do medicamento que se deseja
	 *            cadastrar.
	 * @param categorias
	 *            Conjunto de Strings com os nomes das categorias associadas ao
	 *            medicamento.
	 * @throws StringInvalidaException
	 *             Lanca excecao personalizada acaso qualques das String
	 *             informadas seja vazia ou igual a null.
	 * @throws NumeroInvalidoException
	 *             Lanca excecao acaso qualquer dos valores informados sejam
	 *             menores que zero.
	 */
	public String cadastraMedicamento(String tipo, int quantidade, double preco, String nome, Set<String> categorias)
			throws CadastroMedicamentoException {

		Medicamento medicamento = farmaceutico.criaMedicamento(nome, preco, quantidade, categorias, tipo);

		if (estoqueDeMedicamentos.add(medicamento)) {
			return nome;

		} else {

			for (Medicamento medicamentoEstocado : estoqueDeMedicamentos) {

				if (medicamentoEstocado.equals(medicamento)) {

					int qntTotal = medicamentoEstocado.getQuantidade() + quantidade;
					medicamentoEstocado.setQuantidade(qntTotal);

				}
			}

			return nome;
		}

	}

	/**
	 * Metodo que acrescenta a quantidade estocada do medicamento associado ao
	 * nome informado.
	 * 
	 * @param nomeMedicamento
	 *            String referente ao nome do medicamento que se pretende
	 *            aumentar a quantidade estocada.
	 * @param quantidadeASerEstocada
	 *            Inteiro relativo a quantidade que se prentede aumentar.
	 * @throws StringInvalidaException
	 *             Lanca excecao personalizada acaso o nome do medicamento seja
	 *             vazio ou igual a null.
	 * @throws NumeroInvalidoException
	 *             Lanca excecao personalizada acaso a quantidade a ser estocada
	 *             seja menor que zero.
	 */
	public void estocaMedicamento(String nomeMedicamento, int quantidadeASerEstocada)
			throws CadastroMedicamentoException {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equals(nomeMedicamento)) {

				int total = medicamento.getQuantidade() + quantidadeASerEstocada;

				medicamento.setQuantidade(total);
			}

		}

	}

	/**
	 * Metodo que fornece um objeto do tipo medicamento solicitado pelo nome,
	 * reduzindo a quantidade pedida do total existente.
	 * 
	 * @param nomeMedicamento
	 *            String contendo o nome do medicamento a ser entregue.
	 * @param quantidadeFornecida
	 *            Inteiro referente a quantidade de medicamentos a serem
	 *            fornecidas.
	 * @return Objeto do tipo Medicamento com a quantidade solicitada.
	 * @throws NumeroInvalidoException
	 * @throws StringInvalidaException
	 */
	public Medicamento forneceMedicamento(String nomeMedicamento, int quantidadeSolicitada)
			throws NumeroInvalidoException, StringInvalidaException {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equals(nomeMedicamento)) {

				if (medicamento.getQuantidade() >= quantidadeSolicitada) {

					int total = medicamento.getQuantidade() - quantidadeSolicitada;
					medicamento.setQuantidade(total);

					return medicamento;
				}

				throw new NumeroInvalidoException("quantidade de medicamento solicitada",
						"existe apenas" + medicamento.getQuantidade() + "no estoque");
			}

		}

		throw new StringInvalidaException("medicamento solicitado", "nao existe no estoque");

	}

	/**
	 * Metodo que consulta a lista de medicamentos associados a uma categoria
	 * informada.
	 * 
	 * @param categoria
	 * @return
	 * @throws ObjetoNaoEncontradoException
	 */
	public String consultaMedCategoria(String categoria) throws ObjetoNaoEncontradoException {

		Verificacao.validaCategoria(categoria);

		int medicamentosEncontrados = 0;
		StringBuilder listaDeMedicamentos = new StringBuilder();
		CategoriasEnum enumCategoria = CategoriasEnum.valueOf(categoria);

		for (Medicamento medicamento : estoqueDeMedicamentos) {


			if (medicamento.getCategorias().contains(enumCategoria)) {

				listaDeMedicamentos.append(medicamento.getNome()).append(",");
				medicamentosEncontrados += 1;
			}

		}

		if (medicamentosEncontrados > 0) {
			listaDeMedicamentos.deleteCharAt(listaDeMedicamentos.length() - 1);
		}

		return listaDeMedicamentos.toString();


	}

	/**
	 * Metodo que retorna as informacoes importantes do medicamento solicitado.
	 * 
	 * @param nomeDoRemedio
	 *            String relacionada ao nome do medicamento que se pretende
	 *            obter as informacoes.
	 * @return String com as informacoes do medicamento solicitado.
	 * @throws StringInvalidaException
	 *             Lanca excecao acaso o nome informado seja igual a null ou
	 *             vazio.
	 */
	public String getInfoMedicamento(String nomeDoRemedio) throws StringInvalidaException {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equals(nomeDoRemedio)) {
				return medicamento.toString();
			}

		}

		throw new StringInvalidaException("nome do remedio", "nao cadastrado no sistema");

	}

}
