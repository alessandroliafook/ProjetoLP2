package clinica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exceptions.BancoDeOrgaosException;
import exceptions.RemoveOrgaoException;
import factory.FactoryDeOrgao;
import util.VerificaOrgao;

public class BancoDeOrgaos {

	private FactoryDeOrgao facOrgao;
	private ArrayList<Orgao> bancoDeOrgaos;

	public BancoDeOrgaos() {
		bancoDeOrgaos = new ArrayList<Orgao>();
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
	public void cadastraOrgao(String nome, String tipoSanguineo) throws BancoDeOrgaosException {
		Orgao orgao = facOrgao.criaOrgao(nome, tipoSanguineo);
		bancoDeOrgaos.add(orgao);
	}

	/**
	 * Metodo que retorna uma String contendo todos os orgaos compativeis com o
	 * tipo sanguineo especificado
	 * 
	 * @param tipoSanguineo
	 *            Tipo sanguineo a ser pesquisado
	 * @return Uma String contendo todos os orgaos compativeis com o tipo
	 *         sanguineo especificado
	 * @throws BancoDeOrgaosException
	 *             Caso o tipo sanguineo seja invalido
	 */
	public String buscaOrgPorSangue(String tipoSanguineo) throws BancoDeOrgaosException {
		VerificaOrgao.validaTipoSanguineo(tipoSanguineo);

		String saida = "";
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				if (saida.equals("")) {
					saida = orgao.getNome();
				} else {
					saida += "," + orgao.getNome();
				}
			}
		}

		if (saida.equals("")) {
			throw new BancoDeOrgaosException("Nao ha orgaos cadastrados para esse tipo sanguineo.");
		}

		return saida;
	}

	/**
	 * Metodo que retorna uma String contendo os tipos sanguineos compativeis
	 * com o o orgao especificado
	 * 
	 * @param nomeOrgao
	 *            Orgao a ser pesquisado
	 * @return Uma String contendo os tipos sanguineos compativeis com o o orgao
	 *         especificado
	 * @throws BancoDeOrgaosException
	 *             Caso o tipo o nome seja invalido ou nao haja orgaos
	 *             cadastrados com o nome especificados
	 */
	public String buscaOrgPorNome(String nomeOrgao) throws BancoDeOrgaosException {
		VerificaOrgao.validaNome(nomeOrgao);

		String saida = "";
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nomeOrgao)) {
				if (saida.equals("")) {
					saida = orgao.getTipoSanguineo();
				} else {
					saida += "," + orgao.getTipoSanguineo();
				}
			}
		}

		if (saida.equals("")) {
			throw new BancoDeOrgaosException("Orgao nao cadastrado.");
		}

		return saida;
	}

	/**
	 * Metodo que procura saber se ha um orgao compativel com tal tipo sanguineo
	 * 
	 * @param nomeOrgao
	 *            Nome do orgao a ser pesquisado
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao a ser pesquisado
	 * @return True caso haja
	 * @throws BancoDeOrgaosException
	 *             Caso o nome do orgao esteja invalido ou o tipo sanguineo
	 */
	public boolean buscaOrgao(String nomeOrgao, String tipoSanguineo) throws BancoDeOrgaosException {
		VerificaOrgao.validaNome(nomeOrgao);
		VerificaOrgao.validaTipoSanguineo(tipoSanguineo);

		Orgao orgaoPesquisado = facOrgao.criaOrgao(nomeOrgao, tipoSanguineo);

		return bancoDeOrgaos.contains(orgaoPesquisado);
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
	public void retiraOrgao(String nome, String tipoSanguineo) throws RemoveOrgaoException {

		try {

			if (!buscaOrgao(nome, tipoSanguineo)) {
				throw new RemoveOrgaoException("Orgao nao cadastrado.");
			}

			Orgao orgaoRemover = facOrgao.criaOrgao(nome, tipoSanguineo);

			for (Orgao orgao : bancoDeOrgaos) {
				if (orgao.equals(orgaoRemover)) {
					bancoDeOrgaos.remove(orgaoRemover);
				}
			}

		} catch (BancoDeOrgaosException e) {
			throw new RemoveOrgaoException(e.getMessage());
		}

	}

	/**
	 * Metodo que retorna a quantidade de orgaos com o nome especificado
	 * 
	 * @param nome
	 *            Nome do orgao
	 * @return Quantidade de orgaos com o nome especificado
	 * @throws BancoDeOrgaosException
	 *             Caso nao exista algum orgao com o nome especificado
	 */
	public int qtdOrgaos(String nome) throws BancoDeOrgaosException {
		int quantidade = 0;
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				quantidade += 1;
			}
		}

		if (quantidade == 0) {
			throw new BancoDeOrgaosException("Orgao nao cadastrado.");
		}

		return quantidade;
	}
	
	/**
	 * Medoto que retorna a quantidade de orgaos totais no banco de orgaos
	 * 
	 * @return A quantidade total de orgaos no banco de orgaos
	 */
	public int getQuantidadeTotal() {
		return bancoDeOrgaos.size();
	}

//	/**
//	 * Metodo que retorna o orgao que contenha os atributos especificados,
//	 * retorna null caso nao exista tal orgao
//	 * 
//	 * @param nome
//	 *            Nome do orgao
//	 * @param tipoSanguineo
//	 *            Tipo sanguineo do orgao
//	 * @return O orgao caso exista, null do contrario
//	 * @throws Exception
//	 *             Caso o nome ou o tipo sanguineo do orgap especificado estejam
//	 *             vazios
//	 */
//	public Orgao getOrgao(String nome, String tipoSanguineo) throws Exception {
//
//		Orgao orgaoAObter = facOrgao.criaOrgao(nome, tipoSanguineo);
//
//		if (bancoDeOrgaos.containsKey(orgaoAObter) && bancoDeOrgaos.get(orgaoAObter) >= 1) {
//			return orgaoAObter;
//		}
//
//		return null;
//	}

	

}
