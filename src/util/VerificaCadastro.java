package util;

import exceptions.CadastroMedicamentoException;

public class VerificaCadastro {

	
	/**
	 * Metodo que verifica se o nome do medicamento eh vazio.
	 * 
	 * @param nomeMedicamento
	 *            - String contendo o nome do medicamento.
	 * @throws CadastroMedicamentoException 
	 *             - Lanca excecao caso a String com o nome do medicamento seja vazia.
	 */
	public static void validaNomeMedicamento(String nomeMedicamento) throws CadastroMedicamentoException {

		if (nomeMedicamento.trim().equals("")) {
			
			String motivo = "Nome do medicamento nao pode ser vazio.";
			throw new CadastroMedicamentoException(motivo);

		}
	}

	/**
	 * Metodo que verifica se o preco do medicamento informado eh menor que zero.
	 * 
	 * @param qntMedicamentos
	 *            - Inteiro correspondente ao preco associado ao medicamento a ser verificada.
	 * @throws CadastroMedicamentoException
	 *             - Lanca excecao acaso o valor informad0 seja menor que zero.
	 */
	public static void validaPrecoMedicamento(double precoMedicamento) throws CadastroMedicamentoException {
		if (precoMedicamento < 0) {
			String motivo = "Preco do medicamento nao pode ser negativo.";
			throw new CadastroMedicamentoException(motivo);
		}
	}

	
	/**
	 * Metodo que verifica se a quantidade de medicamentos informada eh menor que zero.
	 * 
	 * @param qntMedicamentos
	 *            - Inteiro correspondente a quantidade  de medicamentos a ser verificada.
	 * @throws CadastroMedicamentoException
	 *             - Lanca excecao acaso a quantidade informada seja menor que zero.
	 */
	public static void validaQuantidadeMedicamento(int qntMedicamento) throws CadastroMedicamentoException {
		if (qntMedicamento < 0) {
			String motivo = "Quantidade do medicamento nao pode ser negativo.";
			throw new CadastroMedicamentoException(motivo);
		}
	}

	
}
