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
@ManagedBean(name = "teste_mb")
@SessionScoped
public class TestMB implements Serializable
{

    Service service;
    Boolean sucesso;
    UsuarioVO vo;

    public TestMB()
    {
	vo = gerarVO();
	service = new Service();
    }

    public void redirecionar( )
    {
	new Redirecionador().redirecionar( "pagina-nova.xhtml" );
    }

    public void inserir( )
    {
	System.out.println("Inserindo...");
	try
	{
	    vo = gerarVO();
	    sucesso = service.inserirUsuario( vo );
	    
	    if ( sucesso )
	    {
		System.out.println( "Sucesso ao inserir" );
	    } else
	    {
		System.out.println( "Erro ao inserir" );
	    }
	} catch ( LayerException e )
	{
	    System.out.println( "Exceção ao inserir: " + e.getMessage() );
	}

    }

    public void remover( )
    {
	System.out.println("Removendo...");
	service = new Service();
	try
	{
	    sucesso = service.excluirUsuario( vo );
	}catch ( LayerException e )
	{
	    System.out.println( "Exceção ao inserir: " + e.getMessage() );
	}
	
	if ( sucesso )
	{
	    System.out.println( "Sucesso!" );
	} else
	{
	    System.out.println( "Erro!" );
	}
    }
    
    public void atualizar( )
    {
	System.out.println("Atualizando...");
	vo.setSenha( "nova-senha" );
	service = new Service();
	try
	{
	    sucesso = service.alterarUsuario( vo );
	}catch ( LayerException e )
	{
	    System.out.println( "Exceção ao inserir: " + e.getMessage() );
	}
	
	if ( sucesso )
	{
	    System.out.println( "Sucesso!" );
	} else
	{
	    System.out.println( "Erro!" );
	}
    }
    
    public void listar( ) throws LayerException
    {
	System.out.println("Listando...");
	service = new Service();
	List<UsuarioVO> lista = service.listarUsuarios();
	System.out.println( lista );
	
	if ( lista.size() > 0 )
	{
	    System.out.println( "Sucesso!" );
	} else
	{
	    System.out.println( "Erro!" );
	}
    }
    
    private UsuarioVO gerarVO( )
    {
	String login = "teste-" + new Date().getTime();
	String senha = ( "senha2" );
	String grupo = ( "grupo2" );
	String perfil = ( "perfil2" );

	UsuarioVO vo = new UsuarioVO();
	vo.setLogin( login );
	vo.setSenha( senha );
	vo.setGrupo( grupo );
	vo.setPerfil( perfil );
	vo.setBloqueado( true );	
	vo.setUltimoAcesso( new Date() );
	return vo;
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

    

}
