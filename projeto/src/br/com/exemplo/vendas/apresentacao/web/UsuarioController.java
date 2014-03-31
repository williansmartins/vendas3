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

    public UsuarioController()
    {
	vo = new UsuarioVO();
	System.out.println( "Controller de Usuário" );
	lista = getLista();
    }

    public void insertView( ) throws Exception
    {

    }

    public String save( ) throws Exception
    {
	System.out.println( "Salvando" );
	Hashtable prop = new Hashtable();
	prop.put( InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
	prop.put( InitialContext.PROVIDER_URL, "jnp://localhost:1099" );

	Context ctx = new InitialContext( prop );

	UsuarioInterface remoteUsuario = (UsuarioInterface) ctx.lookup( "UsuarioBean/remote" );


	ServiceLocator serviceLocator = ServiceLocatorFactory.getServiceLocator( "serviceLocator" );
	ServiceDTO requestDTO = new ServiceDTO();
	ServiceDTO responseDTO = new ServiceDTO();


	requestDTO.set( "usuarioVO", vo );
	responseDTO = remoteUsuario.inserirUsuario( requestDTO );
	Boolean sucesso = (Boolean) responseDTO.get( "resposta" );

	vo = new UsuarioVO();
	System.out.println( "Inseriu? " + sucesso);	
	return listAll();
    }

    public String listAll( )
    {
	vo = new UsuarioVO();
	lista = getLista();
	new Redirecionador().redirecionar( "lista-usuarios.xhtml" );
	return "";
    }

    public String remove( )
    {
	// dao.delete( entity.getId() );
	System.out.println( "removendo" );
	return listAll();
    }

    // GETTERS AND SETTERS



    public List<UsuarioVO> getLista( )
    {
	List<UsuarioVO> lista2 = new ArrayList<UsuarioVO>();
	lista2.add( new UsuarioVO( "login1", "senha1", "grupo1", "perfil1",
		"sim", new Date() ) );
	lista2.add( new UsuarioVO( "login1", "senha1", "grupo1", "perfil1",
		"sim", new Date() ) );
	lista2.add( new UsuarioVO( "login1", "senha1", "grupo1", "perfil1",
		"sim", new Date() ) );

	return lista2;
    }

    public UsuarioVO getVo( )
    {
        return vo;
    }

    public void setVo( UsuarioVO vo )
    {
        this.vo = vo;
    }

    public void setLista( List<UsuarioVO> lista )
    {
	this.lista = lista;
    }
}
