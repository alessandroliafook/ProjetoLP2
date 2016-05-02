package departamentos;

import java.util.Set;
import java.util.TreeSet;

import exceptions.CadastroPacienteException;
import exceptions.ConsultaProntuarioException;
import factory.FactoryDePessoa;
import pessoal.Paciente;
import prontuario.Prontuario;

public class Clinica {

	private Set<Prontuario> prontuarios;
	private FactoryDePessoa pacienteFactory;

	public Clinica() {
		this.prontuarios = new TreeSet<Prontuario>();
		pacienteFactory = new FactoryDePessoa();
	}

	/**
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
	 * @return Objeto Paciente referente ao paciente cadastrado
	 * @throws CadastroPacienteException
	 *             Caso o cadastro nao seja bem sucedido
	 */
	public Paciente cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) throws CadastroPacienteException {

		try {
			Paciente novoPaciente = pacienteFactory.criaPaciente(nome, data, peso, tipoSanguineo, sexo, genero,
					getNumeroCadastros() + 1);
			Prontuario novoProntuario = new Prontuario(novoPaciente);

			if (existeProntuario(novoProntuario)) {
				throw new Exception("Paciente ja cadastrado.");
			}

			prontuarios.add(novoProntuario);
			return novoPaciente;

		} catch (Exception e) {
			throw new CadastroPacienteException(e.getMessage());
		}

	}

	/**
	 * 
	 * @return O numero de pacientes ja cadastrados
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
	public boolean existeProntuario(Prontuario prontuario) {
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
	public String getInfoPaciente(Paciente paciente, String atributo) {
		String retorno = "";

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
	 * Obtem as informacoes do prontuario de um paciente cadastrado no sistema
	 * 
	 * @param posicao
	 *            A posicao em que o prontuario esta armazenado no sistema
	 * @return Objeto Paciente retirado do Prontuario na posicao especificada
	 * @throws ConsultaProntuarioException
	 *             Caso a posicao seja invalida
	 */
	public Paciente getProntuario(int posicao) throws ConsultaProntuarioException {
		if (posicao < 0) {
			throw new ConsultaProntuarioException("Indice do prontuario nao pode ser negativo.");
		} else if (posicao >= prontuarios.size()) {
			throw new ConsultaProntuarioException(
					"Nao ha prontuarios suficientes (max = " + getNumeroCadastros() + ").");
		}

		Prontuario prontuarioSolicitado = (Prontuario) prontuarios.toArray()[posicao];

		return prontuarioSolicitado.getPaciente();
	}

}
