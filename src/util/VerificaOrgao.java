package util;

public class VerificaOrgao {

	public static void validaNome(String nome) throws Exception {
		if (nome.trim().equals("")) {
			throw new Exception("Nome do orgao nao pode ser vazio.");
		}
	}
	
	public static void validaTipoSanguineo(String tipoSanguineo) throws Exception {
		if (tipoSanguineo.trim().equals("")) {
			throw new Exception("Nome do tipo sanguineo nao pode ser vazio.");
		}
	}
	
}
