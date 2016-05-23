package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.*;

public class VerificaPessoa {

	/**
	 * Verifica se o nome fornecido eh valido
	 * 
	 * @param nome
	 *            String com nome a ser verificado
	 * @param isPaciente
	 *            Flag que indica se o nome sendo validado pertence a um
	 *            funcionario ou a um paciente
	 * @throws NomePacienteVazioException
	 *             Caso a Flag seja verdadeira e o nome seja vazio
	 * @throws NomeFuncionarioVazioException
	 *             Caso a Flag seja verdadeira e o nome seja vazio
	 */
	public static void validaNome(String nome, boolean isPaciente)
			throws NomePacienteVazioException, NomeFuncionarioVazioException {
		if (nome.trim().equals("")) {
			if (isPaciente)
				throw new NomePacienteVazioException();
			else
				throw new NomeFuncionarioVazioException();
		}
	}

	/**
	 * Valida uma string no formato de uma data de nascimento
	 * 
	 * @param data
	 *            String com a data a ser validada
	 * @throws DataInvalidaException
	 *             Caso a data nao esteja no formato "dd-mm-aaaa"
	 */
	public static void validaData(String data) throws DataInvalidaException {

		try {
			LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) {
			throw new DataInvalidaException();
		}

	}

	/**
	 * Valida uma string que representa o ID do paciente
	 * 
	 * @param id
	 *            String a ser validada
	 * @throws IDVazioException
	 *             Caso a String fornecida nao possa ser convertida para um
	 *             inteiro
	 */
	public static void validaIdPaciente(String id) throws IDVazioException {
		try {
			Integer.parseInt(id);
		} catch (Exception e) {
			throw new IDVazioException();
		}
	}

	/**
	 * Valida um valor real que representa o peso de uma pessoa
	 * 
	 * @param peso
	 *            O valor real a ser validado
	 * @throws PesoInvalidoException
	 *             Caso peso seja negativo
	 */
	public static void validaPeso(double peso) throws PesoInvalidoException {
		if (peso < 0)
			throw new PesoInvalidoException();
	}

	/***
	 * Valida uma String que representa um tipo sanguineo
	 * 
	 * @param tipoSanguineo
	 *            String a ser validada
	 * @throws TipoSanguineoInvalidoException
	 *             Caso a string nao esteja em um dos seguintes formatos "A+",
	 *             "A-", "O+", "O-", "AB-", "AB+", "B+", "B-"
	 */
	public static void validaTipoSanguineo(String tipoSanguineo) throws TipoSanguineoInvalidoException {

		if (tipoSanguineo == null || tipoSanguineo.trim().equals("")) {
			throw new TipoSanguineoInvalidoException();
		}

		String tiposAceitos[] = { "A+", "A-", "O+", "O-", "AB-", "AB+", "B+", "B-" };
		boolean isAceito = false;

		for (String tipo : tiposAceitos) {
			if (tipoSanguineo.equals(tipo)) {
				isAceito = true;
			}
		}

		if (!isAceito)
			throw new TipoSanguineoInvalidoException();
	}

	/**
	 * Valida uma String com o nome de um cargo do sistema
	 * 
	 * @param cargo
	 *            String a ser validada
	 * @throws CargoInvalidoException
	 *             Caso o cargo especificado nao exista ou seja vazio
	 */
	public static void validaCargo(String cargo) throws CargoInvalidoException {

		cargo = cargo.toLowerCase();

		if (cargo.trim().equals("")) {
			throw new CargoInvalidoException("Nome do cargo nao pode ser vazio.");
		}

		String cargosValidos[] = { "diretor geral", "medico", "tecnico administrativo" };
		boolean isValido = false;

		for (String cargoCorreto : cargosValidos) {
			if (cargo.equals(cargoCorreto)) {
				isValido = true;
			}
		}

		if (!isValido) {
			throw new CargoInvalidoException("Cargo invalido.");
		}
	}

}
