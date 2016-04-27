package admin;

import java.time.LocalDate;

import pessoal.Funcionario;

public class HospitalController {

	
	private final String CHAVE = "c041ebf8";
	private boolean isLiberado;
	private Funcionario usuario;
	private ComiteGestor comiteGestor;
	
	public HospitalController() throws Exception {
		
		this.comiteGestor = new ComiteGestor();
		this.isLiberado = false;
		
	
	}
	
	public boolean liberaSistema(String chaveSeguranca) throws Exception {

		if (chaveSeguranca.equals(CHAVE)) {

			this.isLiberado = true;
			return true;
		}

		else {
			
			return false;
		}
		
	}

	
	public boolean realizaLogin(){
		return false;
	}
	
	public boolean cadastraFuncionario(String nome, String cargo, LocalDate dataNascimento){
		return false;
	}
	
	public String geraMatricula(){
		String matricula = "";
		
		return matricula;
	}
	
	public String geraSenha(){
		String senha = "";
		
		return senha;
	}
	
	
	public void atualizaNome(){
		
	}
	
	public void atualizaNascimento(){
		
	}
	
	public Funcionario buscaUsuario(String matricula){
		return comiteGestor.buscaUsuario(matricula);
	}
	
}
