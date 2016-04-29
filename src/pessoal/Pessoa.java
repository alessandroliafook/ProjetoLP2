package pessoal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import util.*;

public abstract class Pessoa {

	private String nome;
	private LocalDate dataNascimento;
	private final DateTimeFormatter FORMATO_DE_DATA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	/**
	 * 
	 * @param nome - Nome da pessoa
	 * @param data - Data de nascimento no formato "dd-mm-aaaa"
	 * @throws DateTimeParseException - Caso a data nao esteja no formato especificado
	 * @throws StringInvalidaException - Caso nome ou data sejam string vazias ou nulas
	 */
	public Pessoa(String nome, String data) throws Exception{
		
		Verificacao.validaString(data, "data de nascimento");
		Verificacao.validaString(nome, "nome da pessoa");
		
		setData(LocalDate.parse(data, FORMATO_DE_DATA));
		setNome(nome);
		setData(dataNascimento);
	}

	public String getNome(){
		return this.nome;
	}
	
	public LocalDate getData(){
		return this.dataNascimento;
	}
	
	private void setNome(String nome){
		this.nome = nome;
	}
	
	private void setData(LocalDate dataNascimento){
		this.dataNascimento = dataNascimento;
	}	
}
