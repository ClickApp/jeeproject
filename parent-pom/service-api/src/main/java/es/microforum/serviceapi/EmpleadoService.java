
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import es.microforum.model.Empleado;

public interface EmpleadoService {

	public abstract Page<Empleado> findAll(Pageable pageable); // Obtener listado con todos los empleados
	public abstract Empleado findByDni(String dni); // Obtener un empleado por su dni
	public abstract Empleado findByDniEmpresa(String dni); //Obtener un empleado por su dni que tiene empresa
	public abstract Page<Empleado> findByNombre(Pageable pageable, String nombre); // Obtener empleados por nombre
	public abstract Empleado save(Empleado empleado); // Agregar, modificar un empleado
	public abstract void delete(Empleado empleado);	// Eliminar un empleado	
	public abstract void actualizarSalario(Double porcentaje); //Actualiza el salario anual de los empleados en funcion de un porcentaje
}
