package br.com.exemplo.vendas.apresentacao.web;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.exemplo.vendas.negocio.interfaces.UsuarioInterface;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.Redirecionador;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;
import br.com.exemplo.vendas.util.locator.ServiceLocator;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory;

@SuppressWarnings("serial")
@ManagedBean(name = "usuario_controller")
@SessionScoped
public class UsuarioController implements Serializable
{

    private UsuarioVO vo;
    private List<UsuarioVO> lista;

    public UsuarioController() throws Exception
    {
	vo = new UsuarioVO();
	System.out.println( "Controller de Usuário" );
    }

    public void insertView( ) throws Exception
    {
	new Redirecionador().redirecionar( "inserir-usuario.xhtml" );
    }

    // GETTERS AND SETTERS

    public UsuarioVO getVo( )
    {
        return vo;
    }

    public void setVo( UsuarioVO vo )
    {
        this.vo = vo;
    }

    public List<UsuarioVO> getLista( ) throws Exception
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
	
	return null;
    }

    public void setLista( List<UsuarioVO> lista )
    {
	this.lista = lista;
    }
}
