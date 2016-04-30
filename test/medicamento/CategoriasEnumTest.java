package medicamento;

import static org.junit.Assert.*;

import org.junit.Test;

public class CategoriasEnumTest {

	@Test
	public void test() {

	assertTrue(CategoriasEnum.ANALGESICO.equals(CategoriasEnum.valueOf("ANALGESICO")));

	String string = CategoriasEnum.ANALGESICO.toString();
	assertTrue(string.equals("analgesico"));
	
	}

}
