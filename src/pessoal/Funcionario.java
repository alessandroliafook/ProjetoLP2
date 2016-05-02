package pessoal;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Comparable<Funcionario>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2456859660892263148L;
	private String matricula;
	private String senha;

	/**
	 * 
	 * @param nome
	 *            Nome do funcionario
	 * @param dataNascimento
	 *            Data de nascimento no formato "dd/mm/aaaa"
	 * @param matricula
	 *            Matricula gerada para o funcionario
	 * @param senha
	 *            Senha gerada para o funcionario
	 * @throws DataInvalidaException
	 *            Caso a data nao esteja no formato especificado
	 * @throws NomeFuncionarioVazioException
	 *            Caso nome seja vazio
	 */
	public Funcionario(String nome, String dataNascimento, String matricula, String senha) throws Exception {

		super(nome, dataNascimento);
		setMatricula(matricula);
		setSenha(senha);

	}

	public String getMatricula() {
		return this.matricula;
	}

	public String getSenha() {
		return this.senha;
	}
	
	/**
	 * Verifica o cargo do funcionario com base na matricula
	 * @return Uma String cpm o cargo do Funcionario
	 */
	public String getCargo(){
		if(matricula.charAt(0) == '1') return "Diretor Geral";
		else if(matricula.charAt(0) == '2') return "Medico";
		else return "Tecnico Administrativo";
			
	}

	private void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setSenha(String senha) {
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
		if (obj instanceof Funcionario) {
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
