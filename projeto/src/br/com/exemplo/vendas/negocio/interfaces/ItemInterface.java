package br.com.exemplo.vendas.negocio.interfaces;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface ItemInterface {

	public ServiceDTO inserirItem(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarItem(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirItem(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosItem(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getItem(ServiceDTO requestDTO, Long codigo) throws LayerException, RemoteException;
}