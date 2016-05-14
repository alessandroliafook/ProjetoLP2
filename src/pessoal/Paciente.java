package pessoal;

import java.io.Serializable;

import cartao.CartaoFidelidade;
import util.VerificaPessoa;

public class Paciente extends Pessoa implements Comparable<Paciente>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1296421988284829321L;
	private double peso;
	private String tipoSanguineo;
	private String sexo;
	private String genero;
	private int id;
	private double saldo;
	private CartaoFidelidade cartaoFidelidade;

	/**
	 * Cria um novo objeto Funcionario com os parametros especificados
	 * 
	 * @param nome
	 *            - Nome do paciente
	 * @param dataNascimento
	 *            - Data de nascimento no formato "dd/mm/aaaa"
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
	public Paciente(String nome, String dataNascimento, double peso, String tipoSanguineo, String sexo, String genero,
			int id) throws Exception {

		super(nome, dataNascimento);

		VerificaPessoa.validaPeso(peso);
		VerificaPessoa.validaTipoSanguineo(tipoSanguineo);
		// VerificaPessoa.validaSexo(sexo);
		// VerificaPessoa.validaGenero(genero);
		// VerificaPessoa.validaId(id);
		this.cartaoFidelidade = new CartaoFidelidade();
		setPeso(peso);
		setTipoSanguineo(tipoSanguineo);
		setSexo(sexo);
		setGenero(genero);
		setId(id);
		setSaldo(0);
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

	public double getSaldo(){
		return this.saldo;
	}
	
	public int getPontosFidelidade(){
		return this.cartaoFidelidade.getPontosFidelidade();
	}
	
	public double getGastos(){
		return this.saldo;
	}
	
	private void setId(int id) {
		this.id = id;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	private void setSexo(String sexo) {
		this.sexo = sexo;
	}

	private void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public void setPeso(double novoPeso) {
		this.peso = novoPeso;
	}

	public void setSaldo(double valor){
		this.saldo = valor;
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
		if (obj instanceof Paciente) {
			Paciente outroPaciente = (Paciente) obj;
			return this.id == outroPaciente.getID();
		}
		return false;
	}

	@Override
	public String toString() {
		return super.getNome() + " id = " + id;
	}

}
