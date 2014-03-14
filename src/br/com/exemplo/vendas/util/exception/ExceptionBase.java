package br.com.exemplo.vendas.util.exception ;

import java.io.PrintWriter ;
import java.io.StringWriter ;
import java.util.Date ;

public class ExceptionBase extends Exception
{
	private static final long serialVersionUID = 1L ;
	private String traceString = "" ;

	/**
	 * Gera TraceString associada à thread corrente.
	 * 
	 * A Trace String é composta pela identificação da thread e o timestamp.
	 * 
	 * @return String com identificação do erro original.
	 */
	private static String exceptionTraceCode( )
	{
		return ( Thread.currentThread( ).toString( ) + ( new Date( ) ) ) ;
	}

	/**
	 * Construtor
	 * 
	 * Assinatura de construtor que recebe a exceção original, não derivada
	 * de ExceptionBase.
	 * 
	 * Este construtor deverá ser chamada para a exception original e portanto
	 * gera a TraceString.
	 * 
	 * @param e
	 */
	public ExceptionBase( Throwable e )
	{
		super( e ) ;
		traceString = ExceptionBase.exceptionTraceCode( ) ;
	}

	/**
	 * Construtor
	 * 
	 * Assinatura de construtor que recebe uma exceção derivada de
	 * ExceptionBase e portanto já é uma exceção que encapsula a original e
	 * já possui TraceString, que é reaproveitada para que possa ser usada
	 * como elemento de trace do problema.
	 * 
	 * @param e
	 *            exceção derivada de ExceptionBase
	 */
	public ExceptionBase( ExceptionBase e )
	{
		super( e ) ;
		traceString = e.getTraceString( ) ;
	}

	/**
	 * Construtor
	 * 
	 * Assinatura de construtor que recebe uma exceção derivada de
	 * ExceptionBase e portanto já é uma exceção que encapsula a original e
	 * já possui TraceString, que é reaproveitada para que possa ser usada
	 * como elemento de trace do problema.
	 * 
	 * @param e
	 *            exceção derivada de ExceptionBase
	 */
	public ExceptionBase( String str )
	{
		super( str ) ;
		traceString = ExceptionBase.exceptionTraceCode( ) ;
	}

	/**
	 * Construtor
	 * 
	 * Assinatura de construtor que recebe uma exceção derivada de
	 * ExceptionBase e portanto já é uma exceção que encapsula a original e
	 * já possui TraceString, que é reaproveitada para que possa ser usada
	 * como elemento de trace do problema.
	 * 
	 * @param e
	 *            exceção derivada de ExceptionBase
	 */
	public ExceptionBase( String str, Throwable e )
	{
		super( str, e ) ;
		traceString = ExceptionBase.exceptionTraceCode( ) ;
	}

	/**
	 * Construtor
	 * 
	 * Assinatura de construtor que recebe uma exceção derivada de
	 * ExceptionBase e portanto já é uma exceção que encapsula a original e
	 * já possui TraceString, que é reaproveitada para que possa ser usada
	 * como elemento de trace do problema.
	 * 
	 * @param e
	 *            exceção derivada de ExceptionBase
	 */
	public ExceptionBase( String str, ExceptionBase e )
	{
		super( str, e ) ;
		traceString = e.getTraceString( ) ;
	}

	/**
	 * Exibe a TraceString associada à exceção.
	 * 
	 * @return String
	 */
	public String getTraceString( )
	{
		return traceString ;
	}

	/**
	 * Retorna uma String contendo todo o StackTrace.
	 * 
	 * @return String
	 */
	public String getStackTraceString( )
	{
		StringWriter s = new StringWriter( ) ;
		PrintWriter w = new PrintWriter( s ) ;
		this.printStackTrace( w ) ;
		return s.toString( ) ;
	}
}