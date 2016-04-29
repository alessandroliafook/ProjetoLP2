package pessoal;

import java.util.UUID;

import util.Verificacao;

public class Paciente extends Pessoa implements Comparable<Paciente> {

	private double peso;
	private String tipoSanguineo;
	private String sexo;
	private String genero;
	private UUID id;
	
	/**
	 * 
	 * @param nome - Nome do paciente
	 * @param dataNascimento - Data de nascimento no formato "dd-mm-aaaa"
	 * @param peso - Peso do paciente
	 * @param tipoSanguineo - Tipo sanguineo do paciente
	 * @param sexo - Sexo do paciente(M/F)
	 * @param genero - Genero do paciente
	 * @param id - Id gerada para o paciente
	 * @throws DateTimeParseException - Caso a data nao esteja no formato especificado
	 * @throws StringInvalidaException - Caso quaisquer string fornecida seja vazia ou nula
	 * @throws NumeroInvalidoException - Caso o peso do paciente seja negativo
	 */
	public Paciente(String nome, String dataNascimento, double peso, String tipoSanguineo,
			String sexo, String genero, UUID id) throws Exception {
		
		super(nome, dataNascimento);
		
		Verificacao.validaNumero(peso, "peso do paciente");
		Verificacao.validaString(sexo, "sexo do paciente");
		Verificacao.validaString(genero, "genero do paciente");
		Verificacao.validaObjeto(id, "id do paciente");
		
		setPeso(peso);
		setTipoSanguineo(tipoSanguineo);
		setSexo(sexo);
		setGenero(genero);
		setId(id);
	}
	
	public UUID getID(){
		return this.id;
	}
	

	public double getPeso() {
		return peso;
	}


	public String getTipoSanguineo() {
		return tipoSanguineo;
	}


	public String getSexo() {
		return sexo;
	}


	public String getGenero() {
		return genero;
	}


	private void setId(UUID id){
		this.id = id;
	}

	private void setGenero(String genero) {
		this.genero = genero;
	}

	private void setSexo(String sexo) {
		this.sexo = sexo;
	}

	private void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	private void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * Pacientes sao comparados pelo nome
	 */
	@Override
	public int compareTo(Paciente outroPaciente) {
		return super.getNome().compareTo(outroPaciente.getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Dois pacientes sao iguais caso tenham o mesmo ID
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
	
}
