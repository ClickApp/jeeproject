package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpresaService;
import es.microforum.serviceimpl.repository.EmpresaRepository;

@Service("springJpaEmpresaService")
@Repository
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Transactional(readOnly=true)
	public List<Empresa> findAll() {
		return Lists.newArrayList(empresaRepository.findAll());
	}	

	@Override
	public Empresa findByNif(String nif) {
		return empresaRepository.findByNif(nif);
	}

	@Override
	public List<Empleado> findEmpleados() {
		return empresaRepository.findByEmpleados();
	}

	@Override
	public Empresa save(Empresa empresa) {
		return null;
	}

	@Override
	public void delete(Empresa empresa) {
				
	}
}
