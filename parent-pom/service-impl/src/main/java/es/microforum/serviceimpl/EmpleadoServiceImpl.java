
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceimpl.repository.EmpleadoRepository;

@Service("springJpaEmpleadoService")
@Repository
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	public List<Empleado> findAll() {
		return Lists.newArrayList(empleadoRepository.findAll());
	}	
		
	public Empleado findByDni(String dni) {
		return empleadoRepository.findByDni(dni);
	}
	
	public Page<Empleado> findByNombre(Pageable pageable, String nombre) {
		return empleadoRepository.findByNombre(pageable,nombre);
	}

	public Empleado save(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	public void delete(Empleado empleado) {
		empleadoRepository.delete(empleado);		
	}
	
}
