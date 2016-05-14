package prontuario;

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
	private List<ProcedimentoIF> procedimentos; // Para uso futuro

	/**
	 * Cria um novo Prontuario para o Paciente especificado
	 * 
	 * @param paciente
	 *            Paciente para o qual o prontuario sera gerado
	 */
	public Prontuario(Paciente paciente) {
		this.paciente = paciente;
		this.tabelaDeProcedimentos = new factoryDeProcedimentos();
		this.procedimentos = new ArrayList<ProcedimentoIF>();
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	/**
	 * Metodo que registra um procedimento medito no prontuario do paciente.
	 * 
	 * @param nomeDoPaciente
	 *            Nome do pacimente titular do prontuario onde sera registrado o
	 *            procedimento.
	 * @param nomeDoProcedimento
	 *            Nome do procedimento a ser registrado
	 * @param gastosComMedicamento
	 *            double com o total gasto no fornecimento de medicamentos
	 * @return O nome do procedimento registrado com sucesso.
	 * @throws Exception
	 * @throws CadastroFuncionarioException
	 * @throws ConsultaMedicamentoException
	 */
	public String realizaProcedimento(String nomeDoProcedimento, double gastosComMedicamento) throws Exception {

		ProcedimentoIF procedimento = tabelaDeProcedimentos.selecionaProcedimento(nomeDoProcedimento);
		double custoProcedimento = procedimento.realizaProcedimento(this.paciente, gastosComMedicamento);
		double totalGasto = custoProcedimento + gastosComMedicamento;
		
		totalGasto -= paciente.getDesconto(totalGasto);
		double novoSaldo = paciente.getSaldo() + totalGasto;

		int pontosFidelidadeGanhos = procedimento.getPontosBonus();
		paciente.adicionaPontosFidelidade(pontosFidelidadeGanhos);
		
		paciente.setSaldo(novoSaldo);
		procedimentos.add(procedimento);

		return nomeDoProcedimento;

	}
	

	public int getTotalProcedimento() {
		return procedimentos.size();
	}

	public int getIdade() {
		return paciente.getIdade();
	}

	public String getNome() {
		return paciente.getNome();
	}

	public String getData() {
		return paciente.getData();
	}

	public void setNome(String nome) throws Exception {
		paciente.setNome(nome);
	}

	public void setData(String data) throws DataInvalidaException {
		paciente.setData(data);
	}

	public String getID() {
		return paciente.getID();
	}

	public double getPeso() {
		return paciente.getPeso();
	}

	public String getTipoSanguineo() {
		return paciente.getTipoSanguineo();
	}

	public String getSexo() {
		return paciente.getSexo();
	}

	public String getGenero() {
		return paciente.getGenero();
	}

	public double getSaldo() {
		return paciente.getSaldo();
	}

	public int getPontosFidelidade() {
		return paciente.getPontosFidelidade();
	}

	public double getGastos() {
		return paciente.getGastos();
	}

	public void setGenero(String genero) {
		paciente.setGenero(genero);
	}

	public void setPeso(double novoPeso) {
		paciente.setPeso(novoPeso);
	}

	public void setSaldo(double valor) {
		paciente.setSaldo(valor);
	}

	/**
	 * Metodo que procura o nome do paciente
	 * 
	 * @return String contendo o nome do paciente associado ao prontuario
	 */
	public String getNomePaciente() {
		return paciente.getNome();
	}

	/**
	 * Metodo que procura a ID do paciente
	 * 
	 * @return String contendo a ID do paciente
	 */
	public String getPacienteID() {
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
