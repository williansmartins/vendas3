package br.com.exemplo.vendas.util.dto ;

import java.util.Collection ;

public interface AttributeAccess
{
	public abstract AttributeList getAttributes( Collection collection ) throws ServiceDTOException ;

	public abstract AttributeList getAllAttributes( ) ;

	public abstract void setAttributes( AttributeList attributelist ) throws ServiceDTOException ;

	public abstract Object get( Object obj ) ;

	public abstract void set( Object obj, Object obj1 ) ;

	public abstract boolean hasMessage( ) ;

	public abstract boolean hasNextMessage( ) ;

	public abstract Message getMessage( ) ;

	public abstract void putMessage( Message message ) ;

	public abstract void clearMessages( ) ;

	public abstract void clearAttributes( ) ;
}
