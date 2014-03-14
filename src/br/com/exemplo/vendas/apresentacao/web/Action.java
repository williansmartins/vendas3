package br.com.exemplo.vendas.apresentacao.web ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import br.com.exemplo.vendas.util.exception.LayerException ;

public interface Action
{
	public String execute( HttpServletRequest request, HttpServletResponse response )
			throws LayerException ;
}
