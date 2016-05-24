package departamentoMedico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exceptions.CadastroFuncionarioException;
import exceptions.ConsultaMedicamentoException;
import exceptions.DataInvalidaException;
import factory.factoryDeProcedimentos;
import pessoal.Paciente;
import procedimentos.ProcedimentoIF;

public class Prontuario implements Comparable<Prontuario>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1342099551666299695L;
	private Paciente paciente;
	private factoryDeProcedimentos tabelaDeProcedimentos;
	private List<String> procedimentos;

	/**
	 * Cria um novo Prontuario para o Paciente especificado
	 * 
	 * @param paciente
	 *            Paciente para o qual o prontuario sera gerado
	 */
	public Prontuario(Paciente paciente) {
		this.paciente = paciente;
		this.tabelaDeProcedimentos = new factoryDeProcedimentos();
		this.procedimentos = new ArrayList<String>();
	}

	/**
	 * Retorna o paciente pelo qual o prontuario eh responsavel
	 * 
	 * @return Um objeto do tipo paciente
	 */
	protected Paciente getPaciente() {
		return this.paciente;
	}

	/**
	 * Metodo que registra um procedimento medito no prontuario do paciente.
	 * 
	 * @param nomeDoProcedimento
	 *            Nome do procedimento a ser registrado
	 * @param gastosComMedicamento
	 *            double com o total gasto no fornecimento de medicamentos
	 * @return O nome do procedimento registrado com sucesso.
	 * @throws Exception
	 *             Caso o procedimento nao seja realizado com sucesso
	 */
	protected String realizaProcedimento(String nomeDoProcedimento, double gastosComMedicamento) throws Exception {

		ProcedimentoIF procedimento = tabelaDeProcedimentos.selecionaProcedimento(nomeDoProcedimento);
		double custoProcedimento = procedimento.realizaProcedimento(this.paciente, gastosComMedicamento);
		double totalGasto = custoProcedimento + gastosComMedicamento;

		totalGasto -= this.paciente.getDesconto(totalGasto);
		double novoSaldo = this.paciente.getSaldo() + totalGasto;

		int pontosFidelidadeGanhos = procedimento.getPontosBonus();
		this.paciente.adicionaPontosFidelidade(pontosFidelidadeGanhos);

		this.paciente.setSaldo(novoSaldo);

		return nomeDoProcedimento;

	}

	/**
	 * Adiciona no prontuario um procedimento que foi realizado no paciente
	 * 
	 * @param nomeDoProcedimento
	 *            Nome do procedimento que foi realizado
	 */
	protected void adicionaProcedimento(String nomeDoProcedimento) {

		this.procedimentos.add(nomeDoProcedimento);

	}

	/**
	 * Metodo que cria a ficha para ser exportada
	 * 
	 * @param idPaciente
	 *            Id do paciente que tera a ficha criada
	 * @return A ficha do paciente
	 * @throws Exception
	 *             Caso o id do paciente seja invalido
	 */
	public String getFicha() throws Exception {

		String infoPaciente = "Paciente: " + this.getNomePaciente() + "\n";
		infoPaciente += "Peso: " + this.paciente.getPeso() + " kg Tipo Sanguíneo: " + this.paciente.getTipoSanguineo()
				+ "\n";

		infoPaciente += "Sexo: " + this.paciente.getSexo() + " Genero: " + this.paciente.getGenero() + "\n";

		infoPaciente += "Gasto total: R$ " + this.paciente.getGastos() + " Pontos acumulados: "
				+ this.paciente.getPontosFidelidade() + "\n";
		infoPaciente += "Resumo de Procedimentos: " + this.getTotalProcedimento() + " procedimento(s)" + "\n";

		String ficha = infoPaciente;

		for (String procedimento : procedimentos) {
			ficha += procedimento;
		}

		return ficha;
	}

	/**
	 * Retorna o total de procedimentos realizados no paciente pelo qual o
	 * prontuario eh responsavel
	 * 
	 * @return Um inteiro, a quantidade de procedimentos realizados
	 */
	protected int getTotalProcedimento() {
		return procedimentos.size();
	}

	protected int getIdade() {
		return paciente.getIdade();
	}

	protected String getNome() {
		return paciente.getNome();
	}

	protected String getData() {
		return paciente.getData();
	}

	protected void setNome(String nome) throws Exception {
		paciente.setNome(nome);
	}

	protected void setData(String data) throws DataInvalidaException {
		paciente.setData(data);
	}

	public List<String> getProcedimentos() {
		return procedimentos;
	}

	protected String getID() {
		return paciente.getID();
	}

	protected double getPeso() {
		return paciente.getPeso();
	}

	protected String getTipoSanguineo() {
		return paciente.getTipoSanguineo();
	}

	protected String getSexo() {
		return paciente.getSexo();
	}

	protected String getGenero() {
		return paciente.getGenero();
	}

	protected double getSaldo() {
		return paciente.getSaldo();
	}

	protected int getPontosFidelidade() {
		return paciente.getPontosFidelidade();
	}

	protected double getGastos() {
		return paciente.getGastos();
	}

	protected void setGenero(String genero) {
		paciente.setGenero(genero);
	}

	protected void setPeso(double novoPeso) {
		paciente.setPeso(novoPeso);
	}

	protected void setSaldo(double valor) {
		paciente.setSaldo(valor);
	}

	/**
	 * Metodo que procura o nome do paciente
	 * 
	 * @return String contendo o nome do paciente associado ao prontuario
	 */
	protected String getNomePaciente() {
		return paciente.getNome();
	}

	/**
	 * Metodo que procura a ID do paciente
	 * 
	 * @return String contendo a ID do paciente
	 */
	protected String getPacienteID() {
		return this.paciente.getID();
	}

	/**
	 * Prontuarios sao comparados atraves do nome de seus pacientes
	 */
	@Override
	public int compareTo(Prontuario outroProntuario) {
		return this.paciente.compareTo(outroProntuario.getPaciente());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		return result;
	}

	/**
	 * Dois prontuarios sao iguais caso seus respectivos pacientes sejam iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Prontuario) {
			Prontuario outroProntuario = (Prontuario) obj;
			return this.paciente.equals(outroProntuario.getPaciente());
		}
		return false;
	}

}
