
/*
 * @author Javier Urosa Domingo
 */

package es.microforum.serviceimpl.repository;

import org.springframework.data.repository.CrudRepository;

import es.microforum.model.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, String> {

	public Empresa findByNif(String nif);	
	
}
