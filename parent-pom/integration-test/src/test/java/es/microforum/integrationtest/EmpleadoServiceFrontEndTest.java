package es.microforum.integrationtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import es.microforum.model.Empleado;


public class EmpleadoServiceFrontEndTest {

	RestTemplate restTemplate = new RestTemplate();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void getTest() {
		try {
			Resource<Empleado> resource = getEmpleado(new URI("http://localhost:8080/service-frontend/empleado/12345"));
			assertTrue(resource.getContent().getNombre().equals("Javier"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	private Resource<Empleado> getEmpleado(URI uri) {
		return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Empleado>>() {
		}).getBody();
	}	

}
