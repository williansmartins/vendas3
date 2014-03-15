package br.com.exemplo.vendas.util.locator ;

import java.util.HashMap ;

public class ServiceLocatorFactory
{
	protected static final HashMap locators = new HashMap( ) ;

	public ServiceLocatorFactory( )
	{
	}

	public static ServiceLocator getServiceLocator( Class clazz )
	{
		String name = clazz.getName( ) ;
		ServiceLocator sl = null ;
		if (locators.containsKey( name ))
		{
			sl = ( ServiceLocator ) locators.get( name ) ;
		}
		else
		{
			sl = new ServiceLocatorImpl( ) ;
			locators.put( name, sl ) ;
		}
		return sl ;
	}

	public static ServiceLocator getServiceLocator( String name )
	{
		ServiceLocator sl = null ;
		if (locators.containsKey( name ))
		{
			sl = ( ServiceLocator ) locators.get( name ) ;
		}
		else
		{
			sl = new ServiceLocatorImpl( ) ;
			locators.put( name, sl ) ;
		}
		return sl ;
	}
}