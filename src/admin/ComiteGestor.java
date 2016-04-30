package admin;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import util.VerificaCadastroFuncionario;
import util.VerificacaoLiberaSistema;
import exceptions.CadastroFuncionarioException;
import exceptions.CargoInvalidoException;
import exceptions.ConsultaFuncionarioException;
import exceptions.DataInvalidaException;
import exceptions.LoginException;
import exceptions.MaisDeUmDiretorException;
import exceptions.NomeFuncionarioVazioException;
import factory.FactoryDePessoa;
import pessoal.Funcionario;

public final class ComiteGestor {

	private static ComiteGestor instancia;

	private Funcionario funcLogado;
	private boolean primeiroAcesso;
	private int numeroMatriculas = 1;
	private Funcionario diretorGeral;
	private final String CHAVE = "c041ebf8";

	private Set<Funcionario> corpoClinico;
	private Map<String, String> cadastros;
	private Set<Funcionario> corpoProfissional;
	private FactoryDePessoa facFuncionario;

	private ComiteGestor() {

		this.primeiroAcesso = false;
		this.corpoClinico = new HashSet<Funcionario>();
		this.cadastros = new HashMap<String, String>();
		this.facFuncionario = new FactoryDePessoa();
		this.corpoProfissional = new HashSet<Funcionario>();

	}

	/**
	 * Método que garante que havera apenas uma instancia dessa classe. Caso ja
	 * tennha sido instanciada uma vez, retorna a instancia, do contrario
	 * instancia e retorna.
	 * 
	 * @return A unica instancia da classe
	 */
	public static synchronized ComiteGestor getInstancia() {
		if (instancia == null) {
			instancia = new ComiteGestor();

		}

		return instancia;
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

		VerificacaoLiberaSistema.validaAcesso(this.primeiroAcesso);
		VerificacaoLiberaSistema.validaChave(chave, CHAVE);

		primeiroCadastro(nome, "Diretor Geral", dataNascimento, chave);
		this.primeiroAcesso = true;

		return diretorGeral.getMatricula();
	}

	/**
	 * Metodo que realiza o cadastro do primeiro funcionario, sendo este o de um
	 * diretor geral
	 * 
	 * @param nome
	 *            Nome do diretor geral que devera ser atualizado posteriormente
	 * @param cargo
	 *            Diretor geral
	 * @param dataNascimento
	 *            Data de nascimento do diretor geral que devera ser atualizada
	 *            posteriormente
	 * @param chave
	 *            A senha do diretor geral sera igual a chave que libera o
	 *            sistema
	 * @throws Exception
	 */
	private void primeiroCadastro(String nome, String cargo, String dataNascimento, String chave) throws Exception {
		cadastraFuncionario(nome, cargo, dataNascimento);
		diretorGeral.setSenha(chave);
	}

	/**
	 * Metodo que adiciona uma nova matricula no sistema juntamente com a senha
	 * correspondente
	 * 
	 * @param matricula
	 *            especifica a matricula a ser cadastrada
	 * @param senha
	 *            especifica a senha correspondente
	 * @throws StringInvalidaException
	 *             caso algum dos parametros seja invalido
	 */

	private void realizaCadastro(String matricula, String senha) {
		cadastros.put(matricula, senha);
	}

	/**
	 * Metodo que busca um funcionario cadastrado pela matricula.
	 * 
	 * @param matricula
	 *            - Matricula pela qual sera pesquisado o funcionario.
	 * @throws ObjetoNaoEncontrado
	 *             - Caso o funcionario nao possa ser encontrado.
	 * @return - Retorna o funcionario que possuir a matricula especificada.
	 */
	private Funcionario getFuncionario(String matricula) {

		Funcionario f = null;

		if (this.diretorGeral.getMatricula().equals(matricula))
			f = diretorGeral;

		for (Funcionario func : corpoClinico) {
			if (func.getMatricula().equals(matricula)) {
				f = func;
			}
		}

		for (Funcionario func : corpoProfissional) {
			if (func.getMatricula().equals(matricula)) {
				f = func;
			}
		}

		return f;

	}

	/**
	 * Metodo que recupera informacoes do funcionario especificado, exceto a
	 * senha.
	 * 
	 * @param matricula
	 *            Matricula do funcionario a ter a informacao recuperada
	 * @param atributo
	 *            Atributo do funcionario a ser recuperado
	 * @return String que possui a informacao
	 * @throws ConsultaFuncionarioException
	 *             Caso o atributo a ser recuperado seja a senha do funcionario
	 */
	public String getFuncionarioInfo(String matricula, String atributo) throws ConsultaFuncionarioException {
		Funcionario func = getFuncionario(matricula);
		String ret = "";

		switch (atributo) {
		case "Nome":
			ret = func.getNome();
			break;
		case "Data":
			ret = func.getData();
			break;
		case "Cargo":
			ret = func.getCargo();
			break;
		case "Senha":
			throw new ConsultaFuncionarioException("A senha do funcionario eh protegida.");
		}

		return ret;

	}

	/**
	 * Metodo que verifica se existe um cadastro correspondente a matricula e
	 * senha informadas.
	 * 
	 * @param matricula
	 *            - Matricula a ser verificada
	 * @param senha
	 *            - Senha correspondente a matricula
	 * @throws LoginException
	 *             - Caso a matricula nao exista. Se existir e a senha estiver
	 *             errada, tambem lancara excecao.
	 */
	public void validaLogin(String matricula, String senha) throws LoginException {

		String motivo = "";

		// testa se a matricula esta cadastrada
		if (!cadastros.containsKey(matricula)) {
			motivo = "Funcionario nao cadastrado.";
			throw new LoginException(motivo);
		}

		// teste se a senha corresponde a matricula cadastrada
		else if (!cadastros.get(matricula).equals(senha)) {
			motivo = "Senha incorreta.";
			throw new LoginException(motivo);
		}
	}

	/**
	 * Metodo que realiza o login dos funcionarios, disponibilizando os metodos
	 * que os cabem
	 * 
	 * @param matricula
	 *            especifica a matricula do funcionario a ser logado
	 * @param senha
	 *            especifica a senha correspondente
	 * @throws LoginException
	 */
	public void realizaLogin(String matricula, String senha) throws LoginException {

		validaLogin(matricula, senha);
		funcLogado = getFuncionario(matricula);
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
	public void cadastraFuncionario(String nome, String cargo, String dataNascimento) throws Exception {

		validaDiretor(cargo);

		try {

			Funcionario func = facFuncionario.criaFuncionario(nome, dataNascimento, cargo, this.numeroMatriculas);

			switch (cargo) {
			case "Diretor Geral":
				diretorGeral = func;
				break;
			case "Medico":
				corpoClinico.add(func);
				break;
			case "Tecnico Administrativo":
				corpoProfissional.add(func);
				break;
			}

			realizaCadastro(func.getMatricula(), func.getSenha());
			this.numeroMatriculas += 1;

		} catch (NomeFuncionarioVazioException e) {
			throw new CadastroFuncionarioException(e.getMessage());
		} catch (DataInvalidaException e) {
			throw new CadastroFuncionarioException(e.getMessage());
		} catch (CargoInvalidoException e) {
			throw new CadastroFuncionarioException(e.getMessage());
		}

	}

	/**
	 * Metodo que valida a existencia de um diretor para evitar a criacao de
	 * outro
	 * 
	 * @param cargo
	 *            Cargo a ser validado
	 * @throws MaisDeUmDiretorException
	 *             Caso haja um diretor ja criado
	 */
	private void validaDiretor(String cargo) throws MaisDeUmDiretorException {
		if (diretorGeral != null && cargo.equals("Diretor Geral")) {
			throw new MaisDeUmDiretorException();
		}

	}

	/**
	 * Metodo que atualiza o nome de um funcionario. O diretor geral pode mudar
	 * essa informacao de qualquer outro funcionario, os demais podem apenas
	 * mudar a si.
	 * 
	 * @param matricula
	 *            - Matricula do funcionario a ser atualizado
	 * @param novoNome
	 *            - Novo nome a ser atualizado
	 * @throws StringInvalidaException
	 *             - Caso alguma dos parametros seja invalido
	 * @throws NovoNomeInvalidoException
	 *             - Caso o novo nome a ser atualizado nao siga o padrao
	 *             estabelecido
	 * @throws ObjetoNaoEncontradoException
	 */
	public void atualizaNome(String matricula, String novoNome) {

		if (autoAtualizacao(matricula)) {
			funcLogado.setNome(novoNome);
		} else if (diretorLogado()) {
			Funcionario func = getFuncionario(matricula);
			func.setNome(novoNome);
		}

	}

	/**
	 * Metodo que atualiza a data de nascimento de um funcionario. O diretor
	 * geral pode mudar essa informacao de qualquer outro funcionario, os demais
	 * podem apenas mudar a si.
	 * 
	 * @param matricula
	 *            - Matricula do funcionario a ser atualizado
	 * @param novoNome
	 *            - Nova data de nascimento a ser atualizada
	 * @throws StringInvalidaException
	 *             - Caso alguma dos parametros seja invalido
	 * @throws ObjetoNaoEncontradoException
	 * 
	 */
	public void atualizaDataNascimento(String matricula, String novaDataNascimento) {

		if (autoAtualizacao(matricula)) {
			funcLogado.setNome(novaDataNascimento);
		} else if (diretorLogado()) {
			Funcionario func = getFuncionario(matricula);
			func.setNome(novaDataNascimento);
		}

	}

	/**
	 * Metodo que atualiza a senha de um funcionario. O diretor geral pode mudar
	 * essa informacao de qualquer outro funcionario, os demais podem apenas
	 * mudar a si.
	 * 
	 * @param matricula
	 *            - Matricula do funcionario a ser atualizado
	 * @param novoNome
	 *            - Nova senha a ser atualizada
	 * @throws StringInvalidaException
	 *             - Caso algum dos parametros seja invalido
	 * @throws ObjetoNaoEncontradoException
	 */
	public void atualizaSenha(String matricula, String novaDataNascimento) {

		if (autoAtualizacao(matricula)) {
			funcLogado.setNome(novaDataNascimento);
		} else if (diretorLogado()) {
			Funcionario func = getFuncionario(matricula);
			func.setNome(novaDataNascimento);
		}

	}

	/**
	 * Metodo que verifica se eh o diretor que esta logado
	 * 
	 * @return - True se for o diretor que esta logado, False do contrario
	 */
	private boolean diretorLogado() {
		return (funcLogado.getMatricula().charAt(0) == '1');
	}

	/**
	 * Metodo que verifica se um funcionario esta se autoatualizando
	 * 
	 * @param matricula
	 *            - Matricula do funcionario a ser verificado
	 * @return - True se um funcionario estiver se atualizando, False do
	 *         contrario
	 */
	private boolean autoAtualizacao(String matricula) {
		return (matricula.equals(funcLogado.getMatricula()));
	}

}
