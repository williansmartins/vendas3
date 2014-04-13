package br.com.exemplo.vendas.negocio.interfaces ;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface ClienteFisicoInterface {
	
	public ServiceDTO inserirClienteFisico(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarClienteFisico(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirClienteFisico(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirClienteFisicoPorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosClientesFisicos(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getClienteFisico(ServiceDTO requestDTO, String login) throws LayerException, RemoteException;
}