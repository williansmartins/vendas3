package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import java.rmi.RemoteException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.NamingException;

import br.com.exemplo.vendas.apresentacao.service.Service;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.util.exception.LayerException;

@WebService
public class LocalizarClientes {

	@WebMethod
	public ClienteVO[] localizarClientesComComprasRealizadas() throws NamingException, RemoteException, LayerException {
		Service service = new Service();
		return service.localizarClientesComComprasRealizadas();
	}
}