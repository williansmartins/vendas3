package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ReservaInterface;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesteReserva {

	/**
	 * Inserir Reserva.java
	 * TBL_RESERVA
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main1(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ReservaInterface remoteReserva = (ReservaInterface) ctx.lookup("ReservaBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ReservaVO vo = new ReservaVO(new Long(1), new Date(), "fidalgo", "aberto", new BigDecimal(100), "alberto");
		requestDTO.set("reservaVO", vo);
		responseDTO = remoteReserva.inserirReserva(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			responseDTO = remoteReserva.selecionarTodosReserva(requestDTO);
			ReservaVO[] lista = (ReservaVO[]) responseDTO.get("listaReserva");
			if(lista != null){
				for(int i = 0; i < lista.length; i++){
					ReservaVO reservaVO = (ReservaVO) lista[i];
					System.out.println(reservaVO);
				}
			}
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Buscar Reserva.java por codigo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main2(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ReservaInterface remoteReserva = (ReservaInterface) ctx.lookup("ReservaBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteReserva.getReserva(requestDTO, new Long(1));
		ReservaVO reservaVO = (ReservaVO) responseDTO.get("getReserva");
		System.out.println(reservaVO);
	}
	
	/**
	 * Listas todas Reserva.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main3(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ReservaInterface remoteReserva = (ReservaInterface) ctx.lookup("ReservaBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteReserva.selecionarTodosReserva(requestDTO);
		ReservaVO[] lista = (ReservaVO[]) responseDTO.get("listaReserva");
		if(lista != null){
			for(int i = 0; i < lista.length; i++){
				ReservaVO reservaVO = (ReservaVO) lista[i];
				System.out.println(reservaVO);
			}
		}
	}
}