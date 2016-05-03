package hospital;

import admin.ComiteGestor;
import exceptions.AtualizaFuncionarioException;
import exceptions.ConsultaFuncionarioException;
import exceptions.ExclusaoFuncionarioException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.SistemaException;

public class HospitalFacade {

	ComiteGestor comite = ComiteGestor.getInstancia();

	public void iniciaSistema(){
		comite.iniciaSistema();
	}
	
	public String liberaSistema(String chave, String nome, String dataNascimento) throws Exception {
		return comite.liberaSistema(chave, nome, dataNascimento);
	}

	public String getInfoFuncionario(String matricula, String atributo) throws ConsultaFuncionarioException {
		return comite.getInfoFuncionario(matricula, atributo);
	}

	public void login(String matricula, String senha) throws LoginException {
		comite.login(matricula, senha);
	}

	public void logout() throws LogoutException {
		comite.logout();
	}

	public void cadastraFuncionario(String nome, String cargo, String dataNascimento) throws Exception {
		comite.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor)
			throws AtualizaFuncionarioException {
		comite.atualizaInfoFuncionario(matricula, atributo, novoValor);
	}

	public void atualizaInfoFuncionario(String atributo, String novoValor) throws AtualizaFuncionarioException {
		comite.atualizaInfoFuncionario(atributo, novoValor);
	}

	public void excluiFuncionario(String matricula, String senha) throws ExclusaoFuncionarioException {
		comite.excluiFuncionario(matricula, senha);
	}

	public void atualizaSenha(String senhaAntiga, String novaSenha) throws AtualizaFuncionarioException {
		comite.atualizaSenha(senhaAntiga, novaSenha);
	}

	public void fechaSistema() throws SistemaException {
		comite.fechaSistema();
	}

}
