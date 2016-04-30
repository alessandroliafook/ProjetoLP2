package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import exceptions.*;

public class VerificaPessoa {

	public static void validaNome(String nome, boolean isPaciente) throws Exception {
		if (nome.trim().equals("")) {
			if (isPaciente)
				throw new NomePacienteVazioException();
			else
				throw new NomeFuncionarioVazioException();
		}
	}

	public static void validaData(String data) throws DataInvalidaException {
		DateTimeFormatter formatoAceito = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			LocalDate dataNascimento = LocalDate.parse(data, formatoAceito);
		} catch (DateTimeParseException e) {
			throw new DataInvalidaException();
		}

	}
	
	public static void validaPeso(double peso) throws PesoInvalidoException {
		if(peso < 0) throw new PesoInvalidoException();
	}
	
	public static void validaTipoSanguineo(String tipoSanguineo) throws TipoSanguineoInvalidoException{
		
		if(tipoSanguineo == null || tipoSanguineo.trim().equals("")){
			throw new TipoSanguineoInvalidoException();
		}
		
		String tiposAceitos[] = {"A+","A-","O+","O-","AB-","AB+","B+","B-"};
		boolean isAceito = false;
		
		for(String tipo : tiposAceitos){
			if(tipoSanguineo.equals(tipo)){
				isAceito = true;
			}
		}
		
		if(!isAceito) throw new TipoSanguineoInvalidoException();
	}
}
