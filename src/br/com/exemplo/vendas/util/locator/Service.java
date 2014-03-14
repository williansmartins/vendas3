package br.com.exemplo.vendas.util.locator ;

import java.util.ArrayList ;

public class Service
{
	protected String name ;

	protected String desc ;

	protected boolean cacheable ;

	protected String jndiName ;

	protected ArrayList providers ;

	public Service( )
	{
		providers = new ArrayList( ) ;
	}

	public boolean isCacheable( )
	{
		return cacheable ;
	}

	public String getDesc( )
	{
		return desc ;
	}

	public String getName( )
	{
		return name ;
	}

	public String getJndiName( )
	{
		return jndiName ;
	}

	public void setCacheable( boolean b )
	{
		cacheable = b ;
	}

	public void setDesc( String string )
	{
		desc = string ;
	}

	public void setName( String string )
	{
		name = string ;
	}

	public void setJndiName( String string )
	{
		jndiName = string ;
	}

	public void addProvider( String provider )
	{
		providers.add( provider ) ;
	}

	public String[ ] getProviders( )
	{
		return ( String[ ] ) providers.toArray( new String[ providers.size( ) ] ) ;
	}
}
