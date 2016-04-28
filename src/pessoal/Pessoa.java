package pessoal;

import java.time.LocalDate;

public abstract class Pessoa implements Comparable<Pessoa>{

	private String nome;
	private LocalDate dataNascimento;
	
	public Pessoa(String nome, LocalDate dataNascimento){
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

	@Override
	public int compareTo(Pessoa outraPessoa) {
		return this.nome.compareTo(outraPessoa.getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pessoa){
			Pessoa outro = (Paciente) obj;
			return this.nome.equals(outro.getNome());
		}
		return false;
	}
	
	
}
