package br.com.exemplo.vendas.apresentacao.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.Redirecionador;

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
    }
    
    public void editar( )
    {
    }
    
    public void remover( )
    {
    }
    
    public void atualizar( )
    {
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
