package pessoal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import exceptions.DataInvalidaException;
import exceptions.NomeFuncionarioVazioException;
import exceptions.NomePacienteVazioException;
import util.*;

public abstract class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4892186631163406594L;

	private String nome;
	private String dataNascimento;

	/**
	 * 
	 * @param nome
	 *            Nome da pessoa
	 * @param data
	 *            Data de nascimento no formato "dd/mm/aaaa"
	 * @throws DataInvalidaException
	 *             Caso a data nao esteja no formato especificado
	 * @throws NomeFuncionarioVazioException
	 *             Caso a subclasse do objeto seja Funcionario e nome seja vazio
	 * @throws NomePacienteVazioException
	 *             Caso a subclasse do objeto seja Paciente e nome seja vazio
	 */
	public Pessoa(String nome, String data)
			throws NomePacienteVazioException, NomeFuncionarioVazioException, DataInvalidaException {
		setNome(nome);
		setData(data);
	}

	/**
	 * Retorna a idade da pessoa utilizando a classe ChronoUnit
	 * 
	 * @return A idade da Pessoa
	 */
	public int getIdade() {
		LocalDate data = LocalDate.parse(this.dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		int diferenca = (int) ChronoUnit.YEARS.between(data, LocalDate.now());
		return diferenca;
	}

	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna a data de nascimento no formato "aaaa-mm-dd"
	 * 
	 * @return Uma String com data de nascimento da pessoa
	 */
	public String getData() {
		LocalDate data = LocalDate.parse(this.dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return data.toString();
	}

	public void setNome(String nome) throws NomePacienteVazioException, NomeFuncionarioVazioException {
		boolean isPaciente = (this instanceof Paciente);
		VerificaPessoa.validaNome(nome, isPaciente);
		this.nome = nome;
	}

	public void setData(String data) throws DataInvalidaException {
		VerificaPessoa.validaData(data);
		this.dataNascimento = data;
	}
}
