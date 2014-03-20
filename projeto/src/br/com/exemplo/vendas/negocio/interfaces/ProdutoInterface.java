package br.com.exemplo.vendas.negocio.interfaces ;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface ProdutoInterface {
	
	public ServiceDTO inserirProduto(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarProduto(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirProduto(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosProduto(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getProduto(ServiceDTO requestDTO, Long codigo) throws LayerException, RemoteException;
}