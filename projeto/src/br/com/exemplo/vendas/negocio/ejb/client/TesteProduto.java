package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigDecimal;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ProdutoInterface;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesteProduto {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ProdutoVO vo = new ProdutoVO(new Long(2), "Produto teste", new BigDecimal(100), "Sei la ");
		requestDTO.set("produtoVO", vo);
		responseDTO = remoteProduto.inserirProduto(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			responseDTO = remoteProduto.selecionarTodosProduto(requestDTO);
			ProdutoVO[] lista = (ProdutoVO[]) responseDTO.get("listaProduto");
			if(lista != null){
				for(int i = 0; i < lista.length; i++){
					ProdutoVO produtoVO = (ProdutoVO) lista[i];
					System.out.println(produtoVO);
				}
			}
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
		}
	}
}