package factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import pessoal.Funcionario;
import pessoal.Paciente;
import pessoal.Pessoa;

public class FactoryDePessoa {

	private final DateTimeFormatter FORMATO_DE_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * 
	 * @param nome
	 *            - Nome do funcionario a ser criado
	 * @param dataNascimento
	 *            - Data de nascimento no formato "dd/mm/aaaa"
	 * @param cargo
	 *            - Cargo do funcionarios
	 * @param quantidadeMatriculas
	 *            - Numero de matriculas ja realizadas + 1
	 * @return - Um objeto Funcionario com os atributos especificados
	 * @throws DateTimeParseException
	 *             - Caso a data nao esteja no formato especificado
	 * @throws StringInvalidaException
	 *             - Caso quaisquer dos parametros sejam string vazias ou nulas
	 */
	public Pessoa criaFuncionario(String nome, String dataNascimento, String cargo, int quantidadeMatriculas)
			throws Exception {

		String matricula = geraMatricula(cargo, quantidadeMatriculas);
		String senha = geraSenha(dataNascimento, matricula);

		return new Funcionario(nome, dataNascimento, matricula, senha);
	}

	/**
	 * 
	 * @param nome
	 *            - Nome do paciente
	 * @param dataNascimento
	 *            - Data de nascimento no formato "dd/mm/aaaa"
	 * @param peso
	 *            - Peso do paciente
	 * @param tipoSanguineo
	 *            - Tipo sanguineo do paciente
	 * @param sexo
	 *            - Sexo do paciente(M/F)
	 * @param genero
	 *            - Genero do paciente
	 * @param numeroCadastros
	 *            - Numero de pacientes ja cadastrados + 1
	 * @throws DateTimeParseException
	 *             - Caso a data nao esteja no formato especificado
	 * @throws StringInvalidaException
	 *             - Caso quaisquer string fornecida seja vazia ou nula
	 * @throws NumeroInvalidoException
	 *             - Caso o peso do paciente seja negativo
	 * @throws DateTimeParseException
	 *             - Caso a data nao esteja no formato especificado
	 */
	public Pessoa criaPaciente(String nome, String dataNascimento, double peso, String tipoSanguineo, String sexo,
			String genero, int numeroCadastros) throws Exception {

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

		switch (cargo.toLowerCase()) {
		case "diretor":
			novaMatricula += "1";
			break;

		case "medico":
			novaMatricula += "2";
			break;

		case "tecnico admin":
			novaMatricula += "3";
			break;

		default:
			break;
		}

		novaMatricula += LocalDate.now().getYear();
		novaMatricula += quantidadeMatriculas;

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
