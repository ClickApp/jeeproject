
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceapi;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import es.microforum.model.Empresa;

public interface EmpresaService {

	public List<Empresa> findAll(); // Obtener listado con todos las empresas
	public Empresa findByNif(String nif); // Obtener una empresa por su nif	
	public Empresa save(Empresa empresa); // Agregar, modificar un empresa
	public void delete(Empresa empresa); // Eliminar una empresa		
	public abstract Page<Empresa> findByNombre(String nombre, Pageable pageable); // Obtener listado de empresas por nombre
}
