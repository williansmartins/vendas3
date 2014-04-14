package br.com.exemplo.vendas.util;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class Redirecionador
{
    public void redirecionar( String url )
    {
	try
	{
	    System.out.println( ">> Redirecionando para: " + url );
	    FacesContext.getCurrentInstance().getExternalContext()
		    .redirect( url );
	} catch ( IOException e )
	{
	    System.out.println( "Erro ao redirecionar:" + e.getMessage() );
	    e.printStackTrace();
	}
    }
}
