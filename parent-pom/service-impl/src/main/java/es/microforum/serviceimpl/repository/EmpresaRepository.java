package es.microforum.serviceimpl.repository;

import org.springframework.data.repository.CrudRepository;

import es.microforum.model.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, String> {

	public Empresa findByNif(String nif);	
	
	//@Query("select e from Empleado e where e.nombre like ")
	//public List<Empleado> findByEmpleados();
	
}
