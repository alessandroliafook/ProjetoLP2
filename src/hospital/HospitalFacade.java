package hospital;

import medicamento.Medicamento;
import pessoal.Paciente;
import admin.ComiteGestor;
import exceptions.AtualizaFuncionarioException;
import exceptions.AtualizaMedicamentoException;
import exceptions.CadastroMedicamentoException;
import exceptions.CadastroPacienteException;
import exceptions.ConsultaFuncionarioException;
import exceptions.ConsultaMedicamentoException;
import exceptions.ConsultaProntuarioException;
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

	public void validaMatricula(String matricula) throws Exception {
		comite.validaMatricula(matricula);
	}

	public void validaLogin(String matricula, String senha)
			throws LoginException {
		comite.validaLogin(matricula, senha);
	}

	public String cadastraMedicamento(String nome, String tipo, double preco,
			int quantidade, String categorias)
			throws CadastroMedicamentoException {
		return comite.cadastraMedicamento(nome, tipo, preco, quantidade,
				categorias);
	}

	public void atualizaMedicamento(String nome, String atributo,
			String novoValor) throws AtualizaMedicamentoException {
		comite.atualizaMedicamento(nome, atributo, novoValor);
	}

	public Medicamento forneceMedicamento(String nomeMedicamento,
			int quantidadeSolicitada) throws Exception {
		return comite.forneceMedicamento(nomeMedicamento, quantidadeSolicitada);
	}

	public Medicamento forneceMedicamento(String nomeMedicamento)
			throws ConsultaMedicamentoException {
		return comite.forneceMedicamento(nomeMedicamento);
	}

	public String consultaMedCategoria(String categoria)
			throws ConsultaMedicamentoException {
		return comite.consultaMedCategoria(categoria);
	}

	public String consultaMedNome(String nomeDoRemedio)
			throws ConsultaMedicamentoException {
		return comite.consultaMedNome(nomeDoRemedio);
	}

	public String getEstoqueFarmacia(String ordenacao)
			throws ConsultaMedicamentoException {
		return comite.getEstoqueFarmacia(ordenacao);
	}

	public String getInfoMedicamento(String atributoDoMedicamento,
			Medicamento medicamento) throws ConsultaMedicamentoException {
		return comite.getInfoMedicamento(atributoDoMedicamento, medicamento);
	}

	public Paciente cadastraPaciente(String nome, String data, double peso,
			String sexo, String genero, String tipoSanguineo)
			throws CadastroPacienteException {
		return comite.cadastraPaciente(nome, data, peso, sexo, genero,
				tipoSanguineo);
	}

	public int getNumeroCadastros() {
		return comite.getNumeroCadastros();
	}

	public String getInfoPaciente(Paciente paciente, String atributo) {
		return comite.getInfoPaciente(paciente, atributo);
	}

	public Paciente getProntuario(int posicao)
			throws ConsultaProntuarioException {
		return comite.getProntuario(posicao);
	}

	
	
}
