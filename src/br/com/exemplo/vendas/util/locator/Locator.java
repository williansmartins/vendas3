package br.com.exemplo.vendas.util.locator ;

import java.util.HashMap ;

import javax.naming.InitialContext ;
import javax.naming.NamingException ;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;

public class Locator
{
	private static final Log logger ;
	protected HashMap cache ;
	protected HashMap services ;
	protected HashMap providers ;

	public Locator( )
	{
		cache = new HashMap( ) ;
		services = null ;
		providers = null ;
		LocatorConfig config = new LocatorConfig( ) ;
		providers = config.getProviders( ) ;
		services = config.getServices( ) ;
	}

	public void clear( )
	{
		cache.clear( ) ;
	}

	public void clearCache( String envName )
	{
		if (cache.containsKey( envName ))
		{
			cache.remove( envName ) ;
		}
	}

	protected Object lookup( String envName ) throws ServiceLocatorException
	{
		Service service = getService( envName ) ;
		if (service == null)
		{
			return lookupLocal( envName ) ;
		}
		if (service.isCacheable( ) && cache.containsKey( envName ))
		{
			return cache.get( envName ) ;
		}
		String jndiName = service.getJndiName( ) != null ? service.getJndiName( ) : envName ;
		Object envEntry = lookupProviders( service.getProviders( ), jndiName ) ;
		if (envEntry == null)
		{
			envEntry = lookupLocal( jndiName ) ;
		}
		if (service.isCacheable( ))
		{
			cache.put( envName, envEntry ) ;
		}
		return envEntry ;
	}

	private Service getService( String envName )
	{
		if (services != null && services.containsKey( envName ))
		{
			return ( Service ) services.get( envName ) ;
		}
		else
		{
			return null ;
		}
	}

	private Object lookupLocal( String envName ) throws ServiceLocatorException
	{
		InitialContext ic = null ;
		try
		{
			ic = new InitialContext( ) ;
			Object obj = ic.lookup( envName ) ;
			return obj ;
		}
		catch (NamingException _nex)
		{
			throw new ServiceLocatorException( "O objeto nomeado[" + envName
					+ "] n�o foi encontrado localmente para JNDI service: [" + _nex.getMessage( )
					+ "].", _nex ) ;
		}
		finally
		{
			try
			{
				if (ic != null)
				{
					ic.close( ) ;
				}
			}
			catch (NamingException _nex)
			{
				logger.warn( "N�o foi poss�vel fechar o contexto: [" + _nex.getMessage( )
						+ "].", _nex ) ;
			}
		}
	}

	private Object lookupRemote( Provider provider, String envName ) throws ServiceLocatorException
	{
		InitialContext ic = null ;
		try
		{
			ic = new InitialContext( provider.getProperties( ) ) ;
			Object obj = ic.lookup( envName ) ;
			return obj ;
		}
		catch (NamingException _nex)
		{
			throw new ServiceLocatorException( "O objeto nomeado[" + envName
					+ "] n�o foi encontrado remotamente para JNDI service["
					+ provider.getProperties( ) + "]: [" + _nex.getMessage( ) + "].", _nex ) ;
		}
		finally
		{
			try
			{
				if (ic != null)
				{
					ic.close( ) ;
				}
			}
			catch (NamingException _nex)
			{
				logger.warn( "N�o foi poss�vel fechar o contexto: [" + _nex.getMessage( )
						+ "].", _nex ) ;
			}
		}
	}

	private Object lookupProviders( String providersName[], String envName )
	{
		if (providers != null && providersName != null)
		{
			for (int i = 0; i < providersName.length; i++)
			{
				Provider provider = ( Provider ) providers.get( providersName[ i ] ) ;
				try
				{
					return lookupRemote( provider, envName ) ;
				}
				catch (Exception _ex)
				{
					logger.warn( _ex.getMessage( ), _ex ) ;
				}
			}
		}
		return null ;
	}

	static
	{
		logger = LogFactory.getLog( Locator.class ) ;
	}
}