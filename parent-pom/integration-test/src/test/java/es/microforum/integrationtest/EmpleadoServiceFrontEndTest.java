package es.microforum.integrationtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-data-app-context.xml"})

public class EmpleadoServiceFrontEndTest {

	@Autowired
	EmpleadoService empleadoService;
	
	@Autowired
	DataSource dataSource;
	String dirFrontEnd = "http://localhost:8080/service-frontend";
	RestTemplate restTemplate;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void before() {
		jdbcTemplate = new JdbcTemplate(dataSource);	
		jdbcTemplate.execute("INSERT INTO EMPRESA VALUES('nifPrueba','nombreEmpresaPrueba','direccionPrueba',null,0)");
		jdbcTemplate.execute("INSERT INTO EMPLEADO VALUES('dniPrueba','nombreEmpleadoPrueba','direccionPrueba','tipoPrueba','colPrueba',20000,25,800,null,'nifPrueba',0)");
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void getTest() {
		try{
			URI uri = new URI(dirFrontEnd + "/empleado/dniPrueba");
			Resource<Empleado> resource = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Empleado>>() {}).getBody();
			assertTrue(resource.getContent().getNombre().equals("nombreEmpleadoPrueba"));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}
	
	@Test
	public void deleteTest() {
		try {			
			String nombreEmpleado = (String)jdbcTemplate.queryForObject("SELECT NOMBRE FROM EMPLEADO WHERE DNI= ?", new Object[]{ "dniPrueba" }, String.class);
			assertTrue(nombreEmpleado.equals("nombreEmpleadoPrueba"));
			URI uri = new URI(dirFrontEnd.toString() + "/empleado/dniPrueba");
			restTemplate.delete(uri);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		 
		assertTrue(empleadoService.findByDni("dniPrueba") == null);				
	}
	
	@Test
	public void postTest() throws RestClientException, URISyntaxException {
		String url = dirFrontEnd + "/empleado/";
		String acceptHeaderValue = "application/json";

		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.valueOf(acceptHeaderValue));
		requestHeaders.setAccept(mediaTypes);
		requestHeaders.setContentType(MediaType.valueOf(acceptHeaderValue));
		HttpMethod post = HttpMethod.POST;

		String body = "{\"dni\":\"dniPrueba\",\"nombre\":\"nombreEmpleadoPrueba\",\"direccion\":\"direccionPrueba\",\"tipoEmpleado\":\"tipoPrueba\",\"empleadocol\":\"colPrueba\",\"salarioAnual\":\"20000\",\"valorHora\":\"25\",\"cantidadHoras\":\"800\",\"empresa\":{\"nif\":\"nifPrueba\",\"version\":\"0\"},\"version\":\"0\"}";
		HttpEntity<String> entity = new HttpEntity<String>(body, requestHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, post, entity, String.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
		String nombreEmpleado = (String)jdbcTemplate.queryForObject("SELECT NOMBRE FROM EMPLEADO WHERE DNI= ?", new Object[]{ "dniPrueba" }, String.class);
		assertTrue(nombreEmpleado.equals("nombreEmpleadoPrueba"));
	}
	
	@Test
	public void putTest() throws RestClientException, URISyntaxException {
		String url = dirFrontEnd + "/empleado/dniPrueba";
		String acceptHeaderValue = "application/json";

		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.valueOf(acceptHeaderValue));
		requestHeaders.setAccept(mediaTypes);
		requestHeaders.setContentType(MediaType.valueOf(acceptHeaderValue));
		HttpMethod put = HttpMethod.PUT;

		String body = "{\"nombre\":\"nombreEmpleadoModificado\"}";
		HttpEntity<String> entity = new HttpEntity<String>(body, requestHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, put, entity, String.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
		String nombreEmpleado = (String)jdbcTemplate.queryForObject("SELECT NOMBRE FROM EMPLEADO WHERE DNI= ?", new Object[]{ "dniPrueba" }, String.class);
		assertTrue(nombreEmpleado.equals("nombreEmpleadoModificado"));
	}		
	
	@After
	public void tearDown() throws Exception {
		dataSource = null;
		dirFrontEnd = null;
		restTemplate = null;
		jdbcTemplate.execute("DELETE FROM EMPLEADO WHERE DNI='dniPrueba'");
		jdbcTemplate.execute("DELETE FROM EMPRESA WHERE NIF='nifPrueba'");	
	}

}
