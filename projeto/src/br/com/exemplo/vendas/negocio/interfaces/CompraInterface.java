package br.com.exemplo.vendas.negocio.interfaces;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface CompraInterface {

	public ServiceDTO inserirCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getCompra(ServiceDTO requestDTO, Integer numero) throws LayerException, RemoteException;
}