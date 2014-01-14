package es.microforum.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmpresaTest {

	Empresa empresa1;
	Empresa empresa2;
	
	@Before
	public void setUp() throws Exception {		
		empresa1 = new Empresa("123");
		empresa2 = new Empresa("456");		
	}

	@Test
	public void testEqualsObject() {
		assertTrue(!empresa1.equals(empresa2));
		assertTrue(empresa1.equals(empresa1));
	}
}
