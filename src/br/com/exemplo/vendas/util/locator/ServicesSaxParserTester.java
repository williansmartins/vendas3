package br.com.exemplo.vendas.util.locator ;

import java.io.InputStream ;
import java.util.* ;
import javax.xml.parsers.SAXParser ;
import javax.xml.parsers.SAXParserFactory ;

public class ServicesSaxParserTester
{

	public ServicesSaxParserTester( )
	{
	}

	protected InputStream getLocatorResourceAsStream( String name )
	{
		ClassLoader classLoader = Thread.currentThread( ).getContextClassLoader( ) ;
		if (classLoader == null)
		{
			classLoader = getClass( ).getClassLoader( ) ;
		}
		InputStream input = classLoader.getResourceAsStream( name ) ;
		return input ;
	}

	public void run( )
	{
		String fileName = "config/sl-services.xml" ;
		InputStream input = getLocatorResourceAsStream( fileName ) ;

		try
		{
			ServicesSaxParser handler = new ServicesSaxParser( ) ;
			SAXParserFactory factory = SAXParserFactory.newInstance( ) ;
			SAXParser parser = factory.newSAXParser( ) ;
			parser.parse( input, handler ) ;
			HashMap services = handler.getServices( ) ;
			printServices( services ) ;
		}
		catch (Exception _ex)
		{
			_ex.printStackTrace( ) ;
		}
	}

	protected void printServices( HashMap services )
	{
		for (Iterator i = services.values( ).iterator( ); i.hasNext( );)
		{
			Service service = ( Service ) i.next( ) ;
			String providers[] = service.getProviders( ) ;
			for (int p = 0; p < providers.length; p++)
			{
				System.out.println( "\t\t" + providers[ p ] ) ;
			}
		}
	}

	public static void main( String args[] )
	{
		ServicesSaxParserTester tester = new ServicesSaxParserTester( ) ;
		tester.run( ) ;
	}
}
