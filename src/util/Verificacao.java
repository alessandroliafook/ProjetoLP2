package util;

import exceptions.NumeroInvalido;
import exceptions.StringInvalida;

public class Verificacao {

	
	public static void validaNumero(double preco, String parametro) throws NumeroInvalido {
		if (preco < 0) {
			String motivo = "eh menor que zero";
			throw new NumeroInvalido(parametro, motivo);
		}
	}

	public static void validaString(String nome, String parametro) throws StringInvalida {

		if (nome.equals(null)) {
			String motivo = "igual a null";
			throw new StringInvalida(parametro, motivo);

		} else if (nome.trim().equals("")) {
			String motivo = "vazio";
			throw new StringInvalida(parametro, motivo);

		}
	}

	
}
