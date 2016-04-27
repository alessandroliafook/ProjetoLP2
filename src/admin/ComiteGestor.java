package admin;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import exceptions.StringInvalida;
import factory.FactoryDePessoa;
import pessoal.Funcionario;
import pessoal.Pessoa;

public class ComiteGestor {

	private Funcionario diretorGeral;
	private FactoryDePessoa rh;
	private Set<Funcionario> corpoClinico;
	private Set<Funcionario> corpoProfissional;
	
	public ComiteGestor() throws Exception {
		
		this.rh = new FactoryDePessoa();
		this.diretorGeral = rh.criaFuncionario("diretor geral");
		this.corpoClinico = new TreeSet<Funcionario>();
		this.corpoProfissional = new TreeSet<Funcionario>();
		
	}

	
	public Pessoa buscaUsuario(String matricula) throws Exception {
		
		if(diretorGeral.getMatricula().equals(matricula)){
			return diretorGeral;
		}
		
		for(Pessoa funcionario : corpoClinico){

			if(funcionario.getMatricula().equals(matricula)){
				return funcionario;
			}
			
		}

		for(Pessoa funcionario : corpoProfissional){

			if(funcionario.getMatricula().equals(matricula)){
				return funcionario;
			}
			
		}
		
		throw new StringInvalida();
		
	}
	
}
