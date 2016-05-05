package hospital;

import medicamento.Medicamento;
import pessoal.Paciente;
import admin.ComiteGestor;
import exceptions.AtualizaFuncionarioException;
import exceptions.AtualizaMedicamentoException;
import exceptions.CadastroMedicamentoException;
import exceptions.CadastroPacienteException;
import exceptions.CargoInvalidoException;
import exceptions.ConsultaFuncionarioException;
import exceptions.ConsultaMedicamentoException;
import exceptions.ConsultaProntuarioException;
import exceptions.DataInvalidaException;
import exceptions.ExclusaoFuncionarioException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NomeFuncionarioVazioException;
import exceptions.SistemaException;

public class HospitalFacade {

	ComiteGestor comite = ComiteGestor.getInstancia();

	public void iniciaSistema() {
		comite.iniciaSistema();
	}

	/**
	 * Metodo que sera executado apenas uma vez no programa, liberando o sistema
	 * para a criacao do primeiro usuario, um diretor geral, que ira poder
	 * gerenciar os sistemas.
	 * 
	 * @param chave
	 *            string que libera o sistema
	 * @return uma nova matricula com privilegios de um diretor geral
	 * @throws StringInvalidaException
	 *             caso a chave seja invalida
	 */
	public String liberaSistema(String chave, String nome, String dataNascimento) throws Exception {
		return comite.liberaSistema(chave, nome, dataNascimento);
	}

	/**
	 * Metodo que retorna como String o atributo especificado do funcionario
	 * 
	 * @param matricula
	 *            Matricula do funcionario a ter a informacao obtida
	 * @param atributo
	 *            Atributo a ser recuperado
	 * @return String que contem a informacao
	 * @throws ConsultaFuncionarioException
	 *             Caso nao seja possivel recuperar o funcionario
	 * @throws ConsultaFuncionarioException
	 *             Caso o atributo a ser recuperado seja a senha
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws Exception {
		return comite.getInfoFuncionario(matricula, atributo);
	}

	/**
	 * Metodo que realiza o login dos funcionarios, disponibilizando os metodos
	 * que os cabem
	 * 
	 * @param matricula
	 *            Especifica a matricula do funcionario a ser logado
	 * @param senha
	 *            Especifica a senha correspondente
	 * @throws LoginException
	 *             Caso a matricula ou a senha estejam invalidos ou nao
	 *             correspondam
	 */
	public void login(String matricula, String senha) throws LoginException {
		comite.login(matricula, senha);
	}

	/**
	 * Metodo que desloga o funcionario atualmente logado, habilitando assim o
	 * fechamento do sistema.
	 * 
	 * @throws LogoutException
	 *             Caso nao haja nenhum funcionario logado, lanca excecao
	 */
	public void logout() throws LogoutException {
		comite.logout();
	}

	/**
	 * Metodo que cadastra um novo funcionario no sistema
	 * 
	 * @param nome
	 *            Nome do novo funcionario
	 * @param cargo
	 *            Cargo que ele ocupa
	 * @param dataNascimento
	 *            Data de seu nascimento
	 * @throws MaisDeUmDiretorException
	 *             - Caso tente-se criar mais de um diretor
	 * @throws NomeFuncionarioVazioException
	 *             - Caso o nome do funcionario a ser criado esteja vazio
	 * @throws DataInvalidaException
	 *             - Caso a data de nascimento do funcionario a ser criado
	 *             esteja fora do padrao adotado
	 * @throws CargoInvalidoException
	 *             - Caso o cargo do funcionario a ser criada esteja vazio ou
	 *             nao exista
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) throws Exception {
		return comite.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	/**
	 * Metodo que sera usado apenas pelo diretor geral para atualizar
	 * informacoes de outros funcionarios
	 * 
	 * @param matricula
	 *            Matricula do funcionario a ter as informacoes atualizadas
	 * @param atributo
	 *            Informacao a ser atualizada
	 * @param novoValor
	 *            Novo valor do atributo selecionado para ser atualizado
	 * @throws AtualizaFuncionarioException
	 *             Caso o funcionario que esteja tentando realizar a operacao
	 *             nao tenha permissao.
	 * @throws AtualizaFuncionarioException
	 *             Caso a matricula esteja em um formato invalido
	 * @throws AtualizaFuncionarioException
	 *             Caso nao exista nenhum funcionario cadastrado com tal
	 *             matricula
	 * @throws AtualizaFuncionarioException
	 *             Caso o novo valor a ser inserido esteja invalido
	 */
	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor)
			throws AtualizaFuncionarioException {
		comite.atualizaInfoFuncionario(matricula, atributo, novoValor);
	}

	/**
	 * Metodo que sera usado pelo funcionario atualmente logado para atualizar
	 * suas prorias informacoes
	 * 
	 * @param atributo
	 *            Informacao a ser atualizada
	 * @param novoValor
	 *            Novo valor do atributo selecionado para ser atualizado
	 * @throws AtualizaFuncionarioException
	 *             Caso o funcionario que esteja tentando realizar a operacao
	 *             nao tenha permissao.
	 * @throws AtualizaFuncionarioException
	 *             Caso a matricula esteja em um formato invalido
	 * @throws AtualizaFuncionarioException
	 *             Caso nao exista nenhum funcionario cadastrado com tal
	 *             matricula
	 * @throws AtualizaFuncionarioException
	 *             Caso o novo valor a ser inserido esteja invalido
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws AtualizaFuncionarioException {
		comite.atualizaInfoFuncionario(atributo, novoValor);
	}

	/**
	 * Metodo que exlcui um funcionario do sistema
	 * 
	 * @param matricula
	 *            Matricula do funcionario a ser exlcuido
	 * @param senha
	 *            Senha correspodente a matricula
	 * @throws ExclusaoFuncionarioException
	 *             Caso o funcionario inexista no sistema
	 * @throws ExclusaoFuncionarioException
	 *             Caso a senha esteja invalida
	 * @throws ExclusaoFuncionarioException
	 *             Caso o funcionario logado nao tenha permissao para exlcuir
	 */
	public void excluiFuncionario(String matricula, String senha) throws ExclusaoFuncionarioException {
		comite.excluiFuncionario(matricula, senha);
	}
	
	/**
	 * Metodo que atualiza a senha de um funcionario. E necessario que o
	 * funcionario confirme sua senha antes de realizar a troca
	 * 
	 * @param senhaAntiga
	 *            Senha a ser confirmada
	 * @param novaSenha
	 *            Nova senha
	 * @throws AtualizaFuncionarioException
	 *             Caso nao seja possivel confirmar a senha do funcionario
	 */
	public void atualizaSenha(String senhaAntiga, String novaSenha) throws AtualizaFuncionarioException {
		comite.atualizaSenha(senhaAntiga, novaSenha);
	}
	
	/**
	 * Metodo que fecha o sistema
	 * 
	 * @throws SistemaException
	 *             Caso ainda exista um usuario logado
	 */
	public void fechaSistema() throws SistemaException {
		comite.fechaSistema();
	}

	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws CadastroMedicamentoException {
		return comite.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	public void atualizaMedicamento(String nome, String atributo, String novoValor)
			throws AtualizaMedicamentoException {
		comite.atualizaMedicamento(nome, atributo, novoValor);
	}

	public Medicamento forneceMedicamento(String nomeMedicamento, int quantidadeSolicitada) throws Exception {
		return comite.forneceMedicamento(nomeMedicamento, quantidadeSolicitada);
	}

	public Medicamento forneceMedicamento(String nomeMedicamento) throws Exception {
		return comite.forneceMedicamento(nomeMedicamento);
	}

	public String consultaMedCategoria(String categoria) throws ConsultaMedicamentoException {
		return comite.consultaMedCategoria(categoria);
	}

	public String consultaMedNome(String nomeDoRemedio) throws ConsultaMedicamentoException {
		return comite.consultaMedNome(nomeDoRemedio);
	}

	public String getEstoqueFarmacia(String ordenacao) throws ConsultaMedicamentoException {
		return comite.getEstoqueFarmacia(ordenacao);
	}

	public String getInfoMedicamento(String atributoDoMedicamento, String nomeMedicamento)
			throws ConsultaMedicamentoException {
		return comite.getInfoMedicamento(atributoDoMedicamento, nomeMedicamento);
	}

	public int cadastraPaciente(String nome, String data, double peso, String sexo, String genero, String tipoSanguineo)
			throws CadastroPacienteException {
		return comite.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo);
	}

	public int getNumeroCadastros() {
		return comite.getNumeroCadastros();
	}

	public String getInfoPaciente(int id, String atributo) {
		return comite.getInfoPaciente(id, atributo);
	}

	public int getProntuario(int posicao) throws ConsultaProntuarioException {
		return comite.getProntuario(posicao);
	}

}
