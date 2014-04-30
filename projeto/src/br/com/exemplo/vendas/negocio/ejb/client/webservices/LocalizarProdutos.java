package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import java.rmi.RemoteException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.NamingException;

import br.com.exemplo.vendas.apresentacao.service.Service;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.exception.LayerException;

@WebService
public class LocalizarProdutos {

	@WebMethod
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProdutoVO[] localizarProdutosPorQuantidadeAcimaDe2UnidadesEPrecoAbaixoDe1000() throws NamingException, RemoteException, LayerException {
		/*
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("preco", new BigDecimal(1000));
		requestDTO.set("quantidadeEstoque", 2);
		responseDTO = remoteProduto.localizarProdutosPorQuantidadeAcimaDeEPrecoAbaixoDe(requestDTO);
		ProdutoVO[] produtoVOs = (ProdutoVO[]) responseDTO.get("produtosPorQuantidadeAcimaDeEPrecoAbaixoDe");
		if(produtoVOs != null){
			for(int i = 0; i < produtoVOs.length; i++){
				ProdutoVO produtoVO = (ProdutoVO) produtoVOs[i];
				System.out.println(produtoVO);
			}
		}
		*/
		Service service = new Service();
		return service.localizarProdutosPorQuantidadeAcimaDe2UnidadesEPrecoAbaixoDe1000();
	}
}