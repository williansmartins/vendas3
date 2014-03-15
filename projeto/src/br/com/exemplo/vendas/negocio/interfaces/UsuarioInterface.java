package br.com.exemplo.vendas.negocio.interfaces ;

public interface UsuarioInterface
{
	public br.com.exemplo.vendas.util.dto.ServiceDTO inserirUsuario(
			br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO )
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException ;

	public br.com.exemplo.vendas.util.dto.ServiceDTO alterarUsuario(
			br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO )
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException ;

	public br.com.exemplo.vendas.util.dto.ServiceDTO excluirUsuario(
			br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO )
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException ;

	public br.com.exemplo.vendas.util.dto.ServiceDTO selecionarTodosUsuario(
			br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO )
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException ;

	public br.com.exemplo.vendas.util.dto.ServiceDTO getUsuario(
			br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO, String login )
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException ;
}
