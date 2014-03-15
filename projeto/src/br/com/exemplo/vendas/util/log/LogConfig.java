package br.com.exemplo.vendas.util.log ;

import java.io.InputStream ;
import java.util.Properties ;
import br.com.exemplo.vendas.util.Util ;

public class LogConfig
{
	private static String PROPS_ENVIRONMENT = "ambiente" ;
	private static String PROPS_DEV = "desenvolvimento" ;
	private static String PROPS_PRD = "producao" ;

	private String directory ;

	public LogConfig( )
	{
		this.loadConfig( ) ;
	}

	private Properties loadProperties( )
	{
		Properties props = new Properties( ) ;
		try
		{
			ClassLoader classLoader = Thread.currentThread( ).getContextClassLoader( ) ;
			if (classLoader == null)
			{
				classLoader = getClass( ).getClassLoader( ) ;
			}
			InputStream input = classLoader.getResourceAsStream( Util.LOG_FILE ) ;

			props.load( input ) ;

		}
		catch (Exception e)
		{
			e.printStackTrace( ) ;
		}
		return props ;
	}

	private void loadConfig( )
	{
		Properties props = loadProperties( ) ;

		if (props.getProperty( PROPS_ENVIRONMENT ).equalsIgnoreCase( PROPS_DEV ))
		{
			this.setDirectory( props.getProperty( "des_directory" ) ) ;
		}

		if (props.getProperty( PROPS_ENVIRONMENT ).equalsIgnoreCase( PROPS_PRD ))
		{
			this.setDirectory( props.getProperty( "prd_directory" ) ) ;
		}
	}

	public String getDirectory( )
	{
		return directory ;
	}

	public void setDirectory( String _directory )
	{
		directory = _directory ;
	}
}