package br.com.exemplo.vendas.negocio.interfaces ;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface UsuarioInterface {
	
	public ServiceDTO inserirUsuario(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarUsuario(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirUsuario(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosUsuario(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getUsuario(ServiceDTO requestDTO, String login) throws LayerException, RemoteException;
}
