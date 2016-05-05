package admin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import medicamento.Medicamento;
import departamentos.Clinica;
import departamentos.Farmacia;
import util.VerificacaoLiberaSistema;
import exceptions.AtualizaFuncionarioException;
import exceptions.AtualizaMedicamentoException;
import exceptions.CadastroFuncionarioException;
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

	private Farmacia farmacia;
	private Clinica clinica;

	private ComiteGestor() {

		this.primeiroAcesso = false;
		this.corpoClinico = new HashSet<Funcionario>();
		this.cadastros = new HashMap<String, String>();
		this.facFuncionario = new FactoryDePessoa();
		this.corpoProfissional = new HashSet<Funcionario>();
		this.farmacia = new Farmacia();
		this.clinica = new Clinica();
	}

	public void iniciaSistema() {
	}

	/**
	 * Metodo que garante que havera apenas uma instancia dessa classe. Caso ja
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

		this.primeiroAcesso = true;
		return cadastraFuncionario(nome, "diretor geral", dataNascimento);
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

		Funcionario funcionario = null;

		if (this.diretorGeral.getMatricula().equals(matricula))
			funcionario = diretorGeral;

		for (Funcionario func : corpoClinico) {
			if (func.getMatricula().equals(matricula)) {
				funcionario = func;
			}
		}

		for (Funcionario func : corpoProfissional) {
			if (func.getMatricula().equals(matricula)) {
				funcionario = func;
			}
		}

		return funcionario;
	}

	/**
	 * Metodo que verifica se a matrimedicamentocula do funcionario esta
	 * seguindo o padrao adotado e se este esta realmente matriculado
	 * 
	 * @param matricula
	 *            Matricula a ser verificada
	 * @throws ConsultaFuncionarioException
	 *             Caso nao esteja seguiundo o padrao, lanca excecao
	 */
	private void validaMatricula(String matricula) throws Exception {

		boolean apenasNumeros = true;
		boolean tamanhoCorreto = true;

		for (Character c : matricula.toCharArray()) {
			if (!Character.isDigit(c))
				apenasNumeros = false;
		}

		// if (matricula.length() != 8) {
		// tamanhoCorreto = false;
		// }

		if (!(tamanhoCorreto && apenasNumeros)) {
			throw new Exception("A matricula nao segue o padrao.");
		}
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
	private void validaLogin(String matricula, String senha) throws LoginException {

		String motivo = "";

		if (funcLogado != null) {
			motivo = "Um funcionario ainda esta logado: " + funcLogado.getNome() + ".";
			throw new LoginException(motivo);
		}

		// testa se a matricula esta cadastrada
		else if (!isMatriculado(matricula)) {
			motivo = "Funcionario nao cadastrado.";
			throw new LoginException(motivo);
		}

		// teste se a senha corresponde a matricula cadastrada
		else if (!isSenhaCorreta(matricula, senha)) {
			motivo = "Senha incorreta.";
			throw new LoginException(motivo);
		}
	}

	private boolean isSenhaCorreta(String matricula, String senha) {
		return cadastros.get(matricula).equals(senha);
	}

	private boolean isMatriculado(String matricula) {
		return cadastros.containsKey(matricula);
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
		validaLogin(matricula, senha);
		funcLogado = getFuncionario(matricula);
	}

	/**
	 * Metodo que adiciona uma nova matricula no sistema juntamente com a senha
	 * correspondente
	 * 
	 * @param matricula
	 *            especifica a matricula a ser cadastrada
	 * @param senha
	 *            especifica a senha correspondente
	 */

	private void adicionaLogin(String matricula, String senha) {
		cadastros.put(matricula, senha);
	}

	/**
	 * Medoto que remove uma matricula no sistema juntamente com a senha
	 * correspondente.
	 * 
	 * @param matricula
	 *            especifica a matricula a ser cadastrada
	 * @param senha
	 *            especifica a senha correspondente
	 */
	private void removeLogin(String matricula, String senha) {
		cadastros.put(matricula, "");
		cadastros.remove(matricula);
	}

	/**
	 * Metodo que desloga o funcionario atualmente logado, habilitando assim o
	 * fechamento do sistema.
	 * 
	 * @throws LogoutException
	 *             Caso nao haja nenhum funcionario logado, lanca excecao
	 */
	public void logout() throws LogoutException {
		if (funcLogado == null) {
			throw new LogoutException("Nao ha um funcionario logado.");
		}

		funcLogado = null;
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

		cargo = cargo.toLowerCase();

		validaPermissao();
		validaDiretor(cargo);

		try {

			Funcionario func = facFuncionario.criaFuncionario(nome, dataNascimento, cargo, this.numeroMatriculas);
			String mat = func.getMatricula();

			switch (cargo) {
			case "diretor geral":
				diretorGeral = func;
				break;
			case "medico":
				corpoClinico.add(func);
				break;
			case "tecnico administrativo":
				corpoProfissional.add(func);
				break;
			}

			adicionaLogin(func.getMatricula(), func.getSenha());
			this.numeroMatriculas += 1;

			return mat;

		} catch (NomeFuncionarioVazioException e) {
			throw new CadastroFuncionarioException(e.getMessage());
		} catch (DataInvalidaException e) {
			throw new CadastroFuncionarioException(e.getMessage());
		} catch (CargoInvalidoException e) {
			throw new CadastroFuncionarioException(e.getMessage());
		}
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

		try {
			validaMatricula(matricula);
		} catch (Exception e) {
			throw new ConsultaFuncionarioException(e.getMessage());
		}

		if (!isMatriculado(matricula)) {
			throw new ConsultaFuncionarioException("Funcionario nao cadastrado.");
		}

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

		// verifica se eh o diretor que esta tentando atualizar alguem
		if (!isAutorizado()) {
			throw new AtualizaFuncionarioException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para excluir funcionarios.");
		}

		// verifica se essa matricula eh valida
		try {
			validaMatricula(matricula);
		} catch (Exception e) {
			throw new AtualizaFuncionarioException(e.getMessage());
		}

		// sendo valida a matricula, verifica se ela realmente existe no sistema
		if (!isMatriculado(matricula)) {
			throw new AtualizaFuncionarioException("Funcionario nao cadastrado.");
		}

		Funcionario func = getFuncionario(matricula);

		switch (atributo) {
		case "Nome":
			try {
				func.setNome(novoValor);
			} catch (Exception e) {
				throw new AtualizaFuncionarioException(e.getMessage());
			}
			break;
		case "Data":
			try {
				func.setData(novoValor);
			} catch (Exception e) {
				throw new AtualizaFuncionarioException(e.getMessage());
			}
		}
	}

	/**
	 * Metodo que atualiza as informacoes do funcionario logado
	 * 
	 * @param atributo
	 *            Atributo que se deseja atualizar
	 * @param novoValor
	 *            Novo valor a ser atribuido
	 * @throws AtualizaFuncionarioException
	 *             Caso nao exista nenhum funcionario cadastrado com tal
	 *             matricula
	 * @throws AtualizaFuncionarioException
	 *             Caso o novo valor a ser inserido esteja invalido
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws AtualizaFuncionarioException {
		switch (atributo) {
		case "Nome":
			try {
				funcLogado.setNome(novoValor);
			} catch (Exception e) {
				throw new AtualizaFuncionarioException(e.getMessage());
			}
			break;
		case "Data":
			try {
				funcLogado.setData(novoValor);
			} catch (Exception e) {
				throw new AtualizaFuncionarioException(e.getMessage());
			}
		}
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

		if (!isAutorizado()) {
			throw new ExclusaoFuncionarioException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para excluir funcionarios.");
		}

		if (!confirmaDiretor(senha)) {
			throw new ExclusaoFuncionarioException("Senha invalida.");
		}

		try {
			validaMatricula(matricula);
		} catch (Exception e) {
			throw new ExclusaoFuncionarioException(e.getMessage());
		}

		if (!isMatriculado(matricula)) {
			throw new ExclusaoFuncionarioException("Funcionario nao cadastrado.");
		}

		excluiFuncionarioUtil(getFuncionario(matricula));
		removeLogin(matricula, senha);
	}

	private boolean confirmaDiretor(String senha) {
		return (senha.equals(diretorGeral.getSenha()));
	}

	/**
	 * Metodo que remove um funcionario do seu respectivo corpo de funcionarios
	 * 
	 * @param funcionario
	 *            Funcionario a ser removido
	 */
	private void excluiFuncionarioUtil(Funcionario funcionario) {

		if (funcionario.getCargo().equals("Medico")) {
			corpoClinico.remove(funcionario);
		} else if (funcionario.getCargo().equals("Tecnico Administrativo")) {
			corpoProfissional.remove(funcionario);
		}
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
		confirmaSenha(senhaAntiga);
		validaSenha(novaSenha);
		funcLogado.setSenha(novaSenha);
		cadastros.put(funcLogado.getMatricula(), novaSenha);
	}

	/**
	 * Metodo que valida a senha a ser atualizada
	 * 
	 * @param novaSenha
	 *            Senha a ser verificada
	 * @throws AtualizaFuncionarioException
	 *             Caso a senha nao siga o padrao estabelecido
	 */
	private void validaSenha(String novaSenha) throws AtualizaFuncionarioException {
		// A nova senha deve ter entre 8 - 12 caracteres alfanumericos.
		boolean tamanho = (novaSenha.length() >= 8 && novaSenha.length() <= 12);
		boolean alfanum = true;

		for (Character caractere : novaSenha.toCharArray()) {
			if (!Character.isAlphabetic(caractere) && !Character.isDigit(caractere)) {
				alfanum = false;
			}
		}

		if (!(tamanho && alfanum)) {
			throw new AtualizaFuncionarioException("A nova senha deve ter entre 8 - 12 caracteres alfanumericos.");
		}
	}

	/**
	 * Metodo que confirma a senha de um funcionario para futura troca de senha
	 * 
	 * @param senhaAntiga
	 *            Senha a ser confirmada
	 * @throws AtualizaFuncionarioException
	 *             Caso a senha nao seja confirmada
	 */
	private void confirmaSenha(String senhaAntiga) throws AtualizaFuncionarioException {
		if (!cadastros.get(funcLogado.getMatricula()).equals(senhaAntiga)) {
			throw new AtualizaFuncionarioException("Senha invalida.");
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
	private void validaDiretor(String cargo) throws CadastroFuncionarioException {
		if (diretorGeral != null && cargo.equals("diretor geral")) {
			throw new CadastroFuncionarioException("Nao eh possivel criar mais de um Diretor Geral.");
		}
	}

	/**
	 * Metodo que verifica se o usuario que esta logado tem permissao para
	 * realizar a tarefa
	 * 
	 * @throws CadastroFuncionarioException
	 *             Caso nao tenha permissao
	 */
	private void validaPermissao() throws CadastroFuncionarioException {
		if (diretorGeral != null && !isAutorizado()) {
			throw new CadastroFuncionarioException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para cadastrar funcionarios.");
		}
	}

	/**
	 * Metodo que verifica se eh o diretor que esta logado
	 * 
	 * @return - True se for o diretor que esta logado, False do contrario
	 */
	private boolean isAutorizado() {
		return (funcLogado.getMatricula().charAt(0) == '1');
	}

	/**
	 * Metodo que fecha o sistema
	 * 
	 * @throws SistemaException
	 *             Caso ainda exista um usuario logado
	 */
	public void fechaSistema() throws SistemaException {
		if (funcLogado != null) {
			throw new SistemaException("Um funcionario ainda esta logado: " + funcLogado.getNome() + ".");
		}
	}

	// metodos da farmacia

	/**
	 * @param nome
	 * @param tipo
	 * @param preco
	 * @param quantidade
	 * @param categorias
	 * @return
	 * @throws CadastroMedicamentoException
	 * @see departamentos.Farmacia#cadastraMedicamento(java.lang.String,
	 *      java.lang.String, double, int, java.lang.String)
	 */
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws CadastroMedicamentoException {

		if (!isDiretorOuTecnico()) {
			throw new CadastroMedicamentoException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para cadastrar medicamentos.");
		}

		return farmacia.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	/**
	 * @param nome
	 * @param atributo
	 * @param novoValor
	 * @throws AtualizaMedicamentoException
	 * @see departamentos.Farmacia#atualizaMedicamento(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void atualizaMedicamento(String nome, String atributo, String novoValor)
			throws AtualizaMedicamentoException {

		if (!isDiretorOuTecnico()) {
			throw new AtualizaMedicamentoException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para atualizar medicamentos.");
		}

		farmacia.atualizaMedicamento(nome, atributo, novoValor);
	}

	/**
	 * @param nomeMedicamento
	 * @param quantidadeSolicitada
	 * @return
	 * @throws Exception
	 * @see departamentos.Farmacia#forneceMedicamento(java.lang.String, int)
	 */
	public Medicamento forneceMedicamento(String nomeMedicamento, int quantidadeSolicitada) throws Exception {

		if (!isDiretorOuTecnico()) {
			throw new Exception(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para fornecer medicamentos.");
		}

		return farmacia.forneceMedicamento(nomeMedicamento, quantidadeSolicitada);
	}

	/**
	 * @param nomeMedicamento
	 * @return
	 * @throws Exception
	 * @see departamentos.Farmacia#forneceMedicamento(java.lang.String)
	 */
	public Medicamento forneceMedicamento(String nomeMedicamento) throws Exception {

		if (!isDiretorOuTecnico()) {
			throw new Exception(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para fornecer medicamentos.");
		}

		return farmacia.forneceMedicamento(nomeMedicamento);
	}

	/**
	 * @param categoria
	 * @return
	 * @throws ConsultaMedicamentoException
	 * @see departamentos.Farmacia#consultaMedCategoria(java.lang.String)
	 */
	public String consultaMedCategoria(String categoria) throws ConsultaMedicamentoException {

		if (!isDiretorOuTecnico()) {
			throw new ConsultaMedicamentoException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para consultar medicamentos.");
		}

		return farmacia.consultaMedCategoria(categoria);
	}

	/**
	 * @param nomeDoRemedio
	 * @return
	 * @throws ConsultaMedicamentoException
	 * @see departamentos.Farmacia#consultaMedNome(java.lang.String)
	 */
	public String consultaMedNome(String nomeDoRemedio) throws ConsultaMedicamentoException {

		if (!isDiretorOuTecnico()) {
			throw new ConsultaMedicamentoException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para consultar medicamentos.");
		}

		return farmacia.consultaMedNome(nomeDoRemedio);
	}

	/**
	 * @param ordenacao
	 * @return
	 * @throws ConsultaMedicamentoException
	 * @see departamentos.Farmacia#getEstoqueFarmacia(java.lang.String)
	 */
	public String getEstoqueFarmacia(String ordenacao) throws ConsultaMedicamentoException {

		if (!isDiretorOuTecnico()) {
			throw new ConsultaMedicamentoException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para consultar medicamentos.");
		}

		return farmacia.getEstoqueFarmacia(ordenacao);
	}

	/**
	 * @param atributoDoMedicamento
	 * @param medicamento
	 * @return
	 * @throws ConsultaMedicamentoException
	 * @see departamentos.Farmacia#getInfoMedicamento(java.lang.String,
	 *      medicamento.Medicamento)
	 */
	public String getInfoMedicamento(String atributoDoMedicamento, String nomeMedicamento)
			throws ConsultaMedicamentoException {

		if (!isDiretorOuTecnico()) {
			throw new ConsultaMedicamentoException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para consultar medicamentos.");
		}

		return farmacia.getInfoMedicamento(atributoDoMedicamento, nomeMedicamento);
	}

	// metodos da clinica

	/**
	 * @param nome
	 * @param data
	 * @param peso
	 * @param sexo
	 * @param genero
	 * @param tipoSanguineo
	 * @return
	 * @throws CadastroPacienteException
	 * @throws @see
	 *             departamentos.Clinica#cadastraPaciente(java.lang.String,
	 *             java.lang.String, double, java.lang.String, java.lang.String,
	 *             java.lang.String)
	 */
	public int cadastraPaciente(String nome, String data, double peso, String sexo, String genero, String tipoSanguineo)
			throws CadastroPacienteException {

		if (!isDiretorOuTecnico()) {
			throw new CadastroPacienteException(
					"O funcionario " + funcLogado.getNome() + " nao tem permissao para cadastrar pacientes.");
		}

		return clinica.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo);
	}

	private boolean isDiretorOuTecnico() {

		char caractere = funcLogado.getMatricula().charAt(0);
		return (caractere == '1' || caractere == '3');

	}

	/**
	 * @return
	 * @see departamentos.Clinica#getNumeroCadastros()
	 */
	public int getNumeroCadastros() {
		return clinica.getNumeroCadastros();
	}

	/**
	 * @param paciente
	 * @param atributo
	 * @return
	 * @see departamentos.Clinica#getInfoPaciente(pessoal.Paciente,
	 *      java.lang.String)
	 */
	public String getInfoPaciente(int id, String atributo) {
		return clinica.getInfoPaciente(id, atributo);
	}

	/**
	 * @param posicao
	 * @return
	 * @throws ConsultaProntuarioException
	 * @see departamentos.Clinica#getProntuario(int)
	 */
	public int getProntuario(int posicao) throws ConsultaProntuarioException {
		return clinica.getProntuario(posicao);
	}

}
