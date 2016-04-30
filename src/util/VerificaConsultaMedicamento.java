package util;

import exceptions.ConsultaMedicamentoException;
import exceptions.ObjetoNaoEncontradoException;
import medicamento.CategoriasEnum;

public class VerificaConsultaMedicamento {

	/**
	 * Metodo que verifica se a categoria informada existe no sistema.
	 * 
	 * @param categoria
	 *            String contendo o nome da categoria que se pretende verificar.
	 * @throws ConsultaMedicamentoException
	 *             Lanca excecao acaso nao exista uma categoria com o nome
	 *             verificado.
	 */
	public static void validaCategoria(String categoria) throws ConsultaMedicamentoException {

		String motivo = "Categoria invalida.";

		for (CategoriasEnum categoriaEnum : CategoriasEnum.values()) {
			if (categoriaEnum.name().equalsIgnoreCase(categoria)) {
				return;
			}
		}

		throw new ConsultaMedicamentoException(motivo);

	}

}
