
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceimpl.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import es.microforum.model.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, String> {

	public Empleado findByDni(String dni);
	
	public List<Empleado> findByNombre(String nombre);	
	
}
