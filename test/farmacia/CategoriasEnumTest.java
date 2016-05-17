package farmacia;

import static org.junit.Assert.*;

import org.junit.Test;

import farmacia.CategoriasEnum;

public class CategoriasEnumTest {

	@Test
	public void test() {

	assertTrue(CategoriasEnum.ANALGESICO.equals(CategoriasEnum.valueOf("ANALGESICO")));

	String string = CategoriasEnum.ANALGESICO.toString();
	assertTrue(string.equals("analgesico"));
	
	}

}
