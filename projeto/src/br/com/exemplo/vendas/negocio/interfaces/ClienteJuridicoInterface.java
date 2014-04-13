package br.com.exemplo.vendas.negocio.interfaces ;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface ClienteJuridicoInterface {
	
	public ServiceDTO inserirClienteJuridico(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarClienteJuridico(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirClienteJuridico(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirClienteJuridicoPorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosClientesJuridicos(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getClienteJuridico(ServiceDTO requestDTO, String login) throws LayerException, RemoteException;
}