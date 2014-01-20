package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-data-app-context.xml")
@TransactionConfiguration(defaultRollback=true)
public class EmpleadoServiceImplTest {
	
	@Autowired
	EmpleadoService empleadoService;
	Empleado empleado;
	Empleado empleado2;
	List<Empleado> empleados;
		
	@Before
	public void setUp() throws Exception {		
		//Creo un empleado para probar los test
		empleado = new Empleado("dni1",null,"empleado1","dir1","tipo1","empleadoCol1",35000.0,35.0,150.0,null);			
	}

	@Test
	@Transactional
	public void testFindAll() {				
		//Agregamos un empleado a la base de datos (Transactional) para al menos tener un empleado y poder buscar
		empleadoService.save(empleado);		
		//Obtengo el listado de todos los empleados de la base de datos
		empleados = empleadoService.findAll();
		//Compruebo que el listado tiene al menos 1 empleado y que el empleado agregado recientemente viene en ese listado
		assertTrue(empleados.size() > 0);
		assertTrue(empleados.contains(empleado));
	}

	@Test
	@Transactional
	public void testFindByDni() {		
		//Agregamos un empleado a la base de datos (Transactional) para al menos tener un empleado y poder buscarlo
		empleadoService.save(empleado);
		//Busco el empleado por su id y el resultado de la consulta lo meto en una variable empleado2
		empleado2 = empleadoService.findByDni("dni1");
		//Compruebo que el dni de mi variable empleado2 es el mismo que el que guarde y por tanto el empleado devuelto por la consulta es correcto
		assertTrue(empleado2.getDni().equals("dni1"));
	}

	@Test
	@Transactional
	public void testFindByNombre() {		
		//Agregamos un empleado a la base de datos (Transactional) para al menos tener un empleado y poder buscarlo
		empleadoService.save(empleado);
		//Busco empleados por nombre y el resultado de la consulta lo meto en un List<Empleado> 
		empleados = empleadoService.findByNombre("empleado1");			
		//Compruebo que el List<Empleado> devuelto por la consulta contiene el empleado agregado cuyo nombre es por el cual realizo la misma
		assertTrue(empleados.contains(empleado));
	}

	@Test
	@Transactional
	public void testSave() {		
		//Agregamos un empleado a la base de datos (Transactional)
		empleadoService.save(empleado);		
		//Busco el empleado por su clave primaria, en este caso el dni y el resultado de la consulta lo meto en mi variable empleado2
		empleado2 = empleadoService.findByDni("dni1");		
		//Compruebo que el dni del empleado2 es el mismo que el que he agregado, por tanto el empleado se agrego a la base de datos corectamente
		assertTrue(empleado2.getDni().equals("dni1"));
	}

	@Test
	@Transactional
	public void testDelete() {
		//Ejecuto el test en el que se a�ade un empleado a la base de datos, de forma que a�ado un empleado y compruebo que realmente se agrego
		testSave();
		//Elimino el empleado de la base de datos y compruebo que al buscar entre todos los empleados ese ya no esta
		empleadoService.delete(empleado);
		assertTrue(!empleadoService.findAll().contains(empleado));
	}
	
	@After
	public void tearDown() throws Exception
	{ 
		empleadoService = null;
		empleado = null;
		empleado2 = null;
		empleados = null;
	}

}
