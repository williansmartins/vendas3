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

import br.com.exemplo.vendas.apresentacao.service.Service;
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

    public void insertView( )
    {
	new Redirecionador().redirecionar( "inserir-usuario.xhtml" );
    }
    
    public void metodo( ) throws LayerException
    {
	Service service = new Service( ) ;
	List<UsuarioVO> lista = service.listarUsuarios( ) ;
	System.out.println(lista);
    }

    public void salvar( )
    {
	System.out.println("voltar tentar salvar agora...");
	System.out.println(">>" + vo);
	//(Devemos integar com o Jboss aqui)
	//Inserir o usuário no banco
	new Redirecionador().redirecionar( "lista-usuarios.xhtml" );
    }
    
    public void apresentarUsuarios( )
    {
	System.out.println("deveria retornar os usuários aqui ...");
	//popular a lista de usuarios, por enquanto é fake
	//(Devemos integar com o Jboss aqui)
	lista = listaFake();
	new Redirecionador().redirecionar( "lista-usuarios.xhtml" );
    }
    
    public void remover( )
    {
	System.out.println("deveria remover um usuário aqui ...");
	//(Devemos integar com o Jboss aqui)
//	System.out.println("Remover o usuário com ID: " + vo.getId());
	new Redirecionador().redirecionar( "lista-usuarios.xhtml" );
    }

    @SuppressWarnings({ "unused", "unchecked" })
    private void buscarUsuarios( ) throws NamingException, LayerException,
	    RemoteException
    {
	@SuppressWarnings("rawtypes")
	Hashtable prop = new Hashtable();
	prop.put( InitialContext.INITIAL_CONTEXT_FACTORY,
		"org.jnp.interfaces.NamingContextFactory" );
	prop.put( InitialContext.PROVIDER_URL, "jnp://localhost:1099" );

	Context ctx = new InitialContext( prop );

	UsuarioInterface remoteUsuario = (UsuarioInterface) ctx
		.lookup( "UsuarioBean/remote" );

	ServiceLocator serviceLocator = ServiceLocatorFactory
		.getServiceLocator( "serviceLocator" );
	ServiceDTO requestDTO = new ServiceDTO();
	ServiceDTO responseDTO = new ServiceDTO();

	/**
	 * Consultar usuario
	 */

	responseDTO = remoteUsuario.selecionarTodosUsuarios( requestDTO ) ;
	UsuarioVO[ ] lista = ( UsuarioVO[ ] ) responseDTO.get( "listaUsuario" ) ;
	if (lista != null)
	{
	    for ( int i = 0; i < lista.length; i++ )
	    {
		UsuarioVO usuarioVO = (UsuarioVO) lista[i];
		System.out.println( usuarioVO );
	    }
	}
    }
    
    public List<UsuarioVO> listaFake( )
    {
	List<UsuarioVO> lista2 = new ArrayList<UsuarioVO>();
	/*
	lista2.add( new UsuarioVO( "login1", "senha1", "grupo1", "perfil1",
		"sim", new Date() ) );
	lista2.add( new UsuarioVO( "login1", "senha1", "grupo1", "perfil1",
		"sim", new Date() ) );
	lista2.add( new UsuarioVO( "login1", "senha1", "grupo1", "perfil1",
		"sim", new Date() ) );
	*/

	return lista2;
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

    public List<UsuarioVO> getLista( )
    {
	return lista;
    }

    public void setLista( List<UsuarioVO> lista )
    {
	this.lista = lista;
    }
}
