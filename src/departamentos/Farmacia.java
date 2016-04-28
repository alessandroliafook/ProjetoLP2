package departamentos;

import java.util.Set;
import java.util.TreeSet;

import exceptions.StringInvalida;
import medicamento.CategoriasEnum;
import medicamento.Medicamento;

public class Farmacia {

	Set<Medicamento> estoqueDeMedicamentos;
	
	public Farmacia(){
		
		this.estoqueDeMedicamentos = new TreeSet<Medicamento>();
		
	}
	
	
	public String consultaPorCategoria(String categoria){
		
		CategoriasEnum enumCategoria = CategoriasEnum.valueOf(categoria);
		String listaDeMedicamentos = "";
		
		for(Medicamento medicamento : estoqueDeMedicamentos){
			
			if (medicamento.getCategorias().contains(enumCategoria)){
				listaDeMedicamentos = listaDeMedicamentos + medicamento.toString() + "\n";
			}
			
		}

		return listaDeMedicamentos;
		
	}
	
	public String consultaPorNome(String nomeDoRemedio) throws StringInvalida{
		
		for(Medicamento medicamento : estoqueDeMedicamentos){
			
			if (medicamento.getNome().equals(nomeDoRemedio)){
				return medicamento.toString();
			}
			
		}
		
		throw new StringInvalida("nome do remedio", "nao cadastrado no sistema");
		
	}
	
}
