package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.CompraInterface;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesteCompra {

	/**
	 * Inserir Compra.java
	 * TBL_COMPRA
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main1(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		CompraInterface remoteCompra = (CompraInterface) ctx.lookup("CompraBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		CompraVO vo = new CompraVO(new Long(1), new Date(), "aberto", new BigDecimal(100), new Long(1), "alberto");
		requestDTO.set("compraVO", vo);
		responseDTO = remoteCompra.inserirCompra(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Grava\u00e7\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Buscar Compra.java por numero
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main2(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		CompraInterface remoteCompra = (CompraInterface) ctx.lookup("CompraBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteCompra.getCompra(requestDTO, new Long(1));
		CompraVO compraVO = (CompraVO) responseDTO.get("getCompra");
		System.out.println(compraVO);
	}
	
	/**
	 * Listas todas Compra.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main3(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		CompraInterface remoteCompra = (CompraInterface) ctx.lookup("CompraBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteCompra.selecionarTodosCompras(requestDTO);
		CompraVO[] lista = (CompraVO[]) responseDTO.get("listaCompra");
		if(lista != null){
			for(int i = 0; i < lista.length; i++){
				CompraVO compraVO = (CompraVO) lista[i];
				System.out.println(compraVO);
			}
		}
	}
}