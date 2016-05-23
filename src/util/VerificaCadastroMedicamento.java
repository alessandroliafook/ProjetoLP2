package util;

import exceptions.NomeMedicamentoException;
import exceptions.NumeroInvalidoException;

public class VerificaCadastroMedicamento {

	
	/**
	 * Metodo que verifica se o nome do medicamento eh vazio.
	 * 
	 * @param nomeMedicamento
	 *            - String contendo o nome do medicamento.
	 * @throws NomeMedicamentoException 
	 *             - Lanca excecao caso a String com o nome do medicamento seja vazia.
	 */
	public static void validaNomeMedicamento(String nomeMedicamento) throws NomeMedicamentoException {

		if (nomeMedicamento.trim().equals("")) {
			
			throw new NomeMedicamentoException();

		}
	}

	/**
	 * Metodo que verifica se o preco do medicamento informado eh menor que zero.
	 * 
	 * @param precoMedicamento
	 *            Valor correspondente ao preco associado ao medicamento a ser verificado.
	 * @throws NumeroInvalidoException
	 *             Lanca excecao acaso o valor informado seja menor que zero.
	 */
	public static void validaPrecoMedicamento(double precoMedicamento) throws NumeroInvalidoException {
		if (precoMedicamento < 0) {
			String parametro = "Preco do medicamento";
			throw new NumeroInvalidoException(parametro);
		}
	}

	
	/**
	 * Metodo que verifica se a quantidade de medicamentos informada eh menor que zero.
	 * 
	 * @param qntMedicamento
	 *            Inteiro correspondente a quantidade  de medicamentos a ser verificada.
	 * @throws NumeroInvalidoException
	 *             Lanca excecao acaso a quantidade informada seja menor que zero.
	 */
	public static void validaQuantidadeMedicamento(int qntMedicamento) throws NumeroInvalidoException {
		if (qntMedicamento < 0) {
			String parametro = "Quantidade do medicamento";
			throw new NumeroInvalidoException(parametro);
		}
	}

	
}
