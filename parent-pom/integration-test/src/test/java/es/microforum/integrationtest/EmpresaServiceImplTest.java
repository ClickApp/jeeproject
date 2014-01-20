
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
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

import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpresaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-data-app-context.xml")
@TransactionConfiguration(defaultRollback=true)
public class EmpresaServiceImplTest {

	@Autowired
    EmpresaService empresaService;
    Empresa empresa;
    Empresa empresa2;
    SimpleDateFormat sdf;
    List<Empresa> empresas;
	
	@Before
	public void setUp() throws Exception {
		// Creamos una empresa para probar los test
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		empresa = new Empresa("nif1", "empresa1", "dir1", sdf.parse("01/01/2014"), null);
	}	

	@Test
	@Transactional
	public void testFindAll() {
		//Agregamos una empresa a la base de datos (Transactional) para al menos tener una empresa y poder buscar
		empresaService.save(empresa);		
		//Obtengo el listado de todas las empresas de la base de datos
		empresas = empresaService.findAll();
		//Compruebo que el listado tiene al menos 1 empresa y que la empresa agregada recientemente viene en ese listado
		assertTrue(empresas.size() > 0);
		assertTrue(empresas.contains(empresa));
	}

	@Test
	@Transactional
	public void testFindByNif() {
		//Agregamos una empresa a la base de datos (Transactional) para al menos tener una empresa y poder buscarla
		empresaService.save(empresa);
		//Busco la empresa por su nif y el resultado de la consulta lo meto en una variable empresa2
		empresa2 = empresaService.findByNif("nif1");
		//Compruebo que el nif de mi variable empresa2 es el mismo que el que guarde y por tanto la empresa devuelta por la consulta es correcta
		assertTrue(empresa2.getNif().equals("nif1"));
	}

	@Test
	@Transactional
	public void testSave() {
		//Agregamos una empresa a la base de datos (Transactional)
		empresaService.save(empresa);		
		//Busco la empresa por su clave primaria, en este caso el nif y el resultado de la consulta lo meto en mi variable empresa2
		empresa2 = empresaService.findByNif("nif1");		
		//Compruebo que el nif de la variable empresa2 es el mismo que el que he agregado, por tanto la empresa se agrego a la base de datos correctamente
		assertTrue(empresa2.getNif().equals("nif1"));
	}

	@Test
	@Transactional
	public void testDelete() {
		//Ejecuto el test en el que se añade una empresa a la base de datos, de forma que añado una empresa y compruebo que realmente se agrego
		testSave();
		//Elimino la empresa de la base de datos y compruebo que al buscar entre todas las empresas esa ya no esta
		empresaService.delete(empresa);
		assertTrue(!empresaService.findAll().contains(empresa));
	}
	
	@After
	public void tearDown() throws Exception {
		empresaService = null;
	    empresa = null;
	    empresa2 = null;
		sdf = null;	    
	    empresas = null;
	}

}
