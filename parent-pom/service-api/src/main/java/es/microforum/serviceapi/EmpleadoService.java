package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empleado;

public interface EmpleadoService {

	// Find all empleados
	public List<Empleado> findAll();
	
	// Find a empleado with details by dni
	public Empleado findByDni(String dni);
	
	// Find empleados by nombre
	public List<Empleado> findByNombre(String nombre);
	
	// Insert or update Empleado
	public Empleado save(Empleado empleado);

	// Delete a Empleado
	public void delete(Empleado empleado);		
	
}
