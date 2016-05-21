package pessoal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import exceptions.DataInvalidaException;
import util.*;

public abstract class Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4892186631163406594L;
	
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
	 * Retorna a idade da pessoa utilizando a classe ChronoUnit
	 * 
	 * @return A idade da Pessoa
	 */
	public int getIdade() {
		int diferenca = (int) ChronoUnit.YEARS.between(this.dataNascimento, LocalDate.now());
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
