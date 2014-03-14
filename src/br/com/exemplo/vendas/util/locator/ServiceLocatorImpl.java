package br.com.exemplo.vendas.util.locator ;

public class ServiceLocatorImpl extends Locator implements ServiceLocator
{
	public ServiceLocatorImpl( )
	{
	}

	public Object getService( String jndiName ) throws ServiceLocatorException
	{
		return lookup( jndiName ) ;
	}
}
