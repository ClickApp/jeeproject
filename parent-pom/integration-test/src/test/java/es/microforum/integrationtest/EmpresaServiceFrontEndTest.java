package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import es.microforum.model.Empresa;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-data-app-context.xml"})
public class EmpresaServiceFrontEndTest {

	@Autowired
	DataSource dataSource;
	String dirFrontEnd = "http://localhost:8080/service-frontend";
	RestTemplate restTemplate;
	private JdbcTemplate jdbcTemplate;

	
	@Before
	public void setUp() throws Exception {		
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("DELETE FROM EMPRESA WHERE NIF LIKE '%nifPrueba%'");
		restTemplate = new RestTemplate();
	}	

	@Test
	public void getTest() {
		try{
			jdbcTemplate.execute("INSERT INTO EMPRESA VALUES('nifPrueba','nombrePrueba','DirPrueba',null,0)");
			URI uri = new URI(dirFrontEnd + "/empresa/nifPrueba");
			Resource<Empresa> resource = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Empresa>>() {}).getBody();
			assertTrue(resource.getContent().getNombre().equals("nombrePrueba"));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void deleteTest() {
		try {
			jdbcTemplate.execute("INSERT INTO EMPRESA VALUES('nifPrueba','nombrePrueba','DirPrueba',null,0)");
			int count = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM EMPRESA WHERE NIF='nifPrueba'");
			assertTrue(count == 1);
			URI uri = new URI(dirFrontEnd.toString() + "/empresa/nifPrueba");
			restTemplate.delete(uri);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		int count = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM EMPRESA WHERE NIF='nifPrueba'");
		assertTrue(count == 0);
	}
	
//	@Test
//	public void postTest() throws RestClientException, URISyntaxException {		
//		String url = dirFrontEnd + "/empresa/";
//		String acceptHeaderValue = "application/json";
//
//		HttpHeaders requestHeaders = new HttpHeaders();
//		List<MediaType> mediaTypes = new ArrayList<MediaType>();
//		mediaTypes.add(MediaType.valueOf(acceptHeaderValue));
//		requestHeaders.setAccept(mediaTypes);
//		requestHeaders.setContentType(MediaType.valueOf(acceptHeaderValue));
//		HttpMethod post = HttpMethod.POST;
//
//		String body = "{\"Nif\":\"nifPrueba\",\"Nombre\":\"nombrePrueba\",\"DireccionFiscal\":\"DirPrueba\",\"FechaInicioActividades\":\"2014-01-10\",\"Version\":\"0\"}";
//		HttpEntity<String> entity = new HttpEntity<String>(body, requestHeaders);
//
//		ResponseEntity<String> response = restTemplate.exchange(url, post, entity, String.class);
//		assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
//		int count = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM EMPRESA WHERE NIF='nifPrueba'");
//		assertTrue(count == 1);
//		jdbcTemplate.execute("DELETE FROM EMPRESA WHERE NIF='nifPrueba'");
//	}
	
//	@Test
//	public void putTest() throws RestClientException, URISyntaxException {
//		jdbcTemplate.execute("INSERT INTO EMPRESA VALUES('nifPrueba','nombrePrueba','DirPrueba','2014-01-10',0)");
//		String url = dirFrontEnd + "/empresa/nifPrueba";
//		String acceptHeaderValue = "application/json";
//
//		HttpHeaders requestHeaders = new HttpHeaders();
//		List<MediaType> mediaTypes = new ArrayList<MediaType>();
//		mediaTypes.add(MediaType.valueOf(acceptHeaderValue));
//		requestHeaders.setAccept(mediaTypes);
//		requestHeaders.setContentType(MediaType.valueOf(acceptHeaderValue));
//		HttpMethod put = HttpMethod.PUT;
//
//		String body = "{\"Nombre\":\"nombrePruebaModificado\"}";
//		HttpEntity<String> entity = new HttpEntity<String>(body, requestHeaders);
//
//		ResponseEntity<String> response = restTemplate.exchange(url, put, entity, String.class);
//		assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
//		int count = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM EMPRESA WHERE NOMBRE = 'nombrePruebaModificado'");
//		assertTrue(count == 1);
//		jdbcTemplate.execute("DELETE FROM EMPRESA WHERE NIF = 'nifPrueba'");
//	}
		
	@After
	public void tearDown() throws Exception {
		dataSource = null;
		dirFrontEnd = null;
		restTemplate = null;
		jdbcTemplate.execute("DELETE FROM EMPRESA WHERE NIF='nifPrueba'");
	}

}
