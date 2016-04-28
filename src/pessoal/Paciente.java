package pessoal;

import java.time.LocalDate;
import java.util.UUID;

public class Paciente extends Pessoa {

	private double peso;
	private String tipoSanguineo;
	private String sexo;
	private String genero;
	private UUID id;
	
	public Paciente(String nome, LocalDate dataNascimento, double peso, String tipoSanguineo,
			String sexo, String genero, UUID id){
		
		super(nome, dataNascimento);
		
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
	
}
