package hospital;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clinica.Clinica;
import exceptions.AtualizaFuncionarioException;
import exceptions.AtualizaMedicamentoException;
import exceptions.BancoDeOrgaosException;
import exceptions.CadastroFuncionarioException;
import exceptions.CadastroMedicamentoException;
import exceptions.CadastroPacienteException;
import exceptions.ConsultaFuncionarioException;
import exceptions.ConsultaMedicamentoException;
import exceptions.ConsultaProntuarioException;
import exceptions.ExclusaoFuncionarioException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NaoAutorizadoException;
import exceptions.NumeroInvalidoException;
import exceptions.RealizaProcedimentoException;
import exceptions.RemoveOrgaoException;
import exceptions.SistemaException;
import factory.FactoryDePessoa;
import farmacia.Farmacia;
import pessoal.Funcionario;
import util.*;

public final class ComiteGestor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8379224367585964465L;

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

	public ComiteGestor() {

		this.primeiroAcesso = false;
		this.corpoClinico = new HashSet<Funcionario>();
		this.cadastros = new HashMap<String, String>();
		this.facFuncionario = new FactoryDePessoa();
		this.corpoProfissional = new HashSet<Funcionario>();
		this.farmacia = new Farmacia();
		this.clinica = new Clinica();
	}

	/**
	 * Verifica o numero de procedimentos realizados pelo paciente com a ID
	 * especificada
	 * 
	 * @param id
	 *            ID do paciente a ser consultado
	 * @return O total de procidementos a qual o paciente foi submetido
	 * @throws Exception
	 *             Caso nao exista paciente com o ID especificado
	 */
	public int getTotalProcedimento(String id) throws Exception {
		return clinica.getTotalProcedimento(id);
	}

	/**
	 * Busca o paciente com ID especificado e retorna o total gasto pelo mesmo
	 * 
	 * @param id
	 *            ID do paciente a ser verificado
	 * @return O total de gastos do paciente
	 * @throws Exception
	 *             Caso nao exista paciente com o ID repassado
	 */
	public String getGastosPaciente(String id) throws Exception {
		return clinica.getGastosPaciente(id);
	}

	/**
	 * Busca o paciente com ID especificado e retorna o total de pontos
	 * fidelidade do mesmo
	 * 
	 * @param id
	 *            ID do paciente a ser verificado
	 * @return O total de pontos de fidelidade que o paciente tem no momento
	 * @throws Exception
	 *             Caso nao exista o paciente com o ID repassado
	 */
	public int getPontosFidelidade(String id) throws Exception {
		return clinica.getPontosFidelidade(id);
	}

	public void iniciaSistema() throws Exception {

		File file = new File("system_data");
		File arquivo = new File(file, "soos.dat");

		if (file.exists()) {

			if (arquivo.exists()) {

				ObjectInputStream reader = new ObjectInputStream(new FileInputStream(arquivo));
				reader.close();

			} else {
				file.createNewFile();

			}

		} else {

			file.mkdir();
			arquivo.createNewFile();

			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(arquivo));
			writer.close();

		}

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

		try {
			estaMatriculado(matricula);
			existeFuncionarioLogado();
			verificaSenhaCorreta(matricula, senha);
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}

	}

	private void existeFuncionarioLogado() throws Exception {
		if (funcLogado != null) {
			throw new Exception("Um funcionario ainda esta logado: " + funcLogado.getNome() + ".");
		}
	}

	private void verificaSenhaCorreta(String matricula, String senha) throws Exception {
		if (!cadastros.get(matricula).equals(senha)) {
			throw new Exception("Senha incorreta.");
		}
	}

	private void estaMatriculado(String matricula) throws Exception {
		if (!cadastros.containsKey(matricula)) {
			throw new Exception("Funcionario nao cadastrado.");
		}
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
	 * @throws CadastroFuncionarioException
	 *             Caso o cadastro de funcionario nao seja bem sucedido
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento)
			throws CadastroFuncionarioException {

		cargo = cargo.toLowerCase();

		try {
			VerificaPrivilegiosDeDiretor.validaPermissao(funcLogado, "cadastrar funcionarios.");
			VerificaPrivilegiosDeDiretor.verificaExistenciaDeDiretor(cargo, this.diretorGeral);

			Funcionario funcionario = facFuncionario.criaFuncionario(nome, dataNascimento, cargo,
					this.numeroMatriculas);
			String matricula = funcionario.getMatricula();

			switch (cargo) {
			case "diretor geral":
				diretorGeral = funcionario;
				break;
			case "medico":
				corpoClinico.add(funcionario);
				break;
			case "tecnico administrativo":
				corpoProfissional.add(funcionario);
				break;
			}

			adicionaLogin(funcionario.getMatricula(), funcionario.getSenha());
			this.numeroMatriculas += 1;

			return matricula;

		} catch (Exception e) {
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
			VerificaCadastroFuncionario.validaMatricula(matricula);
			estaMatriculado(matricula);
			
			Funcionario funcionario = getFuncionario(matricula);
			String informacaoSolicitada = "";

			switch (atributo) {
			case "Nome":
				informacaoSolicitada = funcionario.getNome();
				break;
			case "Data":
				informacaoSolicitada = funcionario.getData();
				break;
			case "Cargo":
				informacaoSolicitada = funcionario.getCargo();
				break;
			case "Senha":
				throw new Exception("A senha do funcionario eh protegida.");
			}

			return informacaoSolicitada;
			
		} catch (Exception e) {
			throw new ConsultaFuncionarioException(e.getMessage());
		}

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
	 *             Caso o funcionario tentando realizar a operacao nao esteja
	 *             cadastro ou nao tenha permissoes suficientes. Ou se algum dos
	 *             dados informados seja invalido
	 */
	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor)
			throws AtualizaFuncionarioException {

		atributo = atributo.toLowerCase();

		try {
			VerificaPrivilegiosDeDiretor.validaPermissao(funcLogado, "excluir funcionarios.");
			VerificaCadastroFuncionario.validaMatricula(matricula);
			estaMatriculado(matricula);

			Funcionario funcionario = getFuncionario(matricula);

			switch (atributo) {
			case "nome":
				funcionario.setNome(novoValor);
				break;
			case "data":
				funcionario.setData(novoValor);
				break;
			default:
				throw new Exception("Atributo invalido.");
			}

		} catch (Exception e) {
			throw new AtualizaFuncionarioException(e.getMessage());
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
	 *             matricula, ou o novo valor a ser inserido seja invalido
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws AtualizaFuncionarioException {

		atributo = atributo.toLowerCase();

		try {

			switch (atributo) {
			case "nome":
				funcLogado.setNome(novoValor);
				break;

			case "data":
				funcLogado.setData(novoValor);
				break;
			default:
				throw new Exception("Atributo invalido.");
			}

		} catch (Exception e) {
			throw new AtualizaFuncionarioException(e.getMessage());
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

		try {
			VerificaPrivilegiosDeDiretor.validaPermissao(funcLogado, "excluir funcionarios.");
			VerificaCadastroFuncionario.validaMatricula(matricula);
			estaMatriculado(matricula);
			confirmaSenhaDiretor(senha);
		} catch (Exception e) {
			throw new ExclusaoFuncionarioException(e.getMessage());
		}

		excluiFuncionarioUtil(getFuncionario(matricula));
		removeLogin(matricula, senha);
	}

	/**
	 * Confirma se a senha passada eh igual a senha do diretor
	 * 
	 * @param senha
	 *            Suposta senha do diretor
	 * @throws Exception
	 *             Caso a senha passada nao seja a senha do diretor
	 */
	private void confirmaSenhaDiretor(String senha) throws Exception {
		if (!senha.equals(diretorGeral.getSenha())) {
			throw new Exception("Senha invalida.");
		}
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

		try {
			VerificaCadastroFuncionario.confirmaSenha(cadastros.get(funcLogado.getMatricula()), senhaAntiga);
			VerificaCadastroFuncionario.validaSenha(novaSenha);
		} catch (Exception e) {
			throw new AtualizaFuncionarioException(e.getMessage());
		}

		funcLogado.setSenha(novaSenha);
		cadastros.put(funcLogado.getMatricula(), novaSenha);
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
	 * Metodo que cadastra um medicamento no estoque da farmacia. Acaso o
	 * medicamento ja exista na farmacia, apenas adiciona a quantidade informada
	 * no objeto ja existente.
	 * 
	 * @param tipo
	 *            String que informa se o medicamento eh generico ou de
	 *            referencia.
	 * @param quantidade
	 *            Inteiro que indica a quantidade de medicamentos que se deseja
	 *            cadastrar no sistema.
	 * @param preco
	 *            Numero Real referente ao valor associado ao medicamento. Acaso
	 *            o medicamento seja do tipo generico, o preco associado sera
	 *            40% menor que o informado.
	 * @param nome
	 *            String referente ao nome do medicamento que se deseja
	 *            cadastrar.
	 * @param categorias
	 *            Conjunto de Strings com os nomes das categorias associadas ao
	 *            medicamento.
	 * @return Retorna o nome do medicamento cadastrado, acaso a operacao tenha
	 *         sido realizada com sucesso.
	 * @throws StringInvalidaException
	 *             Lanca excecao personalizada acaso qualques das String
	 *             informadas seja vazia ou igual a null.
	 * @throws NumeroInvalidoException
	 *             Lanca excecao acaso qualquer dos valores informados sejam
	 *             menores que zero.
	 */
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws CadastroMedicamentoException {

		try {
			VerificaAutorizacaoFarmacia.validaPermissao(funcLogado, "cadastrar medicamentos.");
		} catch (NaoAutorizadoException e) {
			throw new CadastroMedicamentoException(e.getMessage());
		}

		return farmacia.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	/**
	 * Metodo que atualiza o preco ou a quantidade de um medicamento existente.
	 * 
	 * @param nome
	 *            String contendo o nome do medicamento que se prentende
	 *            atualizar.
	 * @param atributo
	 *            String com o nome do atributo que se pretende atualizar, seja
	 *            preco ou quantidade.
	 * @param novoValor
	 *            double com o novo valor da ser atribuido, observe que no caso
	 *            da quantidade, o metodo converte o valor para inteiro.
	 * @throws AtualizaMedicamentoException
	 *             Lanca excecao acaso seja solicitado para modificar nome,
	 *             tipo, um atributo que nao exista, ou um medicamento nao
	 *             cadastrado no sistema.
	 */
	public void atualizaMedicamento(String nome, String atributo, String novoValor)
			throws AtualizaMedicamentoException {

		try {
			VerificaAutorizacaoFarmacia.validaPermissao(funcLogado, "atualizar medicamentos.");
		} catch (NaoAutorizadoException e) {
			throw new AtualizaMedicamentoException(e.getMessage());
		}

		farmacia.atualizaMedicamento(nome, atributo, novoValor);
	}

	/**
	 * Metodo que fornece um objeto do tipo medicamento solicitado pelo nome.
	 * 
	 * @param nomeMedicamento
	 *            String contendo o nome do medicamento a ser entregue.
	 * @return Objeto do tipo Medicamento.
	 * @throws ConsultaMedicamentoException
	 *             Lanca excecao acaso o medicamento pesquisado nao exista no
	 *             estoque.
	 */
	public double forneceMedicamento(String nomeMedicamento) throws Exception {

		try {
			VerificaAutorizacaoFarmacia.validaPermissao(funcLogado, "fornecer medicamentos.");
		} catch (NaoAutorizadoException e) {
			throw new Exception(e.getMessage());
		}

		return farmacia.forneceMedicamento(nomeMedicamento);
	}

	/**
	 * Metodo que consulta a lista de medicamentos associados a uma categoria
	 * informada.
	 * 
	 * @param categoria
	 *            - String com o nome da categoria associada aos medicamentos
	 *            que se pretende listar.
	 * @return String com a lista de medicamentos que contenham a categoria
	 *         pesquisada.
	 * @throws ConsultaMedicamentoException
	 *             Lanca excecao acaso a categoria nao exista, ou nao tenha
	 *             nenhum medicamento associado a mesma.
	 */
	public String consultaMedCategoria(String categoria) throws ConsultaMedicamentoException {

		try {
			VerificaAutorizacaoFarmacia.validaPermissao(funcLogado, "consultar medicamentos.");
		} catch (NaoAutorizadoException e) {
			throw new ConsultaMedicamentoException(e.getMessage());
		}

		return farmacia.consultaMedCategoria(categoria);
	}

	/**
	 * Metodo que retorna as informacoes importantes do medicamento solicitado.
	 * 
	 * @param nomeDoRemedio
	 *            String relacionada ao nome do medicamento que se pretende
	 *            obter as informacoes.
	 * @return String com as informacoes do medicamento solicitado.
	 * @throws ConsultaMedicamentoException
	 *             Lanca excecao acaso o nome informado seja igual a null ou
	 *             vazio.
	 */
	public String consultaMedNome(String nomeDoRemedio) throws ConsultaMedicamentoException {

		try {
			VerificaAutorizacaoFarmacia.validaPermissao(funcLogado, "consultar medicamentos.");
		} catch (NaoAutorizadoException e) {
			throw new ConsultaMedicamentoException(e.getMessage());
		}

		return farmacia.consultaMedNome(nomeDoRemedio);
	}

	/**
	 * Metodo que retorna uma a lista de medicamentos armazenados na farmacia.
	 * 
	 * @param ordenacao
	 *            String com o criterio de ordenacao desejado, que deve ser por
	 *            preco ou ordem alfabetica.
	 * @return String com os nomes dos medicamentos existentes na farmacia
	 *         ordenados segundo o parametro de ordenacao solicitado.
	 * @throws ConsultaMedicamentoException
	 *             Lanca excecao acaso o criterio de ordenacao nao seja por
	 *             preco ou ordem alfabética.
	 */
	public String getEstoqueFarmacia(String ordenacao) throws ConsultaMedicamentoException {

		try {
			VerificaAutorizacaoFarmacia.validaPermissao(funcLogado, "consultar medicamentos.");
		} catch (NaoAutorizadoException e) {
			throw new ConsultaMedicamentoException(e.getMessage());
		}

		return farmacia.getEstoqueFarmacia(ordenacao);
	}

	/**
	 * Metodo que consulta o atributo escolhido do medicamento escolhido.
	 * 
	 * @param atributoDoMedicamento
	 *            String com o atributo que se deseja informacao.
	 * @param medicamento
	 *            Objeto que se deseja a informacao de seu atributo.
	 * @return String contendo a informacao solicitada
	 * @throws ConsultaMedicamentoException
	 *             retorna excecao acaso o atributo nao exista.
	 */
	public String getInfoMedicamento(String atributoDoMedicamento, String nomeMedicamento)
			throws ConsultaMedicamentoException {

		try {
			VerificaAutorizacaoFarmacia.validaPermissao(funcLogado, "consultar medicamentos.");
		} catch (NaoAutorizadoException e) {
			throw new ConsultaMedicamentoException(e.getMessage());
		}

		return farmacia.getInfoMedicamento(atributoDoMedicamento, nomeMedicamento);
	}

	// metodos da clinica

	/**
	 * Metodo que tenta cadastrar um Paciente no sistema
	 * 
	 * @param nome
	 *            Nome do paciente a ser cadastrado
	 * @param data
	 *            Data de nascimento do paciente
	 * @param peso
	 *            Peso do paciente
	 * @param sexo
	 *            Sexo do paciente
	 * @param genero
	 *            Genero do paciente
	 * @param tipoSanguineo
	 *            Tipo sanguineo do paciente(A/B/O/AB seguido de + ou -) Ex: AB-
	 * @return O id do paciente cadastrado
	 * @throws CadastroPacienteException
	 *             Caso o cadastro nao seja bem sucedido
	 */
	public String cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) throws CadastroPacienteException {

		try {
			VerificaAutorizacaoFarmacia.validaPermissao(funcLogado, "cadastrar pacientes.");
		} catch (NaoAutorizadoException e) {
			throw new CadastroPacienteException(e.getMessage());
		}

		return clinica.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo);
	}

	/**
	 * Retorna o numero de cadastros ja realizados
	 * 
	 * @return O numero de pacientes ja cadastrados
	 */
	public int getNumeroCadastros() {
		return clinica.getNumeroCadastros();
	}

	/**
	 * Retorna a informacao solicitada do paciente especificado
	 * 
	 * @param paciente
	 *            Objeto Paciente do qual sera retirada a informacao solicitada
	 * @param atributo
	 *            Descricao da informacao
	 *            solicitada(Nome/Data/Sexo/Genero/TipoSanguineo/Peso/Idade
	 * @return Uma String com a informacao solicitada
	 */
	public String getInfoPaciente(String id, String atributo) throws Exception {
		return clinica.getInfoPaciente(id, atributo);
	}

	/**
	 * Verifica se o prontuario fornecido ja existe no sistema
	 * 
	 * @param prontuario
	 *            Objeto Prontuario a ser verificado
	 * @return True caso o objeto ja exista no sistema, False caso contrario
	 */
	public String getProntuario(int posicao) throws ConsultaProntuarioException {
		return clinica.getProntuario(posicao);
	}

	/**
	 * Metodo que registra um procedimento medito no prontuario do paciente.
	 * 
	 * @param nomeDoProcedimento
	 *            Nome do procedimento a ser registrado
	 * @param idDoPaciente
	 *            Id do paciente titular do prontuario onde sera registrado o
	 *            procedimento.
	 * @param listaDeMedicamentos
	 *            String com nomes dos medicamentos necessarios ao procedimento
	 * @throws Exception
	 *             Caso o procedimento nao seja realizado com sucesso
	 */
	public void realizaProcedimento(String nomeDoProcedimento, String idDoPaciente, String listaDeMedicamentos)
			throws Exception {

		try {
			VerificaAutorizacaoClinica.validaPermissao(funcLogado, "realizar procedimentos.");
			VerificaPessoa.validaIdPaciente(idDoPaciente);

			double gastosComMedicamento = farmacia.verificaEstoque(listaDeMedicamentos);

			clinica.realizaProcedimento(nomeDoProcedimento, idDoPaciente, gastosComMedicamento);

			for (String nomeMedicamento : listaDeMedicamentos.split(",")) {
				farmacia.forneceMedicamento(nomeMedicamento);
			}

		} catch (Exception e) {
			throw new RealizaProcedimentoException(e.getMessage());
		}

	}

	/**
	 * Metodo que registra um procedimento medito no prontuario do paciente,
	 * onde eh necessario realizar a disponibilizacao de um orgao existente no
	 * banco do hospital.
	 * 
	 * @param nomeDoProcedimento
	 *            Nome do procedimento a ser registrado
	 * @param idDoPaciente
	 *            Id do paciente titular do prontuario onde sera registrado o
	 *            procedimento.
	 * @param listaDeMedicamentos
	 *            String com nomes dos medicamentos necessarios ao procedimento
	 * @throws Exception
	 *             Caso o procedimento nao seja realizado com sucesso
	 */
	public void realizaProcedimento(String nomeDoProcedimento, String idDoPaciente, String nomeDoOrgao,
			String listaDeMedicamentos) throws Exception {

		try {
			VerificaAutorizacaoClinica.validaPermissao(funcLogado, "realizar procedimentos.");
			VerificaPessoa.validaIdPaciente(idDoPaciente);

			double gastosComMedicamento = farmacia.verificaEstoque(listaDeMedicamentos);

			clinica.realizaProcedimento(nomeDoProcedimento, idDoPaciente, nomeDoOrgao, gastosComMedicamento);

			for (String nomeMedicamento : listaDeMedicamentos.split(",")) {
				farmacia.forneceMedicamento(nomeMedicamento);
			}

		} catch (Exception e) {
			throw new RealizaProcedimentoException(e.getMessage());
		}

	}

	public void realizaProcedimento(String nomeDoProcedimento, String idDoPaciente) throws Exception {

		try {
			VerificaAutorizacaoClinica.validaPermissao(funcLogado, "realizar procedimentos.");
			VerificaPessoa.validaIdPaciente(idDoPaciente);

			clinica.realizaProcedimento(nomeDoProcedimento, idDoPaciente, 0);

		} catch (Exception e) {
			throw new RealizaProcedimentoException(e.getMessage());
		}

	}

	/**
	 * Busca um paciente no sistema atraves do seu nome
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @return O ID do primeiro paciente com o nome especificado
	 * @throws Exception
	 *             Lanca excecao acaso o nome seja vazio, ou o paciente nao
	 *             esteja cadastrado no sistema.
	 */
	public String getPacienteID(String nome) throws Exception {
		return clinica.getPacienteID(nome);
	}

	// METODOS DO BANCO DE ORGAOS

	/**
	 * Metodo que adiciona um novo orgao ao banco de orgaos
	 * 
	 * @param nome
	 *            Nome do orgao a ser adicionado
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao ser adicionado
	 * 
	 * @throws Exception
	 *             Caso o nome ou o tipo sanguineo do orgao sejam vazios
	 */
	public void cadastraOrgao(String nome, String tipoSanguineo) throws BancoDeOrgaosException {
		clinica.cadastraOrgao(nome, tipoSanguineo);
	}

	/**
	 * Metodo que retorna uma String contendo todos os orgaos compativeis com o
	 * tipo sanguineo especificado
	 * 
	 * @param tipoSanguineo
	 *            Tipo sanguineo a ser pesquisado
	 * @return Uma String contendo todos os orgaos compativeis com o tipo
	 *         sanguineo especificado
	 * @throws BancoDeOrgaosException
	 *             Caso o tipo sanguineo seja invalido
	 */
	public String buscaOrgPorSangue(String tipoSanguineo) throws BancoDeOrgaosException {
		return clinica.buscaOrgPorSangue(tipoSanguineo);
	}

	/**
	 * Metodo que retorna uma String contendo os tipos sanguineos compativeis
	 * com o o orgao especificado
	 * 
	 * @param nomeOrgao
	 *            Orgao a ser pesquisado
	 * @return Uma String contendo os tipos sanguineos compativeis com o o orgao
	 *         especificado
	 * @throws BancoDeOrgaosException
	 *             Caso o tipo o nome seja invalido ou nao haja orgaos
	 *             cadastrados com o nome especificados
	 */
	public String buscaOrgPorNome(String nomeOrgao) throws BancoDeOrgaosException {
		return clinica.buscaOrgPorNome(nomeOrgao);
	}

	/**
	 * Metodo que procura saber se ha um orgao compativel com tal tipo sanguineo
	 * 
	 * @param nomeOrgao
	 *            Nome do orgao a ser pesquisado
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao a ser pesquisado
	 * @return True caso haja
	 * @throws BancoDeOrgaosException
	 *             Caso o nome do orgao esteja invalido ou o tipo sanguineo
	 */
	public boolean buscaOrgao(String nomeOrgao, String tipoSanguineo) throws BancoDeOrgaosException {
		return clinica.buscaOrgao(nomeOrgao, tipoSanguineo);
	}

	/**
	 * Metodo que remove um orgao do banco de orgaos
	 * 
	 * @param nome
	 *            Nome do orgao a ser removido
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao a ser removido
	 * @throws Exception
	 *             Caso o nome ou o tipo sanguineo estejam vazios ou nao haja
	 *             orgaos desse tipo no banco de orgaos
	 */
	public void retiraOrgao(String nome, String tipoSanguineo) throws RemoveOrgaoException {
		clinica.retiraOrgao(nome, tipoSanguineo);
	}

	/**
	 * Metodo que retorna a quantidade de orgaos com o nome especificado
	 * 
	 * @param nome
	 *            Nome do orgao
	 * @return Quantidade de orgaos com o nome especificado
	 * @throws BancoDeOrgaosException
	 *             Caso nao exista algum orgao com o nome especificado
	 */
	public int qtdOrgaos(String nome) throws BancoDeOrgaosException {
		return clinica.qtdOrgaos(nome);
	}

	/**
	 * Medoto que retorna a quantidade de orgaos totais no banco de orgaos
	 * 
	 * @return A quantidade total de orgaos no banco de orgaos
	 */
	public int totalOrgaosDisponiveis() {
		return clinica.totalOrgaosDisponiveis();
	}

}
