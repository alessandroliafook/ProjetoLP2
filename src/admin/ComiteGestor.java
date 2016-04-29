package admin;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import util.Verificacao;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.StringInvalidaException;
import pessoal.Funcionario;

public class ComiteGestor {

	private Funcionario funcLogado;
	private boolean primeiroAcesso;
	private int numeroMatriculas = 1;
	private Funcionario diretorGeral;
	private final String CHAVE = "c041ebf8";

	private Set<Funcionario> corpoClinico;
	private Map<String, String> cadastros;
	private Set<Funcionario> corpoProfissional;
	private FactoryDeFuncionario facFuncionario;

	public ComiteGestor() throws Exception {

		this.primeiroAcesso = false;
		this.corpoClinico = new HashSet<Funcionario>();
		this.cadastros = new HashMap<String, String>();
		this.facFuncionario = new FactoryDeFuncionario();
		this.corpoProfissional = new HashSet<Funcionario>();

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
	public String liberaSistema(String chave) throws Exception {

		Verificacao.validaString("chave", chave);

		if (!this.primeiroAcesso) {
			if (chave.equals(CHAVE)) {
				primeiroCadastro("DEFAULT_NAME", "diretor", "00-00-0000", chave);
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
	 */
	private void primeiroCadastro(String nome, String cargo, String dataNascimento, String chave) {
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
		Verificacao.validaString("senha", senha);
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

		Verificacao.validaString("matricula", matricula);
		Verificacao.validaString("senha", senha);

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
	 *             - Caso algum dos parametros seja invalidos
	 * @throws DateTimeParseException
	 *             - Caso a data de nascimento nao esteja no formato adequado
	 */
	public void cadastraFuncionario(String nome, String cargo, String dataNascimento)
			throws StringInvalidaException, DateTimeParseException {

		Verificacao.validaString(nome, "nome");
		Verificacao.validaString(cargo, "cargo");
		Verificacao.validaString(dataNascimento, "data de nascimento");

		Funcionario func = facFuncionario.criaFuncionario(nome, dataNascimento, cargo, this.numeroMatriculas);
		realizaCadastro(func.getMatricula(), func.getSenha());

		switch (cargo) {
		case "diretor":
			diretorGeral = func;
			break;
		case "medico":
			corpoClinico.add(func);
			break;
		case "tecnico admin":
			corpoProfissional.add(func);
			break;
		}

		this.numeroMatriculas += 1;
	}

}
