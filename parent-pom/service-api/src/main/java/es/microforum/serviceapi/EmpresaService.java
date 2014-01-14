package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;

public interface EmpresaService {

	// Find all empresas
	public List<Empresa> findAll();

	// Find a empresa by nif
	public Empresa findByNif(String nif);

	// Find Empleados 
	public List<Empleado> findEmpleados(String nif);	
	
	// Insert or update a empresa
	public Empresa save(Empresa empresa);

	// Delete a empresa
	public void delete(Empresa empresa);	
	
}