package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceimpl.EmpleadoServiceImpl;

public class EmpleadoServiceImplTest {
	
	GenericXmlApplicationContext ctx;
	EmpleadoService empleadoService;
	Empleado empleado;
	
	@Before
	public void setUp() throws Exception {
		ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring-data-app-context.xml");
		ctx.refresh();		
		empleado = new Empleado("1234");
		empleadoService = ctx.getBean("springJpaEmpleadoService", EmpleadoService.class);
		//empleadoService.delete(empleado);
		//empleadoService.save(empleado);
	}

	@Test
	public void testFindAll() {				
		List<Empleado> empleados = empleadoService.findAll();
		assertTrue(empleados.size() > 0);
	}

//	@Test
//	public void testFindByDni() {
//		
//	}
//
//	@Test
//	public void testFindByNombre() {
//		
//	}
//
//	@Test
//	public void testSave() {
//		
//	}
//
//	@Test
//	public void testDelete() {
//		
//	}

}
