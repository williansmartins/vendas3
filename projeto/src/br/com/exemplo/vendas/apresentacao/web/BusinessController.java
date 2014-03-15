package br.com.exemplo.vendas.apresentacao.web ;

import java.io.IOException ;
import java.util.HashMap ;

import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import br.com.exemplo.vendas.util.Util ;
import br.com.exemplo.vendas.util.exception.LayerException ;

public class BusinessController extends HttpServlet
{
	private HashMap actions ;

	private Action actionCreate( String className ) throws Exception
	{
		Action instance ;
		synchronized (actions)
		{
			instance = ( Action ) actions.get( className.toLowerCase( ) ) ;
		}
		if (instance == null)
		{
			instance = ( Action ) Util.actionInstance( className ) ;
			actions.put( className.toLowerCase( ), instance ) ;
		}
		return instance ;
	}

	private String process( HttpServletRequest _request, HttpServletResponse _response,
			Action action )
	{
		try
		{
			return action.execute( _request, _response ) ;
		}
		catch (LayerException layerExcpetion)
		{
			// verificar se é businessException ou sysException e
			// realiza o tratamento (por exemplo: forward para paginas
			// diferentes)
		}
		return null ;
	}

	private void processForward( HttpServletRequest _request, HttpServletResponse _response,
			String forward )
	{
		if (forward == null)
		{
			return ;
		}
		try
		{
			_response.sendRedirect( forward ) ;
		}
		catch (IOException ioexception)
		{
		}
	}

	protected void initController( ) throws BusinessControllerException
	{
		try
		{
			super.init( ) ;
			this.actions = new HashMap( ) ;
		}
		catch (Throwable _exception)
		{
			throw new BusinessControllerException( _exception.getMessage( ) ) ;
		}
	}

	protected void destroyController( )
	{
		super.destroy( ) ;
	}

	protected void processController( HttpServletRequest _request, HttpServletResponse _response )
			throws BusinessControllerException
	{
		try
		{
			String actionName = _request.getParameter( "action" ) ;
			if (( actionName != null ) && ( actionName.trim( ).length( ) > 0 ))
			{
				Action action = actionCreate( actionName ) ;
				String forward = process( _request, _response, action ) ;
				processForward( _request, _response, forward ) ;
			}
			else
			{
				// tratar caso a action venha nula
			}
		}
		catch (Throwable _exception)
		{
			throw new BusinessControllerException( _exception.getMessage( ) ) ;
		}
	}
}