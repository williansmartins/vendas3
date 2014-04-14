package br.com.exemplo.vendas.apresentacao.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.apresentacao.service.Service;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.Redirecionador;
import br.com.exemplo.vendas.util.exception.LayerException;

@SuppressWarnings("serial")
@ManagedBean(name = "usuario_mb")
@SessionScoped
public class UsuarioMB implements Serializable
{

    private UsuarioVO vo;
    private List<UsuarioVO> lista;

    public UsuarioMB()
    {
	vo = new UsuarioVO();
	lista = new ArrayList<>();
    }

    public void redirecionarIncAlt( )
    {
	new Redirecionador().redirecionar( "inserir-usuario.xhtml" );
    }

    public void redirecioarListagem( )
    {
	new Redirecionador().redirecionar( "lista-usuarios.xhtml" );
    }

    public void inserir( )
    {
	System.out.println( "Inserindo..." );
	String login = vo.getLogin();
	String senha = ( "senha2" );
	String grupo = ( "grupo2" );
	String perfil = ( "perfil2" );
	String bloqueado = ( "bloqueado" );
	String nome = ( "nome" );

	UsuarioVO vo = new UsuarioVO( login, senha, grupo, perfil, true,
		new Date() );
	Service service = new Service();
	Boolean sucesso = false;
	try
	{
	    sucesso = service.inserirUsuario( vo );
	} catch ( LayerException e )
	{
	    System.out.println( "Exceção ao inserir: " + e.getMessage() );
	}

	if ( sucesso )
	{
	    System.out.println( "Sucesso ao inserir" );
	} else
	{
	    System.out.println( "Erro ao inserir" );
	}

    }

    public void buscar( )
    {
	System.out.println( "Buscando..." );
	Service service = new Service( ) ;
	try
	{
	    List<UsuarioVO> lista = service.listarUsuarios( ) ;
	    for ( UsuarioVO usuarioVO : lista )
	    {
		System.out.println(">>>>>" + usuarioVO);
	    }
	} catch ( LayerException e )
	{
	    e.printStackTrace();
	    System.out.println("Erro ao buscar: " + e.getMessage());
	}

    }

    public void editar( )
    {}

    public void remover( )
    {}

    public void atualizar( )
    {}

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