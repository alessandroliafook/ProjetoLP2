package admin;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import exceptions.StringInvalida;
import pessoal.Funcionario;

public class ComiteGestor {

	private Funcionario diretorGeral;
	private FactoryDeFuncionario facFuncionario;
	private Set<Funcionario> corpoClinico;
	private Set<Funcionario> corpoProfissional;

	public ComiteGestor() throws Exception {

		this.facFuncionario = new FactoryDeFuncionario();
		this.corpoClinico = new TreeSet<Funcionario>();
		this.corpoProfissional = new TreeSet<Funcionario>();

	}
	
	public boolean liberaSistema() {
		
	}
	
	public boolean primeiroLogin() {
		
	}

}
