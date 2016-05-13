package util;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.BancoDeOrgaosException;

public class VerificaOrgao {

	private final ArrayList<String> tiposSanguineos = new ArrayList<String>(
			Arrays.asList("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"));
	
	public static void validaNome(String nome) throws BancoDeOrgaosException {
		if (nome.trim().equals("")) {
			throw new BancoDeOrgaosException("Nome do orgao nao pode ser vazio.");
		}
	}
		
	public static void validaTipoSanguineo(String tipoSanguineo) throws BancoDeOrgaosException {
		if (tipoSanguineo.trim().equals("") || !tipoSanguineo.contains(tipoSanguineo)) {
			throw new BancoDeOrgaosException("Tipo sanguineo invalido.");
		}

	}

}
