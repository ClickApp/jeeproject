
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
import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceimpl.repository.EmpleadoRepository;
import es.microforum.serviceimpl.repository.EmpresaRepository;

@Service("springJpaEmpleadoService")
@Repository
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Page<Empleado> findAll(Pageable pageable) {
		return empleadoRepository.findAll(pageable);
	}	
		
	public Empleado findByDni(String dni) {
		return empleadoRepository.findOne(dni);
	}		

	public Empleado findByDniEmpresa(String dni) {
		Empleado empleado = empleadoRepository.findOne(dni);
		Empresa empresa = empleado.getEmpresa();
		empresa.getEmpleados().size();
		return empleado;
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

	@Override
	public void actualizarSalario(double porcentaje) {		
		List<Empleado> empleados = Lists.newArrayList(empleadoRepository.findAll());
		for(Empleado empleado : empleados){
			empleado.setSalarioAnual(((empleado.getSalarioAnual() * porcentaje) / 100) + empleado.getSalarioAnual());
			save(empleado);
		}	
	}
	
}
