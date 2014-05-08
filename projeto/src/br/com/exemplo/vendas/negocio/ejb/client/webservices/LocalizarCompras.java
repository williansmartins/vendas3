package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import java.rmi.RemoteException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.NamingException;

import br.com.exemplo.vendas.apresentacao.service.Service;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.exception.LayerException;

@WebService
public class LocalizarCompras {

	@WebMethod
	public CompraVO[] localizarComprasPorValorAbaixoDe500() throws NamingException, RemoteException, LayerException {
		Service service = new Service();
		return service.localizarComprasPorValorAbaixoDe500();
	}
}