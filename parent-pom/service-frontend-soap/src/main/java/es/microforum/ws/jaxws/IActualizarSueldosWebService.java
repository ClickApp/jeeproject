package es.microforum.ws.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IActualizarSueldosWebService {
	@WebMethod(operationName="actualizarSueldos")
	void actualizarSueldos(double porcentaje);
}
