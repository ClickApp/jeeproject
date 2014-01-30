
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceimpl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.microforum.model.Empresa;

public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, String> {

	public Empresa findByNif(String nif);	
	
	public Page<Empresa> findByNombre(Pageable pageable, String nombre);
	
}
