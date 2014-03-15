package br.com.exemplo.vendas.util.log ;

import java.io.OutputStream ;
import java.io.PrintWriter ;
import java.io.StringWriter ;
import java.io.Writer ;

public class ExceptionWriter extends PrintWriter
{

	public ExceptionWriter( )
	{
		super( new StringWriter( ) ) ;
	}

	public ExceptionWriter( OutputStream out )
	{
		super( out ) ;
	}

	public ExceptionWriter( OutputStream out, boolean autoFlush )
	{
		super( out, autoFlush ) ;
	}

	public ExceptionWriter( Writer out )
	{
		super( out ) ;
	}

	public ExceptionWriter( Writer out, boolean autoFlush )
	{
		super( out, autoFlush ) ;
	}

	public StringBuffer getBuffer( )
	{
		if (super.out instanceof StringWriter)
		{
			return ( ( StringWriter ) out ).getBuffer( ) ;
		}
		throw new IllegalArgumentException( "Underlying writer is not a StringWriter" ) ;
	}
}
