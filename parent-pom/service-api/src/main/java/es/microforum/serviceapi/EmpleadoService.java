
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceapi;

import java.util.List;
import es.microforum.model.Empleado;

public interface EmpleadoService {

	public List<Empleado> findAll(); // Obtener listado con todos los empleados
	public Empleado findByDni(String dni); // Obtener un empleado por su dni
	public List<Empleado> findByNombre(String nombre); // Obtener empleados por nombre
	public Empleado save(Empleado empleado); // Agregar, modificar un empleado
	public void delete(Empleado empleado);	// Eliminar un empleado	
	
}
