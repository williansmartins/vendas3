package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.exemplo.vendas.negocio.interfaces.ClienteInterface;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@WebService
public class LocalizarClientes {

	@WebMethod
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ClienteVO[] localizarClientesComComprasRealizadas() throws NamingException, RemoteException, LayerException {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteInterface remoteCliente = (ClienteInterface) ctx.lookup("ClienteBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("loginCliente", "alberto");
		responseDTO = remoteCliente.localizarClientesPorCompra(requestDTO);
		ClienteVO[] clienteVOs = (ClienteVO[]) responseDTO.get("clientesPorCompra");
		if(clienteVOs != null){
			for(int i = 0; i < clienteVOs.length; i++){
				ClienteVO clienteVO = (ClienteVO) clienteVOs[i];
				System.out.println(clienteVO);
			}
		}
		return clienteVOs;
	}
}