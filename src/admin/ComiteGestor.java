package admin;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import util.Verificacao;

import exceptions.StringInvalida;
import pessoal.Funcionario;

public class ComiteGestor {

	private boolean primeiroAcesso;
	private int numeroMatriculas = 1;
	private final String CHAVE = "c041ebf8";
	private Funcionario funcLogado;

	private FactoryDeFuncionario facFuncionario;
	private Set<Funcionario> corpoClinico;
	private Set<Funcionario> corpoProfissional;
	private Set<Funcionario> diretoresGerais;
	private Map<String, String> matriculas;

	public ComiteGestor() throws Exception {

		this.primeiroAcesso = false;
		this.corpoClinico = new Set<Funcionario>();
		this.matriculas = new Map<String, String>();
		this.diretoresGerais = new Set<Funcionario>();
		this.corpoProfissional = new Set<Funcionario>();
		this.facFuncionario = new FactoryDeFuncionario();

	}

	/**
	 * Metodo que sera executado apenas uma vez no programa, liberando o sistema
	 * para a criacao do primeiro usuario, um diretor geral, que ira poder
	 * gerenciar os sistemas.
	 * 
	 * @param chave
	 *            string que libera o sistema
	 * @return uma nova matricula com privilegios de um diretor geral
	 * @throws StringInvalida
	 *             caso a chave seja invalida
	 */
	public String liberaSistema(String chave) throws Exception {
		
		Verificacao.validaString("chave", chave);
		
		if (!this.primeiroAcesso) {
			if (chave.equals(CHAVE)) {
				String matricula = geraMatricula("diretor");
				addMatricula(matricula, chave);
				diretoresGerais.add(new Funcionario(matricula, chave));
				this.primeiroAcesso = true;
				return matricula;

			} else {
				throw new StringInvalida("chave", "eh invalida");
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
	 * @throws StringInvalida
	 *             caso o parametro seja invalido, a criacao da matricula nao
	 *             sera possivel. Sendo assim uma excecao sera lancada.
	 * @return a nova matricula
	 */
	private String geraMatricula(String cargo) throws StringInvalida {

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
		return matricula;

	}

	/**
	 * Metodo que adiciona uma nova matricula no sistema juntamente com a senha
	 * correspondente
	 * 
	 * @param matricula
	 *            especifica a matricula a ser cadastrada
	 * @param senha
	 *            especifica a senha correspondente
	 * @throws StringInvalida
	 *             caso algum dos parametros seja invalido
	 */
	private void addMatricula(String matricula, String senha) throws StringInvalida {

		Verificacao.validaString("matricula", matricula);
		Verificacao.validaString("senha", senha);
		matriculas.put(matricula, senha);
	}
	
	private Funcionario getFuncionario(String matricula) {
		for(Funcionario func : corpoClinico)
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
	public boolean realizaLogin(String matricula, String senha) throws StringInvalida {

		Verificacao.validaString("matricula", matricula);
		Verificacao.validaString("senha", senha);

		if (existeCadastro(matricula, senha)) {
			funcLogado = getFuncionario(matricula);
			return true;
		}

		return false;
	}

}
