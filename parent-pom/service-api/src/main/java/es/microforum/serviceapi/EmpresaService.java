
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceapi;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import es.microforum.model.Empresa;

public interface EmpresaService {

	public abstract List<Empresa> findAll(); // Obtener listado con todos las empresas
	public abstract Page<Empresa> findAll(Pageable pageable); // Obtener listado con todos las empresas paginado
	public abstract Empresa findByNif(String nif); // Obtener una empresa por su nif	
	public abstract Empresa save(Empresa empresa); // Agregar, modificar un empresa
	public abstract void delete(Empresa empresa); // Eliminar una empresa		
	public abstract Page<Empresa> findByNombre(String nombre, Pageable pageable); // Obtener listado de empresas por nombre
}
