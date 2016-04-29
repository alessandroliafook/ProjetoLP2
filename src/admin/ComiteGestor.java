package admin;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import util.Verificacao;
import exceptions.ObjetoNaoEncontrado;
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
				String matricula = geraMatricula("diretor");
				realizaCadastro(matricula, chave);
				diretorGeral = new Funcionario(matricula, chave);
				this.primeiroAcesso = true;
				
				
				return matricula;

			} else {
				throw new StringInvalidaException("chave", "eh invalida");
			}

		} else {
			throw new Exception("Primeiro login ja realizado");
		}
	}

	/**
	 * Metodo que gera uma nova matricula de acordo com o cargo, ano de
	 * matricula e quantidade de matriculas
	 * 
	 * @param cargo
	 *            especifica o cargo ao qual a matricula correspondera
	 * @throws StringInvalidaException
	 *             caso o parametro seja invalido, a criacao da matricula nao
	 *             sera possivel. Sendo assim uma excecao sera lancada.
	 * @return a nova matricula
	 */
	private String geraMatricula(String cargo) throws StringInvalidaException {

		Verificacao.validaString("cargo", cargo);

		String prefixo = "";
		String matricula = "";
		LocalDate data = LocalDate.now();
		String sufixo = String.format("%03d", this.numeroMatriculas);

		switch (cargo) {
		case "diretor":
			prefixo = "1";
			break;
		case "medico":
			prefixo = "2";
			break;
		case "tecnico admin":
			prefixo = "3";
			break;
		}

		matricula = prefixo + String.valueOf(data.getYear()) + sufixo;
		this.numeroMatriculas += 1;
		return matricula;

	}

	private String geraSenha(String dataNascimento, String matricula) {

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

	private void addMatricula(String matricula, String senha) throws StringInvalidaException {

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
	private Funcionario getFuncionario(String matricula) throws ObjetoNaoEncontrado {

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

		throw new ObjetoNaoEncontrado("funcionario de matricula " + matricula);
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
	public boolean realizaLogin(String matricula, String senha) throws StringInvalidaException, ObjetoNaoEncontrado {

		Verificacao.validaString("matricula", matricula);
		Verificacao.validaString("senha", senha);

		if (existeCadastro(matricula, senha)) {
			funcLogado = getFuncionario(matricula);
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param nome
	 * @param cargo
	 * @param dataNascimento
	 * @return
	 * @throws StringInvalida
	 */
	public void cadastraFuncionario(String nome, String cargo, String dataNascimento)
			throws StringInvalidaException, DateTimeParseException {

		Verificacao.validaString(nome, "nome");
		Verificacao.validaString(cargo, "cargo");
		Verificacao.validaString(dataNascimento, "data de nascimento");

		String matricula = geraMatricula(cargo);
		String senha = geraSenha(dataNascimento, matricula);

		Funcionario func = facFuncionario.criaFuncionario(nome, dataNascimento, matricula, senha);
		realizaCadastro(func.getMatricula(), func.getSenha());
	}

}
