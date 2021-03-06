package departamentoMedico;

import java.io.Serializable;
import java.util.ArrayList;
import exceptions.BancoDeOrgaosException;
import exceptions.RemoveOrgaoException;
import factory.FactoryDeOrgao;
import util.VerificaOrgao;

public class BancoDeOrgaos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1251791363335311254L;
	
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
	 * @throws BancoDeOrgaosException
	 *             Caso o nome ou o tipo sanguineo do orgao sejam vazios
	 */
	protected void cadastraOrgao(String nome, String tipoSanguineo) throws BancoDeOrgaosException {

		try {
			Orgao orgao = facOrgao.criaOrgao(nome, tipoSanguineo);
			bancoDeOrgaos.add(orgao);
		} catch (Exception e) {
			throw new BancoDeOrgaosException(e.getMessage());
		}
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
	protected String buscaOrgPorSangue(String tipoSanguineo) throws BancoDeOrgaosException {

		String saida = "";

		try {

			VerificaOrgao.validaTipoSanguineo(tipoSanguineo);

			for (Orgao orgao : bancoDeOrgaos) {
				if (orgao.getTipoSanguineo().equals(tipoSanguineo)) {
					if (saida.equals("")) {
						saida = orgao.getNome();
					} else if (!saida.contains(orgao.getNome())) {
						saida += "," + orgao.getNome();
					}
				}
			}

		} catch (Exception e) {
			throw new BancoDeOrgaosException(e.getMessage());
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
	protected String buscaOrgPorNome(String nomeOrgao) throws BancoDeOrgaosException {

		String saida = "";

		try {

			VerificaOrgao.validaNome(nomeOrgao);

			for (Orgao orgao : bancoDeOrgaos) {
				if (orgao.getNome().equals(nomeOrgao)) {
					if (saida.equals("")) {
						saida = orgao.getTipoSanguineo();
					} else {
						saida += "," + orgao.getTipoSanguineo();
					}
				}
			}

		} catch (Exception e) {
			throw new BancoDeOrgaosException(e.getMessage());
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
	protected boolean buscaOrgao(String nomeOrgao, String tipoSanguineo) throws BancoDeOrgaosException {

		try {
			VerificaOrgao.validaNome(nomeOrgao);
			VerificaOrgao.validaTipoSanguineo(tipoSanguineo);
			Orgao orgaoPesquisado = facOrgao.criaOrgao(nomeOrgao, tipoSanguineo);

			return bancoDeOrgaos.contains(orgaoPesquisado);

		} catch (Exception e) {
			throw new BancoDeOrgaosException(e.getMessage());
		}

	}

	/**
	 * Metodo que remove um orgao do banco de orgaos
	 * 
	 * @param nome
	 *            Nome do orgao a ser removido
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao a ser removido
	 * @throws RemoveOrgaoException
	 *             Caso o nome ou o tipo sanguineo estejam vazios ou nao haja
	 *             orgaos desse tipo no banco de orgaos
	 */
	protected void retiraOrgao(String nome, String tipoSanguineo) throws RemoveOrgaoException {
		
		removeOrgaoUtil(nome, tipoSanguineo);
		
		try {
			Orgao orgaoRemover = facOrgao.criaOrgao(nome, tipoSanguineo);
			bancoDeOrgaos.remove(orgaoRemover);
		} catch(Exception e) {
			throw new RemoveOrgaoException(e.getMessage());
		}
		
	}

	private void removeOrgaoUtil(String nome, String tipoSanguineo) throws RemoveOrgaoException {
		boolean temOrgao = false; 
		
		try {
			temOrgao = buscaOrgao(nome, tipoSanguineo);
		} catch(BancoDeOrgaosException e) {
			throw new RemoveOrgaoException(e.getMessage().replace("O banco de orgaos apresentou um erro. ", ""));
		}
		
		if(!temOrgao) {
			throw new RemoveOrgaoException("Orgao nao cadastrado.");
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
	protected int qtdOrgaos(String nome) throws BancoDeOrgaosException {
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
	protected int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.size();
	}

}
