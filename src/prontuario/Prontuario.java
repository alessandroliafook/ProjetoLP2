package prontuario;

import java.io.Serializable;
import java.util.List;
import pessoal.Paciente;

public class Prontuario implements Comparable<Prontuario>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1342099551666299695L;
	private Paciente paciente;
	private List<Object> procedimentos; // Para uso futuro

	/**
	 * Cria um novo Prontuario para o Paciente especificado
	 * 
	 * @param paciente
	 *            Paciente para o qual o prontuario sera gerado
	 */
	public Prontuario(Paciente paciente) {
		this.paciente = paciente;
	}

	public Paciente getPaciente() {
		return this.paciente;
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
