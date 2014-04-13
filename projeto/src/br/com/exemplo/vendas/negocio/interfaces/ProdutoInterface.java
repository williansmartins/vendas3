package br.com.exemplo.vendas.negocio.interfaces ;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface ProdutoInterface {
	
	public ServiceDTO inserirProduto(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarProduto(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirProduto(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirProdutoPorCodigo(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosProdutos(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getProduto(ServiceDTO requestDTO, Long codigo) throws LayerException, RemoteException;
	public ServiceDTO localizarProdutosPorQuantidadeAcimaDeEPrecoAbaixoDe(ServiceDTO requestDTO, BigDecimal preco, Integer quantidadeEstoque) throws LayerException, RemoteException;
}