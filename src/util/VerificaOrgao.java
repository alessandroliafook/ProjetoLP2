package util;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.BancoDeOrgaosException;

public class VerificaOrgao {

	private static final ArrayList<String> tiposSanguineos = new ArrayList<String>(
			Arrays.asList("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"));
	
	public static void validaNome(String nome) throws Exception {
		if (nome.trim().equals("")) {
			throw new Exception("Nome do orgao nao pode ser vazio.");
		}
	}
		
	public static void validaTipoSanguineo(String tipoSanguineo) throws Exception {
		if (tipoSanguineo.trim().equals("") || !tiposSanguineos.contains(tipoSanguineo)) {
			throw new Exception("Tipo sanguineo invalido.");
		}

	}

}
