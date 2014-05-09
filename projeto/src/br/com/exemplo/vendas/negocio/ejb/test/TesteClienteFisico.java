package br.com.exemplo.vendas.negocio.ejb.test;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ClienteFisicoInterface;
import br.com.exemplo.vendas.negocio.model.vo.ClienteFisicoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesteClienteFisico {

	/**
	 * Inserir ClienteFisico.java
	 * TBL_CLIENTE_FISICO
	 * 
	 * 1
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteFisicoInterface remoteClienteFisico = (ClienteFisicoInterface) ctx.lookup("ClienteFisicoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ClienteFisicoVO vo = new ClienteFisicoVO("albertofisico", "alberto123", "Fiap", "Legal", true, new Date(), new Long(1), "Alberto Cerqueira", "Diadema", 12345678, "Sei la", "123123133213", "31231313123");
		requestDTO.set("clienteFisicoVO", vo);
		responseDTO = remoteClienteFisico.inserirClienteFisico(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Grava\u00e7\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Buscar ClienteFisico.java por login
	 * 
	 * 2
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main2(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteFisicoInterface remoteClienteFisico = (ClienteFisicoInterface) ctx.lookup("ClienteFisicoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteClienteFisico.getClienteFisico(requestDTO, "alberto");
		ClienteFisicoVO clienteFisicoVO = (ClienteFisicoVO) responseDTO.get("getClienteFisico");
		System.out.println(clienteFisicoVO);
	}
	
	/**
	 * Listas todos ClienteFisico.java
	 * 
	 * 3
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main3(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteFisicoInterface remoteClienteFisico = (ClienteFisicoInterface) ctx.lookup("ClienteFisicoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteClienteFisico.selecionarTodosClientesFisicos(requestDTO);
		ClienteFisicoVO[] lista = (ClienteFisicoVO[]) responseDTO.get("listaClienteFisico");
		if(lista != null){
			for(int i = 0; i < lista.length; i++){
				ClienteFisicoVO clienteFisicoVO = (ClienteFisicoVO) lista[i];
				System.out.println(clienteFisicoVO);
			}
		}
	}
	
	/**
	 * Excluir ClienteFisico.java
	 * TBL_CLIENTE_FISICO
	 * 
	 * 4
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main4(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ClienteFisicoInterface remoteClienteFisico = (ClienteFisicoInterface) ctx.lookup("ClienteFisicoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("loginClienteFisico", "alberto");
		responseDTO = remoteClienteFisico.excluirClienteFisicoPorLogin(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Exclus\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a exclus\u00e3o.");
		}
	}
}