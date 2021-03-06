package factory;

import java.io.Serializable;

import departamentoMedico.Orgao;
import util.VerificaOrgao;

public class FactoryDeOrgao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8743151192410537231L;

	/**
	 * Metodo que cria um orgao com os parametros especificados
	 * 
	 * @param nome
	 *            Nome do orgao a ser criado
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao a ser criado
	 * @return Novo orgao com os os atributos correspondentes
	 * @throws Exception
	 *             Caso o nome ou o tipo sanguineo estejam vazios
	 */
	public Orgao criaOrgao(String nome, String tipoSanguineo) throws Exception {

		VerificaOrgao.validaNome(nome);
		VerificaOrgao.validaTipoSanguineo(tipoSanguineo);

		return new Orgao(nome, tipoSanguineo);

	}

}
