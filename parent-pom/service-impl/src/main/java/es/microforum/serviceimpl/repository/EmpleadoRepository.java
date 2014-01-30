
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceimpl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import es.microforum.model.Empleado;

public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, String> {

	public Empleado findByDni(String dni);
	
	public Page<Empleado> findByNombre(Pageable pageable,String nombre);	
	
}
