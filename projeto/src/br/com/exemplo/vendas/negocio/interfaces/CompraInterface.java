package br.com.exemplo.vendas.negocio.interfaces;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface CompraInterface {

	public ServiceDTO inserirCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirCompra(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirCompraPorNumero(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosCompras(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getCompra(ServiceDTO requestDTO, Long numero) throws LayerException, RemoteException;
	public ServiceDTO localizarComprasPorValorAbaixoDe(ServiceDTO requestDTO, BigDecimal valor) throws LayerException, RemoteException;
}