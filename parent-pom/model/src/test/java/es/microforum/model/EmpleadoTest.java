package es.microforum.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmpleadoTest {

	Empleado empleado1;
	Empleado empleado2;
	Empresa empresa;
	byte[] imagen; 
	
	@Before
	public void setUp() throws Exception {	
		imagen = new byte[0];
		empresa = new Empresa("123");		
		empleado1 = new Empleado("1",empresa,"Empleado1","Direccion1","Tipo1","EmpleadoCol1",20000.0,35.0,150.0,imagen);
		empleado2 = new Empleado("2",empresa,"Empleado2","Direccion2","Tipo2","EmpleadoCol2",22000.0,37.0,170.0,imagen);		
	}	

	@Test
	public void testEqualsObject() {
		assertTrue(!empleado1.equals(empleado2));
		assertTrue(empleado1.equals(empleado1));
	}
}
