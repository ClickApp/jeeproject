
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmpleadoTest {

	Empleado empleado1;
	Empleado empleado2;
	Empresa empresa;
	byte[] imagen; 
	
	@Before
	public void setUp() throws Exception {	
		//Creamos un empleado con constructor con todos los argumentos para probar los test
		imagen = new byte[0];
		empresa = new Empresa("nif1");		
		empleado1 = new Empleado("dni1",empresa,"Empleado1","Direccion1","Tipo1","EmpleadoCol1",20000.0,35.0,150.0,imagen);
		//Creamos un empleado con constructor que requiere solo un dni para probar los test
		empleado2 = new Empleado("dni2");				
	}	

	@Test
	public void testEqualsObject() {
		//Comprobamos que el empleado1 no es igual que el empleado2
		assertTrue(!empleado1.equals(empleado2));
		//Comprobamos que el empleado1 es igual el mismo
		assertTrue(empleado1.equals(empleado1));
	}
	
	@After
	public void tearDown() throws Exception
	{ 
		empleado1 = null;
		empleado2 = null;
		imagen = null;
		empresa = null;
	}
}
