
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

import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpresaService;
import es.microforum.serviceimpl.repository.EmpresaRepository;

@Service("springJpaEmpresaService")
@Repository
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public List<Empresa> findAll() {
		return Lists.newArrayList(empresaRepository.findAll());
	}	

	public Empresa findByNif(String nif) {
		return empresaRepository.findByNif(nif);
	}

	public Empresa save(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	public void delete(Empresa empresa) {
		empresaRepository.delete(empresa);		
	}
	
	public Page<Empresa> findByNombre(String nombre, Pageable pageable) {
		return empresaRepository.findByNombre(pageable, nombre);
	}
}
