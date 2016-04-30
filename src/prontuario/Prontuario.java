package prontuario;

import java.util.List;
import pessoal.Paciente;

public class Prontuario implements Comparable<Prontuario> {

	private Paciente paciente;
	private List<Object> procedimentos; //Para uso futuro
	
	public Prontuario(){}
	
	public Paciente getPaciente(){
		return this.paciente;
	}

	@Override
	public int compareTo(Prontuario outroProntuario) {
		return this.paciente.compareTo(outroProntuario.getPaciente());
	}
}
