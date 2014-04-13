package br.com.exemplo.vendas.negocio.interfaces ;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface ClienteInterface {
	
	public ServiceDTO inserirCliente(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarCliente(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirCliente(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirClientePorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosClientes(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getCliente(ServiceDTO requestDTO, String login) throws LayerException, RemoteException;
	public ServiceDTO localizarClientesPorCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
}