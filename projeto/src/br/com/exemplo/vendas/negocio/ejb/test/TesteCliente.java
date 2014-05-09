package br.com.exemplo.vendas.negocio.ejb.test;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ClienteInterface;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesteCliente {

	/**
	 * Inserir Cliente.java
	 * TBL_CLIENTE
	 * 
	 * 1
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main1(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteInterface remoteCliente = (ClienteInterface) ctx.lookup("ClienteBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ClienteVO vo = new ClienteVO("alberto1", "alberto123", "Fiap", true, new Date(), new Long(1), "Alberto Cerqueira", "Diadema", 12345678, "Sei la");
		requestDTO.set("clienteVO", vo);
		responseDTO = remoteCliente.inserirCliente(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Grava\u00e7\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Buscar Cliente.java por login
	 * 
	 * 2
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main2(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteInterface remoteCliente = (ClienteInterface) ctx.lookup("ClienteBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteCliente.getCliente(requestDTO, "alberto");
		ClienteVO clienteVO = (ClienteVO) responseDTO.get("getCliente");
		System.out.println(clienteVO);
	}
	
	/**
	 * Listas todos Cliente.java
	 * 
	 * 3
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main3(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteInterface remoteCliente = (ClienteInterface) ctx.lookup("ClienteBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteCliente.selecionarTodosClientes(requestDTO);
		ClienteVO[] lista = (ClienteVO[]) responseDTO.get("listaCliente");
		if(lista != null){
			for(int i = 0; i < lista.length; i++){
				ClienteVO clienteVO = (ClienteVO) lista[i];
				System.out.println(clienteVO);
			}
		}
	}
	
	/**
	 * Excluir Cliente.java
	 * TBL_CLIENTE
	 * 
	 * 4
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main4(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteInterface remoteCliente = (ClienteInterface) ctx.lookup("ClienteBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("loginCliente", "alberto");
		responseDTO = remoteCliente.excluirClientePorLogin(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Exclus\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a exclus\u00e3o.");
		}
	}

	/**
	 * Alterar Cliente.java
	 * TBL_CLIENTE
	 * 
	 * 5
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteInterface remoteCliente = (ClienteInterface) ctx.lookup("ClienteBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ClienteVO vo = new ClienteVO("alberto", "alberto123567", "Fiap - Altera", true, new Date(), new Long(1), "Alberto Cerqueira", "Diadema", 12345678, "Sei la - Alterar");
		requestDTO.set("clienteVO", vo);
		responseDTO = remoteCliente.alterarCliente(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Altera\u00e7\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a altera\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Teste webservices: Localizar cliente que tenham feito compras.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void mainw(String[] args) throws Exception {
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
	}
}