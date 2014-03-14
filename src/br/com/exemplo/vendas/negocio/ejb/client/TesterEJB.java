/**************************************************
 * Sistema de Vendas
 * Trabalho de avaliacao final da
 * disciplina Tecnologia Webservices e Restful
 * FIAP Turma 18SCJ
 * Artur Diniz Samora   -   RM 42934
 * Jose Roberto Salinas -   RM 42937
 ***************************************************/

package br.com.exemplo.vendas.negocio.ejb.client ;

import java.util.Date ;
import java.util.Hashtable ;

import javax.naming.Context ;
import javax.naming.InitialContext ;

import br.com.exemplo.vendas.negocio.interfaces.UsuarioInterface ;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO ;
import br.com.exemplo.vendas.util.dto.ServiceDTO ;
import br.com.exemplo.vendas.util.locator.ServiceLocator ;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory ;

public class TesterEJB
{

	public static void main( String[ ] args ) throws Exception
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
		UsuarioVO vo = new UsuarioVO( "marcao1", "senha1111", "grupo1111", "perfil1111", "S",
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
	}

}
