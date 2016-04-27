package departamentos;

public class Sistema {

	private final String CHAVE = "c041ebf8";

	public boolean liberaSistema(String chaveSeguranca) {

		if (chaveSeguranca.equals(CHAVE)) {

			return true;
		}

		else {

			return false;
		}
	}

}
