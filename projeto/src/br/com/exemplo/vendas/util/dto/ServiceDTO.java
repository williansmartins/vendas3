package br.com.exemplo.vendas.util.dto ;

import java.io.Serializable ;
import java.util.* ;

public class ServiceDTO implements Serializable, AttributeAccess
{
	private AttributeList attributes ;
	private MessageList messages ;

	public ServiceDTO( )
	{
		attributes = new AttributeList( ) ;
		messages = new MessageList( ) ;
	}

	public AttributeList getAttributes( Collection keysOfAttributes ) throws ServiceDTOException
	{
		Iterator keys = keysOfAttributes.iterator( ) ;
		Object key = null ;
		AttributeList result = new AttributeList( ) ;
		try
		{
			for (; keys.hasNext( ); result.put( key, attributes.get( key ) ))
			{
				key = keys.next( ) ;
			}
			return result ;
		}
		catch (Exception e)
		{
			throw new ServiceDTOException( e.getMessage( ) ) ;
		}
	}

	public AttributeList getAllAttributes( )
	{
		return ( AttributeList ) attributes.clone( ) ;
	}

	public void setAttributes( AttributeList pairsKeysValues ) throws ServiceDTOException
	{
		Iterator entries = pairsKeysValues.entrySet( ).iterator( ) ;
		java.util.Map.Entry entry = null ;
		try
		{
			for (; entries.hasNext( ); attributes.put( entry.getKey( ), entry.getValue( ) ))
			{
				entry = ( java.util.Map.Entry ) entries.next( ) ;
			}
		}
		catch (Exception e)
		{
			throw new ServiceDTOException( e.getMessage( ) ) ;
		}
	}

	public Object get( Object key )
	{
		return attributes.get( key ) ;
	}

	public void set( Object key, Object value )
	{
		attributes.put( key, value ) ;
	}

	public boolean hasMessage( )
	{
		return messages.hasMessage( ) ;
	}

	public boolean hasNextMessage( )
	{
		return messages.hasNextMessage( ) ;
	}

	public Message getMessage( )
	{
		return messages.getMessage( ) ;
	}

	public void putMessage( Message message )
	{
		messages.putMessage( message ) ;
	}

	public void clearMessages( )
	{
		messages = new MessageList( ) ;
	}

	public void clearAttributes( )
	{
		attributes = new AttributeList( ) ;
	}
}