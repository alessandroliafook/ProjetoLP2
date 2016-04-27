package factory;

import java.time.LocalDate;

import pessoal.Funcionario;
import pessoal.Paciente;
import pessoal.Pessoa;

public class FactoryDePessoa {

	public Funcionario criaFuncionario(String nome, LocalDate dataNascimento, String cargo) throws Exception{
		return new Funcionario(nome, dataNascimento, cargo);
		
	}

	public Pessoa criaPaciente(){
		return new Paciente();
	}
	
}
