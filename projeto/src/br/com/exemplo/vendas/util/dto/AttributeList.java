package br.com.exemplo.vendas.util.dto ;

import java.io.Serializable ;
import java.util.HashMap ;
import java.util.Map ;

public class AttributeList extends HashMap implements Serializable
{
	public AttributeList( int arg0, float arg1 )
	{
		super( arg0, arg1 ) ;
	}

	public AttributeList( int arg0 )
	{
		super( arg0 ) ;
	}

	public AttributeList( )
	{
	}

	public AttributeList( Map arg0 )
	{
		super( arg0 ) ;
	}
}
