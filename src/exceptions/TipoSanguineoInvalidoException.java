package exceptions;

public class TipoSanguineoInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public TipoSanguineoInvalidoException(){
		super("Tipo sanguineo invalido.");
	}
}
