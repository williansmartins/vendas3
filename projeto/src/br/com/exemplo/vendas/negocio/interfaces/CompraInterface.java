package br.com.exemplo.vendas.negocio.interfaces;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface CompraInterface {

	public ServiceDTO inserirCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirCompraPorNumero(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosCompras(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO localizarComprasPorValorAbaixoDe(ServiceDTO requestDTO) throws LayerException, RemoteException;
}