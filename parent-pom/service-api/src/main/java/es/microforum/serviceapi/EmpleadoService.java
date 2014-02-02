
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceapi;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import es.microforum.model.Empleado;

public interface EmpleadoService {

	public Page<Empleado> findAll(Pageable pageable); // Obtener listado con todos los empleados
	public Empleado findByDni(String dni); // Obtener un empleado por su dni
	public Page<Empleado> findByNombre(Pageable pageable, String nombre); // Obtener empleados por nombre
	public Empleado save(Empleado empleado); // Agregar, modificar un empleado
	public void delete(Empleado empleado);	// Eliminar un empleado	
	
}
