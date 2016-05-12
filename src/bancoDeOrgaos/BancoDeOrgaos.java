package bancoDeOrgaos;

import java.util.HashMap;
import java.util.Map;

import factory.FactoryDeOrgao;

public class BancoDeOrgaos {

	private FactoryDeOrgao facOrgao;
	private Map<Orgao, Integer> bancoDeOrgaos;

	public BancoDeOrgaos() {
		bancoDeOrgaos = new HashMap<Orgao, Integer>();
		facOrgao = new FactoryDeOrgao();
	}

	/**
	 * Metodo que adiciona um novo orgao ao banco de orgaos
	 * 
	 * @param nome
	 *            Nome do orgao a ser adicionado
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao ser adicionado
	 * 
	 * @throws Exception
	 *             Caso o nome ou o tipo sanguineo do orgao sejam vazios
	 */
	public void adicionaOrgao(String nome, String tipoSanguineo)
			throws Exception {
		Orgao orgao = facOrgao.criaOrgao(nome, tipoSanguineo);
		adicionaOrgaoUtil(orgao);
	}

	/**
	 * Metodo que atualiza o banco de orgaos ao adicionar o novo orgao
	 * 
	 * @param orgao
	 *            Orgao a ser adicionado
	 */
	private void adicionaOrgaoUtil(Orgao orgao) {
		if (!bancoDeOrgaos.containsKey(orgao)) {
			bancoDeOrgaos.put(orgao, 1);
		} else {
			bancoDeOrgaos.put(orgao, bancoDeOrgaos.get(orgao) + 1);
		}
	}

	/**
	 * Metodo que remove um orgao do banco de orgaos
	 * 
	 * @param nome
	 *            Nome do orgao a ser removido
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao a ser removido
	 * @throws Exception
	 *             Caso o nome ou o tipo sanguineo estejam vazios ou nao haja
	 *             orgaos desse tipo no banco de orgaos
	 */
	public void removeOrgao(String nome, String tipoSanguineo) throws Exception {

		Orgao orgaoRemover = facOrgao.criaOrgao(nome, tipoSanguineo);

		if (bancoDeOrgaos.get(orgaoRemover) < 1) {
			throw new Exception("Nao ha mais orgaos desse tipo");
		}

		for (Orgao orgao : bancoDeOrgaos.keySet()) {
			if (orgaoRemover.equals(orgao)) {
				bancoDeOrgaos.put(orgao, bancoDeOrgaos.get(orgao) - 1);
			}
		}
	}

	/**
	 * Metodo que retorna o orgao que contenha os atributos especificados,
	 * retorna null caso nao exista tal orgao
	 * 
	 * @param nome
	 *            Nome do orgao
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao
	 * @return O orgao caso exista, null do contrario
	 * @throws Exception
	 *             Caso o nome ou o tipo sanguineo do orgap especificado estejam
	 *             vazios
	 */
	public Orgao getOrgao(String nome, String tipoSanguineo) throws Exception {

		Orgao orgaoAObter = facOrgao.criaOrgao(nome, tipoSanguineo);

		if (bancoDeOrgaos.containsKey(orgaoAObter)
				&& bancoDeOrgaos.get(orgaoAObter) >= 1) {
			return orgaoAObter;
		}

		return null;
	}

	/**
	 * Metodo que retorna a quantidade de orgaos com o nome especificado
	 * 
	 * @param nome
	 *            Nome do orgao
	 * @return Quantidade de orgaos com o nome especificado
	 */
	public int getQuantidade(String nome) {
		int quantidade = 0;

		for (Orgao orgao : bancoDeOrgaos.keySet()) {
			if (orgao.getNome().equals(nome)) {
				quantidade += bancoDeOrgaos.get(orgao);
			}

		}

		return quantidade;
	}

	/**
	 * Medoto que retorna a quantidade de orgaos totais no banco de orgaos
	 * 
	 * @return A quantidade total de orgaos no banco de orgaos
	 */
	public int getQuantidadeTotal() {
		int quantidade = 0;

		for (Orgao orgao : bancoDeOrgaos.keySet()) {
			quantidade += bancoDeOrgaos.get(orgao);
		}

		return quantidade;
	}

}
