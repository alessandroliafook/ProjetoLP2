package farmacia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.Serializable;

import exceptions.AtualizaMedicamentoException;
import exceptions.CadastroMedicamentoException;
import exceptions.ConsultaMedicamentoException;
import exceptions.VerificaEstoqueException;
import factory.FactoryDeMedicamentos;
import util.VerificaCadastroMedicamento;

public class Farmacia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1325300249760149147L;

	Set<Medicamento> estoqueDeMedicamentos;
	FactoryDeMedicamentos farmaceutico;

	/**
	 * Construtor da classe farmacia que inicia o estoque vazio e a factory de
	 * medicamentos.
	 */
	public Farmacia() {

		this.estoqueDeMedicamentos = new TreeSet<Medicamento>();
		farmaceutico = new FactoryDeMedicamentos();

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
	 * @return Retorna o nome do medicamento cadastrado, acaso a operacao tenha
	 *         sido realizada com sucesso.
	 * @throws CadastroMedicamentoException
	 *             Caso os dados sejam invalidos e nao seja possivel realizar o
	 *             cadastro
	 */
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws CadastroMedicamentoException {

		Medicamento medicamento;

		try {

			medicamento = farmaceutico.criaMedicamento(nome, tipo, preco, quantidade, categorias);

		} catch (Exception e) {

			throw new CadastroMedicamentoException(e.getMessage());

		}

		if (this.estoqueDeMedicamentos.contains(medicamento)) {

			for (Medicamento medicamentoEstocado : this.estoqueDeMedicamentos) {

				if (medicamentoEstocado.equals(medicamento)) {

					int qntTotal = medicamentoEstocado.getQuantidade() + quantidade;
					medicamentoEstocado.setQuantidade(qntTotal);
					return medicamentoEstocado.getNome();
				}

			}

		}

		this.estoqueDeMedicamentos.add(medicamento);
		return medicamento.getNome();

	}

	/**
	 * Metodo que atualiza o preco ou a quantidade de um medicamento existente.
	 * 
	 * @param nome
	 *            String contendo o nome do medicamento que se prentende
	 *            atualizar.
	 * @param atributo
	 *            String com o nome do atributo que se pretende atualizar, seja
	 *            preco ou quantidade.
	 * @param novoValor
	 *            double com o novo valor da ser atribuido, observe que no caso
	 *            da quantidade, o metodo converte o valor para inteiro.
	 * @throws AtualizaMedicamentoException
	 *             Lanca excecao acaso seja solicitado para modificar nome,
	 *             tipo, um atributo que nao exista, ou um medicamento nao
	 *             cadastrado no sistema.
	 */
	public void atualizaMedicamento(String nome, String atributo, String novoValor)
			throws AtualizaMedicamentoException {

		for (Medicamento medicamento : this.estoqueDeMedicamentos) {

			if (medicamento.getNome().equalsIgnoreCase(nome)) {

				switch (atributo.toLowerCase()) {

				case "preco":
					double preco = Double.parseDouble(novoValor.trim());
					medicamento.setPreco(preco);
					return;

				case "quantidade":
					int quantidade = Integer.parseInt(novoValor.trim());
					medicamento.setQuantidade(quantidade);
					return;

				case "nome":
					throw new AtualizaMedicamentoException("Nome do medicamento nao pode ser alterado.");

				case "tipo":
					throw new AtualizaMedicamentoException("Tipo do medicamento nao pode ser alterado.");

				default:
					throw new AtualizaMedicamentoException("Atributo do medicamento invalido.");

				}

			}

		}

		throw new AtualizaMedicamentoException("Medicamento nao cadastrado.");

	}

	/**
	 * Metodo que verifica se a lista de medicamentos existe no estoque.
	 * 
	 * @param nomeMedicamentos
	 *            String com os nomes dos medicamentos que se deseja verificar
	 * @return boolean com true se a lista inteira existir.
	 * @throws VerificaEstoqueException
	 *             - Lanca excecao acaso nao tenha quantidade suficiente para o
	 *             fornecimento ou acaso o medicamento nao exista no estoque.
	 */
	public double verificaEstoque(String nomeMedicamentos) throws Exception {

		VerificaCadastroMedicamento.validaNomeMedicamento(nomeMedicamentos);

		String[] listaDeMedicamentos = nomeMedicamentos.split(",");

		Map<String, Integer> medicamentos = new TreeMap<String, Integer>();

		for (String nomeDoMedicamento : listaDeMedicamentos) {

			if (!medicamentos.containsKey(nomeDoMedicamento)) {
				medicamentos.put(nomeDoMedicamento, new Integer(1));

			} else {
				Integer qntSolicitada = medicamentos.get(nomeDoMedicamento) + 1;
				medicamentos.put(nomeDoMedicamento, qntSolicitada);

			}
		}

		double gastosComMedicamentos = 0.0;

		for (String nomeDoMedicamento : medicamentos.keySet()) {
			boolean contem = false;

			for (Medicamento medicamento : estoqueDeMedicamentos) {

				if (medicamento.getNome().equalsIgnoreCase(nomeDoMedicamento)
						&& medicamento.getQuantidade() >= medicamentos.get(nomeDoMedicamento)) {
					contem = true;
					gastosComMedicamentos += medicamento.getPreco() * medicamentos.get(nomeDoMedicamento);
				}
			}

			if (!contem) {
				throw new VerificaEstoqueException();
			}
		}
		return gastosComMedicamentos;
	}

	/**
	 * Metodo que fornece o preco do medicamento solicitado pelo nome, reduzindo
	 * a quantidade estocada em uma unidade.
	 * 
	 * @param nomeMedicamento
	 *            String contendo o nome do medicamento a ser entregue.
	 * @return O preco do medicamento solicitado.
	 * @throws ConsultaMedicamentoException
	 *             Lanca excecao acaso o medicamento pesquisado nao exista no
	 *             estoque.
	 */
	public double forneceMedicamento(String nomeMedicamento) throws ConsultaMedicamentoException {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equalsIgnoreCase(nomeMedicamento)) {

				int total = medicamento.getQuantidade() - 1;
				medicamento.setQuantidade(total);
				return medicamento.getPreco();

			}
		}

		throw new ConsultaMedicamentoException("Medicamento nao cadastrado");
	}

	/**
	 * Metodo que consulta a lista de medicamentos associados a uma categoria
	 * informada.
	 * 
	 * @param categoria
	 *            - String com o nome da categoria associada aos medicamentos
	 *            que se pretende listar.
	 * @return String com a lista de medicamentos que contenham a categoria
	 *         pesquisada.
	 * @throws ConsultaMedicamentoException
	 *             Lanca excecao acaso a categoria nao exista, ou nao tenha
	 *             nenhum medicamento associado a mesma.
	 */
	public String consultaMedCategoria(String categoria) throws ConsultaMedicamentoException {

		CategoriasEnum enumCategoria;

		try {

			enumCategoria = CategoriasEnum.valueOf(categoria.toUpperCase());

		} catch (IllegalArgumentException e) {

			throw new ConsultaMedicamentoException("Categoria invalida.");

		}

		int medicamentosEncontrados = 0;
		List<Medicamento> listaDeMedicamentos = new ArrayList<Medicamento>(this.estoqueDeMedicamentos);
		ComparaPorPreco ordenaPorPreco = new ComparaPorPreco();
		Collections.sort(listaDeMedicamentos, ordenaPorPreco);
		List<String> listaPorCategoria = new ArrayList<String>();

		for (int indice = 0; indice < listaDeMedicamentos.size(); indice++) {

			Medicamento medicamento = listaDeMedicamentos.get(indice);

			if (medicamento.getCategorias().contains(enumCategoria)) {

				listaPorCategoria.add(medicamento.getNome());
				medicamentosEncontrados += 1;
			}

		}

		if (medicamentosEncontrados > 0) {

			String string = String.join(",", listaPorCategoria);
			return string;

		} else {

			throw new ConsultaMedicamentoException("Nao ha remedios cadastrados nessa categoria.");
		}
	}

	/**
	 * Metodo que retorna as informacoes importantes do medicamento solicitado.
	 * 
	 * @param nomeDoRemedio
	 *            String relacionada ao nome do medicamento que se pretende
	 *            obter as informacoes.
	 * @return String com as informacoes do medicamento solicitado.
	 * @throws ConsultaMedicamentoException
	 *             Caso o medicamento nao esteja cadastrado no sistema
	 */
	public String consultaMedNome(String nomeDoRemedio) throws ConsultaMedicamentoException {

		for (Medicamento medicamento : estoqueDeMedicamentos) {

			if (medicamento.getNome().equalsIgnoreCase(nomeDoRemedio)) {
				return medicamento.informacoes();
			}

		}

		throw new ConsultaMedicamentoException("Medicamento nao cadastrado.");

	}

	/**
	 * Metodo que retorna uma a lista de medicamentos armazenados na farmacia.
	 * 
	 * @param ordenacao
	 *            String com o criterio de ordenacao desejado, que deve ser por
	 *            preco ou ordem alfabetica.
	 * @return String com os nomes dos medicamentos existentes na farmacia
	 *         ordenados segundo o parametro de ordenacao solicitado.
	 * @throws ConsultaMedicamentoException
	 *             Lanca excecao acaso o criterio de ordenacao nao seja por
	 *             preco ou ordem alfabética.
	 */
	public String getEstoqueFarmacia(String ordenacao) throws ConsultaMedicamentoException {

		List<String> listaNomesMedicamentos = new ArrayList<String>();
		String string = "";

		switch (ordenacao.toLowerCase()) {

		case "preco":

			List<Medicamento> listaDeMedicamentos = new ArrayList<Medicamento>(this.estoqueDeMedicamentos);
			ComparaPorPreco ordenaPorPreco = new ComparaPorPreco();
			Collections.sort(listaDeMedicamentos, ordenaPorPreco);

			for (int indice = 0; indice < listaDeMedicamentos.size(); indice++) {
				listaNomesMedicamentos.add(listaDeMedicamentos.get(indice).getNome());
			}

			string = String.join(",", listaNomesMedicamentos);
			return string;

		case "alfabetica":

			for (Medicamento medicamento : this.estoqueDeMedicamentos) {
				listaNomesMedicamentos.add(medicamento.getNome());
			}

			string = String.join(",", listaNomesMedicamentos);
			return string;

		default:
			throw new ConsultaMedicamentoException("Tipo de ordenacao invalida.");

		}

	}

	/**
	 * Metodo que consulta o atributo escolhido do medicamento escolhido.
	 * 
	 * @param atributoDoMedicamento
	 *            String com o atributo que se deseja informacao.
	 * @param nomeMedicamento
	 *            Nome do medicamento que se deseja a informacao de seu
	 *            atributo.
	 * @return String contendo a informacao solicitada
	 * @throws ConsultaMedicamentoException
	 *             retorna excecao acaso o atributo nao exista.
	 */
	public String getInfoMedicamento(String atributoDoMedicamento, String nomeMedicamento)
			throws ConsultaMedicamentoException {

		for (Medicamento medicamentoEstocado : this.estoqueDeMedicamentos) {

			if (medicamentoEstocado.getNome().equalsIgnoreCase(nomeMedicamento)) {

				switch (atributoDoMedicamento.toLowerCase()) {

				case "nome":
					return medicamentoEstocado.getNome();

				case "preco":

					String precoString = String.format("%.1f", medicamentoEstocado.getPreco());
					String preco = precoString.replace(",", ".");

					return preco;

				case "quantidade":
					return String.valueOf(medicamentoEstocado.getQuantidade());

				case "categorias":

					List<String> listaDeCategorias = new ArrayList<String>();

					for (CategoriasEnum categoria : medicamentoEstocado.getCategorias()) {
						listaDeCategorias.add(categoria.name().toLowerCase());
					}

					String string = String.join(",", listaDeCategorias);
					return string;

				case "tipo":
					return medicamentoEstocado.getTipo();

				default:
					throw new ConsultaMedicamentoException(
							"Nao ha atributo com o nome especificado associado ao medicamento.");

				}

			}

		}

		throw new ConsultaMedicamentoException("Medicamento nao cadastrado.");
	}

}
