package br.com.exemplo.vendas.util.locator ;

import java.io.InputStream ;
import java.util.* ;
import javax.xml.parsers.SAXParser ;
import javax.xml.parsers.SAXParserFactory ;

public class ProvidersSaxParserTester
{

	public ProvidersSaxParserTester( )
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
		String fileName = "config/sl-providers-test.xml" ;
		InputStream input = getLocatorResourceAsStream( fileName ) ;

		try
		{
			ProvidersSaxParser handler = new ProvidersSaxParser( ) ;
			SAXParserFactory factory = SAXParserFactory.newInstance( ) ;
			SAXParser parser = factory.newSAXParser( ) ;
			parser.parse( input, handler ) ;
			HashMap providers = handler.getProviders( ) ;
			printProviders( providers ) ;
		}
		catch (Exception _ex)
		{
			_ex.printStackTrace( ) ;
		}
	}

	protected void printProviders( HashMap providers )
	{
		for (Iterator i = providers.values( ).iterator( ); i.hasNext( );)
		{
			Provider provider = ( Provider ) i.next( ) ;

			Hashtable properties = provider.getProperties( ) ;
			String key ;
			String value ;
			for (Enumeration keys = properties.keys( ); keys.hasMoreElements( ); System.out
					.println( "\t\t" + key + " = " + value ))
			{
				key = ( String ) keys.nextElement( ) ;
				value = ( String ) properties.get( key ) ;
			}

		}
	}

	public static void main( String args[] )
	{
		ProvidersSaxParserTester tester = new ProvidersSaxParserTester( ) ;
		tester.run( ) ;
	}
}
