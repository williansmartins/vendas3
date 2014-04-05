package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigDecimal;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ItemInterface;
import br.com.exemplo.vendas.negocio.model.vo.ItemVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesteItem {

	/**
	 * Inserir Item.java
	 * TBL_ITEM
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main1(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ItemInterface remoteItem = (ItemInterface) ctx.lookup("ItemBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ItemVO vo = new ItemVO(1000, new BigDecimal(100), "aberto",  new Long(1), new Long(1), new Long(2));
		requestDTO.set("itemVO", vo);
		responseDTO = remoteItem.inserirItem(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Grava\u00e7\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Buscar Item.java por numero
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main2(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ItemInterface remoteItem = (ItemInterface) ctx.lookup("ItemBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteItem.getItem(requestDTO, new Long(1), new Long(1), new Long(2));
		ItemVO itemVO = (ItemVO) responseDTO.get("getItem");
		System.out.println(itemVO);
	}
	
	/**
	 * Listas todos Item.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main3(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ItemInterface remoteItem = (ItemInterface) ctx.lookup("ItemBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteItem.selecionarTodosItens(requestDTO);
		ItemVO[] lista = (ItemVO[]) responseDTO.get("listaItem");
		if(lista != null){
			for(int i = 0; i < lista.length; i++){
				ItemVO itemVO = (ItemVO) lista[i];
				System.out.println(itemVO);
			}
		}
	}
}