package es.microforum.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmpresaTest {

	Empresa empresa1;
	Empresa empresa2;
	Set<Empleado> empleados;
	Date fecha;
	
	@Before
	public void setUp() throws Exception {		
		//Creamos una empresa con constructor con todos los argumentos
		fecha = new Date();
		empleados = new HashSet<Empleado>();
		empresa1 = new Empresa("nif1","empresa1","dir1",fecha,empleados);
		//Creamos una empresa con constructor que requiere solo un nif
		empresa2 = new Empresa("nif2");		
	}

	@Test
	public void testEqualsObject() {
		//Comprobamos que la empresa1 no es igual que la empresa2
		assertTrue(!empresa1.equals(empresa2));
		//Comprobamos que la empresa1 es igual que ella misma
		assertTrue(empresa1.equals(empresa1));
	}
	
	@After
	public void tearDown() throws Exception
	{ 
		empresa1 = null;
		empresa2 = null;
		fecha = null;
		empleados = null;
	}
}
