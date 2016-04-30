package pessoal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import util.*;

public abstract class Pessoa {

	private String nome;
	private LocalDate dataNascimento;
	private final DateTimeFormatter FORMATO_DE_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * 
	 * @param nome
	 *            - Nome da pessoa
	 * @param data
	 *            - Data de nascimento no formato "dd/mm/aaaa"
	 * @throws DateTimeParseException
	 *             - Caso a data nao esteja no formato especificado
	 * @throws StringInvalidaException
	 *             - Caso nome ou data sejam string vazias ou nulas
	 */
	public Pessoa(String nome, String data) throws Exception {

		Verificacao.validaString(data, "data de nascimento");
		Verificacao.validaString(nome, "nome da pessoa");

		setNome(nome);
		setData(data);
	}

	public String getNome() {
		return this.nome;
	}

	public LocalDate getData() {
		return this.dataNascimento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setData(String data) throws DateTimeParseException {
		this.dataNascimento = LocalDate.parse(data, FORMATO_DE_DATA);
	}
}
