package departamentoMedico;

import java.io.Serializable;

public class Orgao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9103783912206092853L;
	
	private String nome;
	private String tipoSanguineo;

	/**
	 * Cria um objeto do tipo Orgao inicializando seu nome e seu tipo sanguineo
	 * 
	 * @param nome
	 *            Nome do orgao a ser criado
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao a ser criado
	 */
	public Orgao(String nome, String tipoSanguineo) {
		setNome(nome);
		setTipoSanguineo(tipoSanguineo);
	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected String getTipoSanguineo() {
		return tipoSanguineo;
	}

	protected void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((tipoSanguineo == null) ? 0 : tipoSanguineo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		Orgao other;

		if (obj instanceof Orgao) {
			other = (Orgao) obj;

			if (other.getNome().equals(getNome())
					&& other.getTipoSanguineo().equals(getTipoSanguineo())) {
				return true;
			}

		}

		return false;

	}

}
