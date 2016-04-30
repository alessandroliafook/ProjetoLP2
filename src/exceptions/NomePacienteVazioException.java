package exceptions;

public class NomePacienteVazioException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public NomePacienteVazioException(){
		super("Nome do paciente nao pode ser vazio.");
	}
	
}
