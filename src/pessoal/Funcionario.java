package pessoal;

import java.time.LocalDate;

import cargos.CargoIF;
import factory.FactoryDeCargo;

public class Funcionario extends Pessoa {

	private String matricula;
	private CargoIF cargo;
	private FactoryDeCargo factoryDeCargo; 
	
	
	public Funcionario(String nome, LocalDate dataNascimento, String cargo) throws Exception{
		
		super(nome, dataNascimento);
		
		this.factoryDeCargo = new FactoryDeCargo();
		this.cargo = factoryDeCargo.escolheCargo(cargo);
	
	}
	

	public String getMatricula(){
		return this.matricula;
	}
	
	
}
