package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empresa;

public interface EmpresaService {

	public List<Empresa> findAll(); // Obtener listado con todos las empresas
	public Empresa findByNif(String nif); // Obtener una empresa por su nif	
	public Empresa save(Empresa empresa); // Agregar, modificar un empresa
	public void delete(Empresa empresa); // Eliminar una empresa		

}
