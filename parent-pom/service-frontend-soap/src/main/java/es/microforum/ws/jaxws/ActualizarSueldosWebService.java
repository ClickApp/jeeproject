package es.microforum.ws.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import es.microforum.serviceapi.EmpleadoService;

@WebService
public class ActualizarSueldosWebService implements IActualizarSueldosWebService{
	
	private EmpleadoService empleadoService;
	
	@WebMethod(exclude=true)
	public void setActualizarSueldos(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}
	
	@Override
	public void actualizarSueldos(double porcentaje) {
		empleadoService.actualizarSalario(porcentaje);
	}

}
