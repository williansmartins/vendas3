package br.com.exemplo.vendas.negocio.ejb.client;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ClienteJuridicoInterface;
import br.com.exemplo.vendas.negocio.model.vo.ClienteJuridicoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesteClienteJuridico {

	/**
	 * Inserir ClienteJuridico.java
	 * TBL_CLIENTE_JURIDICO
	 * 
	 * 1
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteJuridicoInterface remoteClienteJuridico = (ClienteJuridicoInterface) ctx.lookup("ClienteJuridicoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ClienteJuridicoVO vo = new ClienteJuridicoVO("albertojuridico", "alberto123", "Fiap", "Legal", true, new Date(), new Long(1), "Alberto Cerqueira", "Diadema", 12345678, "Sei la", "123123133213", "Rua da Empresa", "002");
		requestDTO.set("clienteJuridicoVO", vo);
		responseDTO = remoteClienteJuridico.inserirClienteJuridico(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Grava\u00e7\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Buscar ClienteJuridico.java por login
	 * 
	 * 2
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main2(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteJuridicoInterface remoteClienteJuridico = (ClienteJuridicoInterface) ctx.lookup("ClienteJuridicoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteClienteJuridico.getClienteJuridico(requestDTO, "alberto");
		ClienteJuridicoVO clienteJuridicoVO = (ClienteJuridicoVO) responseDTO.get("getClienteJuridico");
		System.out.println(clienteJuridicoVO);
	}
	
	/**
	 * Listas todos ClienteJuridico.java
	 * 
	 * 3
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main3(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteJuridicoInterface remoteClienteJuridico = (ClienteJuridicoInterface) ctx.lookup("ClienteJuridicoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteClienteJuridico.selecionarTodosClientesJuridicos(requestDTO);
		ClienteJuridicoVO[] lista = (ClienteJuridicoVO[]) responseDTO.get("listaClienteJuridico");
		if(lista != null){
			for(int i = 0; i < lista.length; i++){
				ClienteJuridicoVO clienteJuridicoVO = (ClienteJuridicoVO) lista[i];
				System.out.println(clienteJuridicoVO);
			}
		}
	}
	
	/**
	 * Excluir ClienteJuridico.java
	 * TBL_CLIENTE_JURIDICO
	 * 
	 * 4
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main4(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteJuridicoInterface remoteClienteJuridico = (ClienteJuridicoInterface) ctx.lookup("ClienteJuridicoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("loginClienteJuridico", "alberto");
		responseDTO = remoteClienteJuridico.excluirClienteJuridicoPorLogin(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Exclus\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a exclus\u00e3o.");
		}
	}
}