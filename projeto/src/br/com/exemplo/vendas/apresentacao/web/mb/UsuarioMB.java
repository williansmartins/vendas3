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
    Service service;
    Boolean sucesso;
    boolean editando = false;

    public UsuarioMB()
    {
	vo = new UsuarioVO();
	vo.setUltimoAcesso( new Date() );
	vo.setBloqueado( true );
	lista = new ArrayList<>();
	lista = buscarItens();
    }

    public List<UsuarioVO> buscarItens( )
    {
	service = new Service();
	try
	{
	    lista = service.listarUsuarios();
	} catch ( LayerException e )
	{
	    System.out.println( "Erro: " + e.getMessage() + " - " + this );
	}
	return lista;
    }

    public void redirecionarIncAlt( )
    {
	vo = new UsuarioVO();
	vo.setUltimoAcesso( new Date() );
	editando = false;
	new Redirecionador().redirecionar( "inserir-usuario.xhtml" );
    }

    public void redirecinoarListagem( )
    {
	lista = buscarItens();
	new Redirecionador().redirecionar( "lista-usuarios.xhtml" );
    }

    public void salvar( )
    {
	if ( editando == true )
	{
	    atualizar();
	} else
	{
	    inserir();
	}
    }

    public void inserir( )
    {

	service = new Service();
	try
	{
	    if ( service.inserirUsuario( vo ) )
	    {
		System.out.println( "Sucesso ao inserir" );
		redirecinoarListagem();
	    } else
	    {
		System.out.println( "Erro ao inserir" );
	    }
	} catch ( LayerException e )
	{
	    System.out.println( "Exceção ao inserir: " + e.getMessage() );
	}

    }

    public void editar( )
    {
	service = new Service();
	try
	{
	    vo = service.buscarUsuario( vo );
	    editando = true;
	    new Redirecionador().redirecionar( "inserir-usuario.xhtml" );
	} catch ( LayerException e )
	{
	    System.out.println( "Erro: " + e.getMessage() + " - " + this );
	}
    }

    public void remover( )
    {}

    public void atualizar( )
    {
	try
	{
	    if ( service.alterarUsuario( vo ) )
	    {
		System.out.println( "Sucesso ao atualizar" );
		redirecinoarListagem();
	    } else
	    {
		System.out.println( "Erro ao atualizar" );
	    }
	} catch ( LayerException e )
	{
	    System.out.println( "Exceção ao inserir: " + e.getMessage() );
	}
    }

    public void loadLista( ) throws LayerException
    {
	service = new Service();
	lista = service.listarUsuarios();
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
