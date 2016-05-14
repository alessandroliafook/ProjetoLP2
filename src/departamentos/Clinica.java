package departamentos;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import clinica.BancoDeOrgaos;
import exceptions.BancoDeOrgaosException;
import exceptions.CadastroFuncionarioException;
import exceptions.CadastroPacienteException;
import exceptions.ConsultaMedicamentoException;
import exceptions.ConsultaProntuarioException;
import exceptions.RemoveOrgaoException;
import factory.FactoryDePessoa;
import pessoal.Paciente;
import prontuario.Prontuario;
import util.VerificaPessoa;

public class Clinica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1274054963107375989L;
	private Set<Prontuario> prontuarios;
	private FactoryDePessoa pacienteFactory;
	private BancoDeOrgaos bancoDeOrgaos;

	public Clinica() {
		this.prontuarios = new TreeSet<Prontuario>();
		pacienteFactory = new FactoryDePessoa();
		bancoDeOrgaos = new BancoDeOrgaos();
	}

	/**
	 * Metodo que tenta cadastrar um Paciente no sistema
	 * 
	 * @param nome
	 *            Nome do paciente a ser cadastrado
	 * @param data
	 *            Data de nascimento do paciente
	 * @param peso
	 *            Peso do paciente
	 * @param sexo
	 *            Sexo do paciente
	 * @param genero
	 *            Genero do paciente
	 * @param tipoSanguineo
	 *            Tipo sanguineo do paciente(A/B/O/AB seguido de + ou -) Ex: AB-
	 * @return O id do paciente cadastrado
	 * @throws CadastroPacienteException
	 *             Caso o cadastro nao seja bem sucedido
	 */
	public int cadastraPaciente(String nome, String data, double peso, String sexo, String genero, String tipoSanguineo)
			throws CadastroPacienteException {

		try {
			Paciente novoPaciente = pacienteFactory.criaPaciente(nome, data, peso, tipoSanguineo, sexo, genero,
					getNumeroCadastros() + 1);
			Prontuario novoProntuario = new Prontuario(novoPaciente);

			if (existeProntuario(novoProntuario)) {
				throw new Exception("Paciente ja cadastrado.");
			}

			prontuarios.add(novoProntuario);
			return novoPaciente.getID();

		} catch (Exception e) {
			throw new CadastroPacienteException(e.getMessage());
		}

	}

	/**
	 * Retorna o numero de cadastros ja realizados
	 * 
	 * @return O numero de pacientes ja cadastradosbuscaPaciente
	 */
	public int getNumeroCadastros() {
		return prontuarios.size();
	}

	/**
	 * Verifica se o prontuario fornecido ja existe no sistema
	 * 
	 * @param prontuario
	 *            Objeto Prontuario a ser verificado
	 * @return True caso o objeto ja exista no sistema, False caso contrario
	 */
	private boolean existeProntuario(Prontuario prontuario) {
		return prontuarios.contains(prontuario);
	}

	/**
	 * Retorna a informacao solicitada do paciente especificado
	 * 
	 * @param paciente
	 *            Objeto Paciente do qual sera retirada a informacao solicitada
	 * @param atributo
	 *            Descricao da informacao
	 *            solicitada(Nome/Data/Sexo/Genero/TipoSanguineo/Peso/Idade
	 * @return Uma String com a informacao solicitada
	 */
	public String getInfoPaciente(int id, String atributo) {
		String retorno = "";
		Paciente paciente = buscaPaciente(id);

		switch (atributo) {
		case "Nome":
			retorno = paciente.getNome();
			break;
		case "Data":
			retorno = paciente.getData();
			break;
		case "Sexo":
			retorno = paciente.getSexo();
			break;
		case "Genero":
			retorno = paciente.getGenero();
			break;
		case "TipoSanguineo":
			retorno = paciente.getTipoSanguineo();
			break;
		case "Peso":
			retorno = String.valueOf(paciente.getPeso());
			break;
		case "Idade":
			retorno = String.valueOf(paciente.getIdade());
		default:
			break;
		}

		return retorno;
	}

	/**
	 * Busca um paciente no sistema atraves do seu ID
	 * 
	 * @param id
	 *            ID do paciente a ser buscado
	 * @return Objeto do tipo Paciente com o ID especificado
	 */
	private Paciente buscaPaciente(int id) {
		for (Prontuario prontuario : this.prontuarios) {
			if (prontuario.getPaciente().getID() == id) {
				return prontuario.getPaciente();
			}
		}
		return null;
	}

	/**
	 * Busca um paciente no sistema atraves do seu nome
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @return O ID do primeiro paciente com o nome especificado
	 * @throws Exception
	 *             Lanca excecao acaso o nome seja vazio
	 */
	public String getPacienteID(String nome) throws Exception {

		VerificaPessoa.validaNome(nome, true);

		for (Prontuario prontuario : this.prontuarios) {
			if (prontuario.getNomePaciente().equalsIgnoreCase(nome)) {
				return prontuario.getPacienteID();
			}
		}
		throw new Exception("Paciente nao cadastrado no sistema.");
	}

	/**
	 * Busca um prontuario no sistema atraves do ID do paciente associado
	 * 
	 * @param id
	 *            ID do paciente a ser buscado
	 * @return Objeto do tipo Paciente com o ID especificado
	 * @throws Exception
	 */
	private Prontuario buscaProntuario(int id) throws Exception {
		for (Prontuario prontuario : this.prontuarios) {
			if (prontuario.getPaciente().getID() == id) {
				return prontuario;
			}
		}
		throw new Exception("Paciente nao cadastrado.");
	}

	/**
	 * Obtem as informacoes do prontuario de um paciente cadastrado no sistema
	 * 
	 * @param posicao
	 *            A posicao em que o prontuario esta armazenado no sistema
	 * @return Id do Paciente retirado do Prontuario na posicao especificada
	 * @throws ConsultaProntuarioException
	 *             Caso a posicao seja invalida
	 */
	public int getProntuario(int posicao) throws ConsultaProntuarioException {
		if (posicao < 0) {
			throw new ConsultaProntuarioException("Indice do prontuario nao pode ser negativo.");
		} else if (posicao >= prontuarios.size()) {
			throw new ConsultaProntuarioException(
					"Nao ha prontuarios suficientes (max = " + getNumeroCadastros() + ").");
		}

		Prontuario prontuarioSolicitado = (Prontuario) prontuarios.toArray()[posicao];

		return prontuarioSolicitado.getPaciente().getID();
	}

	/**
	 * Metodo que registra um procedimento medito no prontuario do paciente.
	 * 
	 * @param nomeDoPaciente
	 *            Nome do pacimente titular do prontuario onde sera registrado o
	 *            procedimento.
	 * @param nomeDoProcedimento
	 *            Nome do procedimento a ser registrado
	 * @param listaDeMedicamentos
	 *            Lista com nomes dos medicamentos necessarios ao procedimento
	 * @return O nome do procedimento registrado com sucesso.
	 * @throws Exception
	 * @throws CadastroFuncionarioException
	 * @throws ConsultaMedicamentoException
	 */
	public void realizaProcedimento(int idDoPaciente, String nomeDoProcedimento, double gastosComMedimentos)
			throws Exception {

		Prontuario prontuario = this.buscaProntuario(idDoPaciente);

		prontuario.realizaProcedimento(nomeDoProcedimento, gastosComMedimentos);
	}

	// METODOS DELEGADOS AO BANCO DE ORGAOS

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
		bancoDeOrgaos.cadastraOrgao(nome, tipoSanguineo);
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
		return bancoDeOrgaos.buscaOrgPorSangue(tipoSanguineo);
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
		return bancoDeOrgaos.buscaOrgPorNome(nomeOrgao);
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
		return bancoDeOrgaos.buscaOrgao(nomeOrgao, tipoSanguineo);
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
		bancoDeOrgaos.retiraOrgao(nome, tipoSanguineo);
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
		return bancoDeOrgaos.qtdOrgaos(nome);
	}

	/**
	 * Medoto que retorna a quantidade de orgaos totais no banco de orgaos
	 * 
	 * @return A quantidade total de orgaos no banco de orgaos
	 */
	public int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.totalOrgaosDisponiveis();
	}

}
