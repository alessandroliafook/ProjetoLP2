package pessoal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import exceptions.DataInvalidaException;
import util.*;

public abstract class Pessoa {

	private String nome;
	private LocalDate dataNascimento;
	private final DateTimeFormatter FORMATO_DE_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
	public Pessoa(String nome, String data) throws Exception {
		setNome(nome);
		setData(data);
	}

	/**
	 * 
	 * @return A idade da Pessoa
	 */
	public int getIdade() {
		int diferenca = (int)ChronoUnit.YEARS.between(LocalDate.now(), this.dataNascimento);
		return diferenca;
	}

	public String getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @return Uma String com data de nascimento da pessoa no formato "aaaa-mm-dd"
	 */
	public String getData() {
		return this.dataNascimento.toString();
	}

	public void setNome(String nome) throws Exception {
		boolean isPaciente = (this instanceof Paciente);
		VerificaPessoa.validaNome(nome, isPaciente);
		this.nome = nome;
	}

	public void setData(String data) throws DataInvalidaException {
		VerificaPessoa.validaData(data);
		this.dataNascimento = LocalDate.parse(data, FORMATO_DE_DATA);
	}
}
