package br.com.exemplo.vendas.tests;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.exemplo.vendas.negocio.interfaces.UsuarioInterface;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;
import br.com.exemplo.vendas.util.locator.ServiceLocator;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory;

public class ListarUsuarios2
{
    public static void main( String[] args )
    {
	try
	{
	    Hashtable prop = new Hashtable( ) ;
		prop.put( InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" ) ;
		prop.put( InitialContext.PROVIDER_URL, "jnp://localhost:1099" ) ;

		Context ctx = new InitialContext( prop ) ;

		UsuarioInterface remoteUsuario = ( UsuarioInterface ) ctx.lookup( "UsuarioBean/remote" ) ;
		// ProdutoInterface remoteProduto = (ProdutoInterface)
		// ctx.lookup("ProdutoBean/remote");

		ServiceLocator serviceLocator = ServiceLocatorFactory.getServiceLocator( "serviceLocator" ) ;
		ServiceDTO requestDTO = new ServiceDTO( ) ;
		ServiceDTO responseDTO = new ServiceDTO( ) ;

		/**
		 * Inserir usuario
		 */
		UsuarioVO vo = new UsuarioVO( "marcao2", "senha1112", "grupo1111", "perfil1111", "S",
				new Date( ) ) ;
		requestDTO.set( "usuarioVO", vo ) ;
		responseDTO = remoteUsuario.inserirUsuario( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get( "resposta" ) ;

		/**
		 * Consultar usuario
		 */

		responseDTO = remoteUsuario.selecionarTodosUsuario( requestDTO ) ;
		UsuarioVO[ ] lista = ( UsuarioVO[ ] ) responseDTO.get( "listaUsuario" ) ;
		if (lista != null)
		{
			for (int i = 0; i < lista.length; i++)
			{
				UsuarioVO usuarioVO = ( UsuarioVO ) lista[ i ] ;
				System.out.println( usuarioVO ) ;
			}
		}

	} catch ( LayerException | NamingException | RemoteException e )
	{
	    System.out.println( "ERRO:" + e.getMessage() );
	}
    }
}
