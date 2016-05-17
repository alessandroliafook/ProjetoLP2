package factory;

import exceptions.TipoMedicamentoException;
import farmacia.MedicamentoGenerico;
import farmacia.MedicamentoReferencia;
import farmacia.TipoMedicamentoIF;

public class FactoryTipoMedicamento {

	/**
	 * Metodo que escolhe qual objeto de TipoMedicamentoIF sera associado a
	 * instancia tipo.
	 * 
	 * @param tipo
	 *            String com o nome do tipo a ser escolhido dentre as
	 *            opcoes(referencia, generico).
	 * @throws TipoMedicamentoException
	 *             Lanca excecao acaso o tipo nao seja valido.
	 */
	public static TipoMedicamentoIF selecionaTipo(String tipo) throws TipoMedicamentoException {

		switch (tipo) {

		case "referencia":
			return new MedicamentoReferencia();

		case "generico":
			return new MedicamentoGenerico();

		default:
			throw new TipoMedicamentoException();

		}
	}

}
