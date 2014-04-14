package br.com.exemplo.vendas.apresentacao.web.mb;

import javax.faces.bean.ManagedBean;

import br.com.exemplo.vendas.util.Redirecionador;

@ManagedBean(name = "geral_mb")
public class GeralMB
{
    private String var = "123";

    public String getVar( )
    {
	return var;
    }

    public void setVar( String var )
    {
	this.var = var;
    }

    public GeralMB()
    {}

    public void usuarios( )
    {
	new Redirecionador().redirecionar( "inserir-usuario.xhtml" );
    }

    public void metodo( )
    {
	System.out.println( "metodo" );
    }

}