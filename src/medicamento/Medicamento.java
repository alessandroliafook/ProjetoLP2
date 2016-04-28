package medicamento;

import java.util.HashSet;
import java.util.Set;

import exceptions.NumeroInvalido;
import exceptions.StringInvalida;



public class Medicamento implements Comparable<Medicamento> {

	private String nome;
	private double preco;
	private int quantidade;
	private Set<CategoriasEnum> categorias;
	private TipoMedicamentoIF tipo;

	public Medicamento(String nome, double preco, int quantidade, Set<String> categorias, String tipo) throws StringInvalida, NumeroInvalido {

		validaString(nome, "nome do medicamento");
		validaNumero(preco, "preco do medicamento");
		validaNumero(quantidade, "quantidade de medicamento");
		validaString(tipo, "tipo do medicamento");

		selecionaTipo(tipo);
		this.nome = nome;
		this.preco = this.tipo.calculaPreco(preco);
		this.quantidade = quantidade;
		this.categorias = new HashSet<CategoriasEnum>();

		for (String categoria : categorias) {

			validaString(categoria, "categoria do medicamento");
			this.categorias.add(CategoriasEnum.valueOf(categoria));
		}

	}

	private void selecionaTipo(String tipo) {
		switch (tipo) {
		case "referencia":
			this.tipo = new MedicamentoReferencia();
		case "generico":
			this.tipo = new MedicamentoGenerico();
		}
	}

	private void validaNumero(double preco, String parametro) throws NumeroInvalido {
		if (preco < 0) {
			String motivo = "eh menor que zero";
			throw new NumeroInvalido(parametro, motivo);
		}
	}

	private void validaString(String nome, String parametro) throws StringInvalida {

		if (nome.equals(null)) {
			String motivo = "igual a null";
			throw new StringInvalida(parametro, motivo);

		} else if (nome.trim().equals("")) {
			String motivo = "vazio";
			throw new StringInvalida(parametro, motivo);

		}
	}

	@Override
	public int compareTo(Medicamento outroMedicamento) {

		if (this.preco < outroMedicamento.getPreco()) {
			return 1;

		} else if (this.preco == outroMedicamento.getPreco()) {
			return 0;
		
		} else {
			return -1;
		}
	}

	public String toString(){
		
		String string = "";
		
		return string;
	}

	public String getNome(){
		return this.nome;
	}
	
	public double getPreco() {
		return this.preco;
	}

	public Set<CategoriasEnum> getCategorias(){
		return this.categorias;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
	
	
}
