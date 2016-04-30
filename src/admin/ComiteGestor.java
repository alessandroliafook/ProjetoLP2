package admin;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import util.VerificaCadastroFuncionario;
import util.Verificacao;
import exceptions.CadastroFuncionarioException;
import exceptions.NaoAutorizadoException;
import exceptions.NovoNomeInvalidoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.StringInvalidaException;
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

		Verificacao.validaString(chave, "chave do sistema");

		if (!this.primeiroAcesso) {
			if (chave.equals(CHAVE)) {
				primeiroCadastro(nome, "diretor", dataNascimento, chave);
				this.primeiroAcesso = true;

				return diretorGeral.getMatricula();

			} else {
				throw new StringInvalidaException("chave", "eh invalida");
			}

		} else {
			throw new Exception("Primeiro login ja realizado");
		}
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

	private void realizaCadastro(String matricula, String senha) throws StringInvalidaException {

		Verificacao.validaString("matricula", matricula);
		Verificacao.validaString(senha, "senha do funcionario");
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
	private Funcionario getFuncionario(String matricula) throws ObjetoNaoEncontradoException {

		if (this.diretorGeral.getMatricula().equals(matricula))
			return this.diretorGeral;

		for (Funcionario func : corpoClinico) {
			if (func.getMatricula().equals(matricula)) {
				return func;
			}
		}

		for (Funcionario func : corpoProfissional) {
			if (func.getMatricula().equals(matricula)) {
				return func;
			}
		}

		throw new ObjetoNaoEncontradoException("funcionario de matricula " + matricula);
	}

	/**
	 * Metodo que verifica se existe um cadastro correspondente a matricula e
	 * senha informadas.
	 * 
	 * @param matricula
	 *            - Matricula a ser verificada
	 * @param senha
	 *            - Senha correspondente a matricula
	 * @return - True caso haja, false do contrario
	 */
	public boolean existeCadastro(String matricula, String senha) {
		if (cadastros.containsKey(matricula) && cadastros.get(matricula).equals(senha)) {
			return true;
		}

		return false;
	}

	/**
	 * Metodo que realiza o login dos funcionarios, disponibilizando os metodos
	 * que os cabem
	 * 
	 * @param matricula
	 *            especifica a matricula do funcionario a ser logado
	 * @param senha
	 *            especifica a senha correspondente
	 * @return True se o login seja efetuado com sucesso, False caso contrario
	 */
	public boolean realizaLogin(String matricula, String senha)
			throws StringInvalidaException, ObjetoNaoEncontradoException {

		Verificacao.validaString(matricula, "matricula do funcionario");
		Verificacao.validaString(senha, "senha do funcionario");

		if (existeCadastro(matricula, senha)) {
			funcLogado = getFuncionario(matricula);
			return true;
		}

		return false;
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
	 * @throws StringInvalidaException
	 *             - Caso alguns dos parametros seja invalido
	 * @throws DateTimeParseException
	 *             - Caso a data de nascimento nao esteja no formato adequado
	 */
	public void cadastraFuncionario(String nome, String cargo, String dataNascimento) throws Exception {

		VerificaCadastroFuncionario.validaNomeFuncionario(nome);
		VerificaCadastroFuncionario.validaDataFuncionario(dataNascimento);

		Funcionario func = facFuncionario.criaFuncionario(nome, dataNascimento, cargo, this.numeroMatriculas);
		realizaCadastro(func.getMatricula(), func.getSenha());

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

		this.numeroMatriculas += 1;
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
	public void atualizaNome(String matricula, String novoNome)
			throws StringInvalidaException, NovoNomeInvalidoException, ObjetoNaoEncontradoException {

		Verificacao.validaString(matricula, "matricula do funcionario");
		Verificacao.validaNovoNome(novoNome);

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
	public void atualizaDataNascimento(String matricula, String novaDataNascimento)
			throws StringInvalidaException, NovoNomeInvalidoException, ObjetoNaoEncontradoException {

		Verificacao.validaString(matricula, "matricula do funcionario");
		Verificacao.validaString(novaDataNascimento, "nova data de nascimento");

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
	public void atualizaSenha(String matricula, String novaDataNascimento)
			throws StringInvalidaException, ObjetoNaoEncontradoException {

		Verificacao.validaString(matricula, "matricula do funcionario");
		Verificacao.validaString(novaDataNascimento, "nova senha do funcionario");

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
