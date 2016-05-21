package exceptions;

public class NaoAutorizadoException extends Exception {

	public NaoAutorizadoException(String nomeFuncionario, String acaoRealizada){
		super("O funcionario " + nomeFuncionario + " nao tem permissao para " + acaoRealizada);
	}
	
}
