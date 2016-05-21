package hospital;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exceptions.AtualizaFuncionarioException;
import exceptions.AtualizaMedicamentoException;
import exceptions.BancoDeOrgaosException;
import exceptions.CadastroMedicamentoException;
import exceptions.CadastroPacienteException;
import exceptions.CargoInvalidoException;
import exceptions.ConsultaFuncionarioException;
import exceptions.ConsultaMedicamentoException;
import exceptions.ConsultaPacienteException;
import exceptions.ConsultaProntuarioException;
import exceptions.DataInvalidaException;
import exceptions.ExclusaoFuncionarioException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NomeFuncionarioVazioException;
import exceptions.NumeroInvalidoException;
import exceptions.RemoveOrgaoException;
import exceptions.SistemaException;

public class HospitalControler {

	ComiteGestor comite;

	public HospitalControler() {
		comite = null;
	}

	/**
	 * Metodo que verifica se existe um arquivo .dat com a instancia do objeto
	 * comiteGestor, caso contrario realiza a criacao do referido caminho.
	 * 
	 * @throws Exception
	 *             Relanca as excecoes de erro de leitura ou de de escrita do
	 *             objeto;
	 */
	public void iniciaSistema() throws Exception {

		File file = new File("system_data");
		File arquivo = new File(file, "soos.dat");

		if (arquivo.exists()) {

			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(arquivo));
			ComiteGestor obj = (ComiteGestor) reader.readObject();
			this.comite = obj;
			reader.close();

		} else {
			
			file.mkdir();
			arquivo.createNewFile();
			this.comite = new ComiteGestor();
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(arquivo));
			writer.writeObject(this.comite);
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
		return comite.liberaSistema(chave, nome, dataNascimento);
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
		return comite.getGastosPaciente(id);
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
		return comite.getPontosFidelidade(id);
	}

	public void realizaProcedimento(String nomeDoProcedimento, String idDoPaciente) throws Exception {
		comite.realizaProcedimento(nomeDoProcedimento, idDoPaciente);
	}

	public void realizaProcedimento(String nomeDoProcedimento, String idDoPaciente, String listaDeMedicamentos)
			throws Exception {
		comite.realizaProcedimento(nomeDoProcedimento, idDoPaciente, listaDeMedicamentos);
	}

	public void realizaProcedimento(String nomeDoProcedimento, String idDoPaciente, String nomeDoOrgao,
			String listaDeMedicamentos) throws Exception {
		comite.realizaProcedimento(nomeDoProcedimento, idDoPaciente, nomeDoOrgao, listaDeMedicamentos);
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
	public void fechaSistema() throws Exception {
		
		comite.fechaSistema();

		File file = new File("system_data");
		File arquivo = new File(file, "soos.dat");
		ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(arquivo));

		writer.writeObject(this.comite);
		writer.close();

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
		return comite.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
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
		comite.atualizaMedicamento(nome, atributo, novoValor);
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
		return comite.forneceMedicamento(nomeMedicamento);
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
		return comite.consultaMedCategoria(categoria);
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
		return comite.consultaMedNome(nomeDoRemedio);
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
		return comite.getEstoqueFarmacia(ordenacao);
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
		return comite.getInfoMedicamento(atributoDoMedicamento, nomeMedicamento);
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
		return comite.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo);
	}

	/**
	 * Retorna o numero de cadastros ja realizados
	 * 
	 * @return O numero de pacientes ja cadastrados
	 */
	public int getNumeroCadastros() {
		return comite.getNumeroCadastros();
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
		return comite.getInfoPaciente(id, atributo);
	}

	/**
	 * Obtem as informacoes do prontuario de um paciente cadastrado no sistema
	 * 
	 * @param posicao
	 *            A posicao em que o prontuario esta armazenado no sistema
	 * @return Id do Paciente retirado do Prontuario na posicao especificada
	 * @throws ConsultaProntuarioException
	 *             Caso a posicao seja invalida
	 */
	public String getProntuario(int posicao) throws ConsultaProntuarioException {
		return comite.getProntuario(posicao);
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
		return comite.getTotalProcedimento(id);
	}

	/**
	 * Busca um paciente no sistema atraves do seu nome
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @return O ID do primeiro paciente com o nome especificado
	 * @throws ConsultaPacienteException
	 *             Lanca excecao acaso o paciente pesquisado nao esteja
	 *             cadastrado no sistema
	 */
	public String getPacienteID(String nome) throws Exception {
		return comite.getPacienteID(nome);
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
		comite.cadastraOrgao(nome, tipoSanguineo);
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
		return comite.buscaOrgPorSangue(tipoSanguineo);
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
		return comite.buscaOrgPorNome(nomeOrgao);
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
		return comite.buscaOrgao(nomeOrgao, tipoSanguineo);
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
		comite.retiraOrgao(nome, tipoSanguineo);
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
		return comite.qtdOrgaos(nome);
	}

	/**
	 * Medoto que retorna a quantidade de orgaos totais no banco de orgaos
	 * 
	 * @return A quantidade total de orgaos no banco de orgaos
	 */
	public int totalOrgaosDisponiveis() {
		return comite.totalOrgaosDisponiveis();
	}

}

