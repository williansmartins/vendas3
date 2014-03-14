package br.com.exemplo.vendas.util.locator ;

public interface ServiceLocator
{
	public abstract Object getService( String s ) throws ServiceLocatorException ;
}