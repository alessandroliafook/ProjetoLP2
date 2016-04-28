package pessoal;

import java.time.LocalDate;

public class Funcionario extends Pessoa {

	private String matricula;
	
	public Funcionario(String nome, LocalDate dataNascimento, String matricula) throws Exception{
		
		super(nome, dataNascimento);
		setMatricula(matricula);
	
	}
	

	public String getMatricula(){
		return this.matricula;
	}
	
	private void setMatricula(String matricula){
		this.matricula = matricula;
	}
	
	
}
