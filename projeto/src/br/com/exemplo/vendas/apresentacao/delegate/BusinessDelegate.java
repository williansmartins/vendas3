package br.com.exemplo.vendas.apresentacao.delegate ;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.negocio.interfaces.ClienteInterface;
import br.com.exemplo.vendas.negocio.interfaces.CompraInterface;
import br.com.exemplo.vendas.negocio.interfaces.ProdutoInterface;
import br.com.exemplo.vendas.negocio.interfaces.RecebeRequisicaoInterface;
import br.com.exemplo.vendas.negocio.interfaces.UsuarioInterface;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;
import br.com.exemplo.vendas.util.exception.SysExceptionFactory;
import br.com.exemplo.vendas.util.locator.ServiceLocator;
import br.com.exemplo.vendas.util.locator.ServiceLocatorException;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory;

public class BusinessDelegate {

	private static BusinessDelegate instance = null;
	private ServiceLocator serviceLocator;

	private BusinessDelegate() throws Exception {
		setServiceLocator();
	}

	public synchronized static BusinessDelegate getInstance() throws LayerException {
		if(instance == null){
			try{
				instance = new BusinessDelegate();
			}catch(Exception exception){
				throw SysExceptionFactory.getException(exception);
			}
		}
		return instance;
	}

	public ServiceDTO inserirUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((UsuarioInterface)serviceLocator.getService("UsuarioBean/remote")).inserirUsuario(requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException( serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO excluirUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((UsuarioInterface)serviceLocator.getService("UsuarioBean/remote")).excluirUsuario( requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO alterarUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((UsuarioInterface)serviceLocator.getService("UsuarioBean/remote")).alterarUsuario( requestDTO ) ;
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO ;
	}

	public ServiceDTO selectionarTodosUsuarios(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((UsuarioInterface)serviceLocator.getService("UsuarioBean/remote")).selecionarTodosUsuarios(requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO listarClientes(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((ClienteInterface)serviceLocator.getService("ClienteBean/remote")).selecionarTodosClientes(requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}
	
	public ServiceDTO buscarUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((UsuarioInterface)serviceLocator.getService("UsuarioBean/remote")).localizarPorLogin( requestDTO, "");
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO ;
	}



	public ServiceDTO inserirProduto(ServiceDTO requestDTO)
			throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).inserirProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO excluirProduto(ServiceDTO requestDTO)
			throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).excluirProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO alterarProduto(ServiceDTO requestDTO)
			throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).alterarProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO selectionarTodosProdutos(ServiceDTO requestDTO)
			throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).selecionarTodosProdutos(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}

		return responseDTO;
	}

	public ServiceDTO getProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((ProdutoInterface)serviceLocator.getService("UsuarioBean/remote")).getProduto(requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO ;
	}

	public ServiceDTO inserirCompra(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).inserirProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO excluirCompra(ServiceDTO requestDTO)
			throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).excluirProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO excluirCompraPorNumero(ServiceDTO requestDTO) throws LayerException, RemoteException{
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((CompraInterface) serviceLocator.getService("CompraBean/remote")).excluirCompraPorNumero(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}

		return responseDTO;
	}

	public ServiceDTO alterarCompra(ServiceDTO requestDTO)
			throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).alterarProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO getCompra(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((CompraInterface) serviceLocator.getService("CompraBean/remote")).getCompra(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO selectionarTodasCompras(ServiceDTO requestDTO)
			throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((CompraInterface) serviceLocator.getService("CompraBean/remote")).selecionarTodosCompras(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO localizarComprasPorValorAbaixoDe500(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((CompraInterface)serviceLocator.getService("CompraBean/remote")).localizarComprasPorValorAbaixoDe(requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}
	
	public ServiceDTO localizarClientesComComprasRealizadas(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((ClienteInterface)serviceLocator.getService("ClienteBean/remote")).localizarClientesPorCompra(requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}
	
	public ServiceDTO localizarProdutosPorQuantidadeAcimaDe2UnidadesEPrecoAbaixoDe1000(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((ProdutoInterface)serviceLocator.getService("ProdutoBean/remote")).localizarProdutosPorQuantidadeAcimaDeEPrecoAbaixoDe(requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO inserirQueue(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			responseDTO = ((RecebeRequisicaoInterface)serviceLocator.getService("RecebeRequisicaoBean/remote")).inserirFila(requestDTO);
		}catch(RemoteException remoteException){
			throw SysExceptionFactory.getException(remoteException);
		}catch(ServiceLocatorException serviceLocatorException){
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	private void setServiceLocator() throws Exception {
		this.serviceLocator = ServiceLocatorFactory.getServiceLocator("serviceLocator");
	}
}