package br.com.exemplo.vendas.util.locator ;

import java.util.Hashtable ;

public class Provider
{
	protected String name ;

	protected String desc ;

	protected Hashtable properties ;

	public Provider( )
	{
		properties = new Hashtable( ) ;
	}

	public String getDesc( )
	{
		return desc ;
	}

	public String getName( )
	{
		return name ;
	}

	public void setDesc( String string )
	{
		desc = string ;
	}

	public void setName( String string )
	{
		name = string ;
	}

	public Hashtable getProperties( )
	{
		return properties ;
	}

	public void setProperties( Hashtable properties )
	{
		this.properties = properties ;
	}

	public void setProperty( String key, String value )
	{
		properties.put( key, value ) ;
	}
}
