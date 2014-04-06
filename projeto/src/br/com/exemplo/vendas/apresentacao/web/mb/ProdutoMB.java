package br.com.exemplo.vendas.apresentacao.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.util.Redirecionador;

@SuppressWarnings("serial")
@ManagedBean(name = "produto_mb")
@SessionScoped
public class ProdutoMB implements Serializable
{

    private Produto entity;
    private List<Produto> lista;

    public ProdutoMB()
    {
	entity = new Produto();
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
    public Produto getEntity( )
    {
        return entity;
    }

    public void setEntity( Produto entity )
    {
        this.entity = entity;
    }

    public List<Produto> getLista( )
    {
        return lista;
    }

    public void setLista( List<Produto> lista )
    {
        this.lista = lista;
    }
    
}
