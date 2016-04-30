package exceptions;

public class MaisDeUmDiretorException extends Exception {
	
	public MaisDeUmDiretorException() {
		super("Nao eh possivel criar mais de um Diretor Geral.");
	}
	
}
