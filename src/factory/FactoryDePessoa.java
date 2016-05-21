package factory;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import pessoal.Funcionario;
import pessoal.Paciente;
import util.VerificaPessoa;

public class FactoryDePessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091067423897999143L;
	
	private final DateTimeFormatter FORMATO_DE_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Cria um objeto do tipo Funcionario
	 * 
	 * @param nome
	 *            Nome do funcionario a ser criado
	 * @param dataNascimento
	 *            Data de nascimento no formato "dd/mm/aaaa"
	 * @param cargo
	 *            Cargo do funcionario
	 * @param quantidadeMatriculas
	 *            Numero de matriculas ja realizadas + 1
	 * @return Um objeto Funcionario com os atributos especificados
	 * @throws DataInvalidaException
	 *             Caso a data nao esteja no formato especificado
	 * @throws NomeFuncionarioVazioException
	 *             Caso o nome fornecido seja vazio
	 * @throws CargoInvalidoException
	 *             Caso o cargo seja vazio ou invalido
	 */
	public Funcionario criaFuncionario(String nome, String dataNascimento, String cargo, int quantidadeMatriculas)
			throws Exception {

		VerificaPessoa.validaNome(nome, false);
		VerificaPessoa.validaCargo(cargo);
		VerificaPessoa.validaData(dataNascimento);

		String matricula = geraMatricula(cargo, quantidadeMatriculas);
		String senha = geraSenha(dataNascimento, matricula);

		return new Funcionario(nome, dataNascimento, matricula, senha);
	}

	/**
	 * Cria um objeto do tipo Paciente
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @param dataNascimento
	 *            Data de nascimento no formato "dd/mm/aaaa"
	 * @param peso
	 *            Peso do paciente
	 * @param tipoSanguineo
	 *            Tipo sanguineo do paciente
	 * @param sexo
	 *            Sexo do paciente
	 * @param genero
	 *            Genero do paciente
	 * @param numeroCadastros
	 *            Numero de pacientes ja cadastrados + 1
	 * @return Um objeto Paciente com os atributos especificados
	 * @throws DataInvalidaException
	 *             Caso a data nao esteja no formato especificado
	 * @throws NomePacienteVazioException
	 *             Caso o nome fornecido seja vazio
	 * @throws PesoInvalidoException
	 *             Caso o peso fornecido seja negativo
	 * @throws TipoSanguineoInvalidoException
	 *             Caso o tipo sanguineo informado seja vazio ou invalido
	 * 
	 */
	public Paciente criaPaciente(String nome, String dataNascimento, double peso, String tipoSanguineo, String sexo,
			String genero, int numeroCadastros) throws Exception {

		VerificaPessoa.validaNome(nome, true);
		VerificaPessoa.validaData(dataNascimento);
		VerificaPessoa.validaPeso(peso);
		VerificaPessoa.validaTipoSanguineo(tipoSanguineo);

		return new Paciente(nome, dataNascimento, peso, tipoSanguineo, sexo, genero, numeroCadastros);

	}

	/**
	 * Gera uma nova matricula de acordo com os dados do usuario
	 * 
	 * @param cargo
	 *            Cargo exercido pelo usuario
	 * @param quantidadeMatriculas
	 *            Quantidade de matriculas ja realizadas + 1
	 * @return Uma String com a nova matricula
	 */
	private String geraMatricula(String cargo, int quantidadeMatriculas) {
		String novaMatricula = "";
		cargo = cargo.toLowerCase();

		switch (cargo) {
		case "diretor geral":
			novaMatricula += "1";
			break;

		case "medico":
			novaMatricula += "2";
			break;

		case "tecnico administrativo":
			novaMatricula += "3";
			break;

		default:
			break;
		}

		novaMatricula += LocalDate.now().getYear();
		novaMatricula += String.format("%03d", quantidadeMatriculas);

		return novaMatricula;
	}

	/**
	 * Gera uma senha padrao para um novo usuario
	 * 
	 * @param dataString
	 *            Data de nascimento do usuario no forma "dd/mm/aaaa"
	 * @param matricula
	 *            Matricula do usuario
	 * @return A senha gerada
	 * @throws DateTimeParseException
	 *             Caso a data nao esteja no formato especificado
	 */
	private String geraSenha(String dataString, String matricula) throws DateTimeParseException {
		LocalDate dataNascimento = LocalDate.parse(dataString, FORMATO_DE_DATA);
		String senha = "";
		senha += dataNascimento.getYear();
		senha += matricula.substring(0, 4);
		return senha;
	}

}
