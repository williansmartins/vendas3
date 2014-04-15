package br.com.exemplo.vendas.negocio.interfaces;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface ItemInterface {

	public ServiceDTO inserirItem(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarItem(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirItem(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirItemPorId(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirItemPorReservaCompraProduto(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosItens(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getItem(ServiceDTO requestDTO, Long codigoReserva, Long numeroCompra, Long codigoProduto) throws LayerException, RemoteException;
}