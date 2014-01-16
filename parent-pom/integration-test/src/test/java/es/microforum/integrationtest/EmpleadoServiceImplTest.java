package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
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
	
	GenericXmlApplicationContext ctx;
	@Autowired
	EmpleadoService empleadoService;
	Empleado empleado;
		
	@Before
	public void setUp() throws Exception {		
		empleado = new Empleado();	
	}

	@Test
	@Transactional
	public void testFindAll() {				
		empleado.setDni("123");
		empleadoService.save(empleado);				
		List<Empleado> empleados = empleadoService.findAll();
		assertTrue(empleados.contains(empleado));
	}

	@Test
	@Transactional
	public void testFindByDni() {		
		empleado.setDni("123");
		empleadoService.save(empleado);
		empleado = empleadoService.findByDni("123");
		assertTrue(empleado.getDni().equals("123"));		
	}

	@Test
	@Transactional
	public void testFindByNombre() {		
		empleado.setDni("123");
		empleado.setNombre("Javier");
		empleadoService.save(empleado);
		List<Empleado> empleados = empleadoService.findByNombre("Javier");	
		assertTrue(empleados.contains(empleado));
	}

	@Test
	@Transactional
	public void testSave() {
		empleado.setDni("123");
		empleadoService.save(empleado);
		empleado = empleadoService.findByDni("123");
		assertTrue(empleado.getDni().equals("123"));
	}

	@Test
	@Transactional
	public void testDelete() {
		empleado.setDni("123");
		empleadoService.save(empleado);
		empleado = empleadoService.findByDni("123");
		assertTrue(empleado.getDni().equals("123"));
		empleadoService.delete(empleado);
		assertTrue(!empleadoService.findAll().contains(empleado));
	}

}
