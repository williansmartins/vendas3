package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.exemplo.vendas.negocio.interfaces.CompraInterface;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@WebService
public class LocalizarCompras {

	@WebMethod
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CompraVO[] localizarComprasPorValorAbaixoDe500() throws NamingException, RemoteException, LayerException {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		CompraInterface remoteCompra = (CompraInterface) ctx.lookup("CompraBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("comprasPorValorAbaixoDe500", new BigDecimal(500));//Valor da compra limitado em R$ 500.
		responseDTO = remoteCompra.localizarComprasPorValorAbaixoDe(requestDTO);
		CompraVO[] compraVOs = (CompraVO[]) responseDTO.get("compraVOs");
		if(compraVOs != null){
			for(int i = 0; i < compraVOs.length; i++){
				CompraVO compraVO = (CompraVO) compraVOs[i];
				System.out.println(compraVO);
			}
		}
		return compraVOs;
	}
}