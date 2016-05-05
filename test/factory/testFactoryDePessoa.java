package factory;

import static org.junit.Assert.*;

import org.junit.Test;

import pessoal.Funcionario;
import pessoal.Paciente;

public class testFactoryDePessoa {

	@Test
	public void testCriaFuncionario() {
		/*Teste para criacao correta de funcionarios*/
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Funcionario funcionario = factory.criaFuncionario("carlos", "13/10/1993", "Medico", 1);

			assertEquals("1993-10-13", funcionario.getData());
			/*
			 * Padrao de matricula = um digito indicando o cargo + ano de
			 * realizacao da matricula + 3 digos com o numero de cadastros ja
			 * realizados
			 */
			assertEquals("22016001", funcionario.getMatricula());
			/*
			 * Padrao de senha = Ano de nascimento do funcionario + 4 primeiros
			 * digitos da matricula
			 */
			assertEquals("19932201", funcionario.getSenha());

			Funcionario funcionario2 = factory.criaFuncionario("pedrao", "12/11/1951", "Tecnico Administrativo", 144);
			
			assertEquals("1951-11-12", funcionario2.getData());
			assertEquals("32016144", funcionario2.getMatricula());
			assertEquals("19513201", funcionario2.getSenha());
		
		} catch (Exception e) {
			fail();
		}
		
		/*Testes de lancamento de excecoes*/
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Funcionario funcionario = factory.criaFuncionario(" ", "12/11/1951", "Tecnico Administrativo", 144);
			fail();
		} catch (Exception e) {
			assertEquals("Nome do funcionario nao pode ser vazio.", e.getMessage());
		}
		
		
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Funcionario funcionario = factory.criaFuncionario("bill", "12/20/1951", "Tecnico Administrativo", 144);
			fail();
		} catch (Exception e) {
			assertEquals("Data invalida.", e.getMessage());
		}
		
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Funcionario funcionario = factory.criaFuncionario("bill", "12/20/1951", "bartender", 144);
			fail();
		} catch (Exception e) {
			assertEquals("Cargo invalido.", e.getMessage());
		}
	}
	
	@Test
	public void testCriaPaciente(){
		/*Teste para criacao correta de pacientes*/
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Paciente paciente = factory.criaPaciente("carla", "15/11/1998", 42, "A+", "feminino", "feminino", 2);
			assertEquals("carla", paciente.getNome());
			assertEquals("1998-11-15", paciente.getData());
		} catch (Exception e) {
			fail();
		}
		
		/*Testes de lancamento de excecoes*/
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Paciente paciente = factory.criaPaciente(" ", "15/11/1998", 42, "A+", "feminino", "feminino", 2);
			fail();
		} catch (Exception e) {
			assertEquals("Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Paciente paciente = factory.criaPaciente("carla", "15/112/1998", 42, "A+", "feminino", "feminino", 2);
			fail();
		} catch (Exception e) {
			assertEquals("Data invalida.", e.getMessage());
		}
		
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Paciente paciente = factory.criaPaciente("carla", "15/12/1998", -1, "A+", "feminino", "feminino", 2);
			fail();
		} catch (Exception e) {
			assertEquals("Peso do paciente nao pode ser negativo.", e.getMessage());
		}
		
		try {
			FactoryDePessoa factory = new FactoryDePessoa();
			Paciente paciente = factory.criaPaciente("carla", "15/12/1998",45 , "X-", "feminino", "feminino", 2);
			fail();
		} catch (Exception e) {
			assertEquals("Tipo sanguineo invalido.", e.getMessage());
		}
	}

}
