package br.com.exemplo.vendas.util ;

public abstract class Util
{
	public static final String LOG_FILE = "config/portallog.properties" ;

	private static Class actionClass( String className ) throws ClassNotFoundException
	{
		ClassLoader classLoader = Thread.currentThread( ).getContextClassLoader( ) ;
		return classLoader.loadClass( className ) ;
	}

	public static Object actionInstance( String className ) throws ClassNotFoundException,
			IllegalAccessException, InstantiationException
	{
		String completeName = "br.com.exemplo.vendas.apresentacao.actions." + className + "ACT" ;
		return actionClass( completeName ).newInstance( ) ;
	}
}