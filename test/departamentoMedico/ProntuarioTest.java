package departamentoMedico;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import departamentoMedico.Prontuario;
import pessoal.Paciente;

public class ProntuarioTest {

	@Test
	public void testConstrutor() {
		try {
			Paciente paciente = new Paciente("pedro", "15/09/1995", 90, "B-", "masculino", "masculino", 1);
			Prontuario prontuario = new Prontuario(paciente);
			assertEquals(paciente, prontuario.getPaciente());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testEquals(){
		/*Dois prontuarios sao iguais caso seus pacientes sejam iguais*/
		try {
			Paciente paciente1 = new Paciente("pedro", "15/09/1995", 90, "B-", "masculino", "masculino", 1);
			Prontuario prontuario1 = new Prontuario(paciente1);
			
			Paciente paciente2 = new Paciente("carlos", "15/11/1945", 111, "A-", "masculino", "masculino", 23);
			Prontuario prontuario2 = new Prontuario(paciente2);
			
			Paciente paciente3 = new Paciente("Paul", "23/07/1972", 73, "AB+", "masculino", "masculino", 23);
			Prontuario prontuario3 = new Prontuario(paciente3);
			
			assertEquals(prontuario2, prontuario3);
			assertNotEquals(prontuario1, prontuario2);
			assertNotEquals(prontuario1, prontuario3);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testCompareTo(){
		/*Prontuarios sao ordenados pela ordem alfabetica do nome de seus pacientes*/
		try {
			Paciente paciente1 = new Paciente("antonio", "15/09/1995", 90, "B-", "masculino", "masculino", 1);
			Prontuario prontuario1 = new Prontuario(paciente1);
			
			Paciente paciente2 = new Paciente("bernardo", "15/11/1945", 111, "A-", "masculino", "masculino", 23);
			Prontuario prontuario2 = new Prontuario(paciente2);
			
			Paciente paciente3 = new Paciente("caio", "23/07/1972", 73, "AB+", "masculino", "masculino", 23);
			Prontuario prontuario3 = new Prontuario(paciente3);
			
			List<Prontuario> listaProntuarios = new ArrayList<Prontuario>();
			listaProntuarios.add(prontuario3);
			listaProntuarios.add(prontuario1);
			listaProntuarios.add(prontuario2);
			
			Collections.sort(listaProntuarios);
			
			assertEquals("antonio", listaProntuarios.get(0).getPaciente().getNome());
			assertEquals("bernardo", listaProntuarios.get(1).getPaciente().getNome());
			assertEquals("caio", listaProntuarios.get(2).getPaciente().getNome());
			
		} catch (Exception e) {
			fail();
		}
	}

}
