package pessoal;

import java.time.LocalDate;

public abstract class Pessoa {

	private String nome;
	private LocalDate dataNascimento;
	
	public Pessoa(String nome, LocalDate dataNascimento){
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	//public abstract String getMatricula();
	
}
