package pessoal;

import util.*;

public class Funcionario extends Pessoa implements Comparable<Funcionario> {

	private String matricula;
	private String senha;
	
	/**
	 * 
	 * @param nome - Nome do funcionario
	 * @param dataNascimento - Data de nascimento no formato "dd-mm-aaaa"
	 * @param matricula - Matricula gerada para o funcionario
	 * @param senha - Senha gerada para o funcionario
	 * @throws DateTimeParseException - Caso a data nao esteja no formato especificado
	 * @throws StringInvalidaException - Caso quaisquer dos parametros sejam string vazias ou nulas
	 */
	public Funcionario(String nome, String dataNascimento,
			String matricula, String senha) throws Exception{
		
		super(nome, dataNascimento);
		Verificacao.validaString(matricula, "matricula do funcionario");
		Verificacao.validaString(senha, "senha do funcionario");
		setMatricula(matricula);
		setSenha(senha);
		
	}

	
	public String getMatricula(){
		return this.matricula;
	}
	
	public String getSenha(){
		return this.senha;
	}
	
	private void setMatricula(String matricula){
		this.matricula = matricula;
	}
	
	private void setSenha(String senha){
		this.senha = senha;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/**
	 * Dois funcionarios sao iguais caso tenham a mesma matricula
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Funcionario){
			Funcionario outro = (Funcionario) obj;
			return this.matricula.equals(outro.getMatricula());
		}
		return false;
	}

	/**
	 * Funcionarios sao comparados pelo nome
	 */
	@Override
	public int compareTo(Funcionario outro) {
		return super.getNome().compareTo(outro.getNome());
	}
	
}
