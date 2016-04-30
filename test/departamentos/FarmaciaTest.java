package departamentos;

import static org.junit.Assert.*;

import org.junit.Test;

import medicamento.Medicamento;

public class FarmaciaTest {

	@Test
	public void test() {

		try{
		Medicamento valium = new Medicamento("Valium", "generico", 21.50, 45, "analgesico");
		Medicamento metamizol = new Medicamento("Metamizol", "referencia", 58.30, 466, "analgesico,antitermico");
		Medicamento morfina = new Medicamento("Morfina", "referencia", 150, 600, "analgesico");
		Medicamento medroxyprogesterona = new Medicamento("Medroxyprogesterona", "generico", 285.50, 600,
				"hormonal");
		Medicamento duraston = new Medicamento("Duraston", "generico", 112.50, 150, "hormonal");
		Medicamento nimesulida = new Medicamento("Nimesulida", "referencia", 12.50, 150,
				"antiinflamatorio,antitermico,analgesico");
		Medicamento penicilina = new Medicamento("Penicilina", "referencia", 80.00, 150, "antibiotico");	
	
	}catch(Exception e){
		
	}
	}
}
