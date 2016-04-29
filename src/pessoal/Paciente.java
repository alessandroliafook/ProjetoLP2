package pessoal;

import util.Verificacao;

public class Paciente extends Pessoa implements Comparable<Paciente> {

	private double peso;
	private String tipoSanguineo;
	private String sexo;
	private String genero;
	private int id;

	/**
	 * 
	 * @param nome
	 *            - Nome do paciente
	 * @param dataNascimento
	 *            - Data de nascimento no formato "dd-mm-aaaa"
	 * @param peso
	 *            - Peso do paciente
	 * @param tipoSanguineo
	 *            - Tipo sanguineo do paciente
	 * @param sexo
	 *            - Sexo do paciente(M/F)
	 * @param genero
	 *            - Genero do paciente
	 * @param id
	 *            - Id gerada para o paciente
	 * @throws DateTimeParseException
	 *             - Caso a data nao esteja no formato especificado
	 * @throws StringInvalidaException
	 *             - Caso quaisquer string fornecida seja vazia ou nula
	 * @throws NumeroInvalidoException
	 *             - Caso o peso do paciente seja negativo
	 */
	public Paciente(String nome, String dataNascimento, double peso,
			String tipoSanguineo, String sexo, String genero,int id) throws Exception {

		super(nome, dataNascimento);

		Verificacao.validaNumeroReal(peso, "peso do paciente");
		Verificacao.validaString(sexo, "sexo do paciente");
		Verificacao.validaString(genero, "genero do paciente");
		Verificacao.validaNumeroInteiro(id, "id do paciente");

		setPeso(peso);
		setTipoSanguineo(tipoSanguineo);
		setSexo(sexo);
		setGenero(genero);
		setId(id);
	}

	public int getID() {
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

	private void setId(int id) {
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
		result = prime * result + id;
		return result;
	}

	/**
	 * Dois pacientes sao iguais caso tenham o mesmo id
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Paciente){
			Paciente outroPaciente = (Paciente) obj;
			return this.id == outroPaciente.getID();
		}
		return false;
	}

	

}
