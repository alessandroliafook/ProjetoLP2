package prontuario;

import java.io.Serializable;
import java.util.List;

import exceptions.CadastroFuncionarioException;
import exceptions.ConsultaMedicamentoException;
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
	private List<Object> procedimentos; // Para uso futuro

	/**
	 * Cria um novo Prontuario para o Paciente especificado
	 * 
	 * @param paciente
	 *            Paciente para o qual o prontuario sera gerado
	 */
	public Prontuario(Paciente paciente) {
		this.paciente = paciente;
		this.tabelaDeProcedimentos = new factoryDeProcedimentos();
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
	public String realizaProcedimento(String nomeDoProcedimento, double gastosComMedicamento) throws Exception{
		
		
		ProcedimentoIF procedimento = tabelaDeProcedimentos.selecionaProcedimento(nomeDoProcedimento);
		double custoProcedimento = procedimento.realizaProcedimento(this.paciente, gastosComMedicamento);
		double totalGasto =  custoProcedimento + gastosComMedicamento;
		double novoSaldo = paciente.getSaldo() + totalGasto;
		
		paciente.setSaldo(novoSaldo); 
		procedimentos.add(procedimento);
		
		return nomeDoProcedimento;
		
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
