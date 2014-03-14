package br.com.exemplo.vendas.util.locator ;

import java.io.PrintStream ;
import java.io.PrintWriter ;

public class ServiceLocatorException extends Exception
{
	private static final long serialVersionUID = 1L ;

	private Throwable cause ;

	public ServiceLocatorException( )
	{

	}

	public ServiceLocatorException( String message )
	{
		super( message ) ;
	}

	public ServiceLocatorException( String message, Throwable cause )
	{
		super( message ) ;
		this.cause = cause ;
	}

	public ServiceLocatorException( Throwable cause )
	{
		super( cause.getMessage( ) ) ;
		this.cause = cause ;
	}

	public Throwable getCause( )
	{
		return cause ;
	}

	public void printStackTrace( )
	{
		super.printStackTrace( ) ;
		if (cause != null)
		{
			cause.printStackTrace( ) ;
		}
	}

	public void printStackTrace( PrintStream s )
	{
		super.printStackTrace( s ) ;
		if (cause != null)
		{
			s.print( "Caused by: " ) ;
			cause.printStackTrace( s ) ;
		}
	}

	public void printStackTrace( PrintWriter s )
	{
		super.printStackTrace( s ) ;
		if (cause != null)
		{
			s.print( "Caused by: " ) ;
			cause.printStackTrace( s ) ;
		}
	}
}