package br.com.exemplo.vendas.apresentacao.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.util.Redirecionador;

@SuppressWarnings("serial")
@ManagedBean(name = "geral_controller")
@SessionScoped
public class GeralController implements Serializable
{

    public void home( )
    {
    	new Redirecionador().redirecionar( "index.xhtml" );
    }

}
