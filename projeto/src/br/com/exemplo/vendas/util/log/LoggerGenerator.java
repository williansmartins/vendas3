package br.com.exemplo.vendas.util.log ;

import java.io.File ;
import java.io.IOException ;

import org.apache.log4j.BasicConfigurator ;
import org.apache.log4j.DailyRollingFileAppender ;
import org.apache.log4j.Level ;
import org.apache.log4j.Logger ;
import org.apache.log4j.PatternLayout ;

import br.com.exemplo.vendas.util.exception.Environment ;

public final class LoggerGenerator
{
	// tamanhos maximos para campos do log
	public final static int FORMATTER_ERROR_CODE_SIZE = 20 ;

	public final static int FORMATTER_BASE_ERROR_CODE_SIZE = 10 ;

	public final static String EVENT_EXECUCAO_APRESENTACAO = "001" ;

	public final static String EVENT_ACESSO_BD = "002" ;

	// tamanhos maximos para campos de mensagens
	public final static int FORMATTER_MESSAGE_SISTEMA = 254 ;

	public final static int FORMATTER_MESSAGE_DEBUG = 500 ;

	private final static Logger debugLogger = Logger.getLogger( "portal.debug" ) ; // log
																					// de
																					// debug
																					// do
																					// sistema

	private final static Logger errorsLogger = Logger.getLogger( "portal.errors" ) ; // log
																						// de
																						// erros
																						// do
																						// sistema

	private final static Logger traceLogger = Logger.getLogger( "portal.trace" ) ; // log
																					// de
																					// trace
																					// do
																					// sistema

	private final static Logger responseLogger = Logger.getLogger( "portal.response" ) ; // log
																							// de
																							// resposta
																							// do
																							// sistema

	private static String directory = "" ;

	static
	{
		LogConfig logConfig = new LogConfig( ) ;
		directory = logConfig.getDirectory( ) ;
	}

	public static void init( )
	{
		try
		{
			BasicConfigurator.configure( ) ;

			DailyRollingFileAppender logDebug = new DailyRollingFileAppender( new PatternLayout(
					"%d{yyyy-MM-dd-HH.mm.ss.SSSSSS} %m%n" ),
					new File( directory ).getCanonicalPath( )
							+ System.getProperty( "file.separator" ) + "PORTAL.LOGTS",
					"a.'D'yyMMdd" ) ;
			DailyRollingFileAppender logErrors = new DailyRollingFileAppender( new PatternLayout(
					"%d{yyyy-MM-dd-HH.mm.ss.SSSSSS} %m%n" ),
					new File( directory ).getCanonicalPath( )
							+ System.getProperty( "file.separator" ) + "PORTAL.LOGTE",
					"a.'D'yyMMdd" ) ;
			DailyRollingFileAppender logTrace = new DailyRollingFileAppender( new PatternLayout(
					"%d{yyyy-MM-dd-HH.mm.ss.SSSSSS} %m%n" ),
					new File( directory ).getCanonicalPath( )
							+ System.getProperty( "file.separator" ) + "PORTAL.LOGTT",
					"a.'D'yyMMdd" ) ;
			DailyRollingFileAppender logResponse = new DailyRollingFileAppender( new PatternLayout(
					"%d{yyyy-MM-dd-HH.mm.ss.SSSSSS} %m%n" ),
					new File( directory ).getCanonicalPath( )
							+ System.getProperty( "file.separator" ) + "PORTAL.LOGTR",
					"a.'D'yyMMdd" ) ;

			debugLogger.addAppender( logDebug ) ;
			errorsLogger.addAppender( logErrors ) ;
			traceLogger.addAppender( logTrace ) ;
			responseLogger.addAppender( logResponse ) ;

			debugLogger.setLevel( Level.DEBUG ) ;
			errorsLogger.setLevel( Level.DEBUG ) ;
			traceLogger.setLevel( Level.DEBUG ) ;
			responseLogger.setLevel( Level.DEBUG ) ;

		}
		catch (IOException ioe)
		{
			System.out.println( "-----------------------------------------------" ) ;
			System.out.println( "Falha ao configurar o Log4j:" ) ;
			System.out.println( "   Mensagem: " + ioe.getMessage( ) ) ;
			System.out.println( "   Stack Trace:" ) ;
			ioe.printStackTrace( System.out ) ;
			System.out.println( "-----------------------------------------------" ) ;
		}
	}

	public static void close( )
	{
		debugLogger.getLoggerRepository( ).shutdown( ) ;
		errorsLogger.getLoggerRepository( ).shutdown( ) ;
		traceLogger.getLoggerRepository( ).shutdown( ) ;
		responseLogger.getLoggerRepository( ).shutdown( ) ;
	}

	/**
	 * Método utilizado para registrar informações de DEBUG no log.
	 * 
	 * @param event
	 *            informa o nível na camada da arquitetura
	 * @param message
	 *            informa a mensagem a ser logada
	 */
	public static void write( String event, String message )
	{
		if (Environment.getLogTraceFile( ))
		{
			init( ) ;
			debugLogger.debug( event + " " + format( message, FORMATTER_MESSAGE_DEBUG ) ) ;
			close( ) ;
		}
	}

	/**
	 * Metodo para tratamento de excecoes em logs
	 */
	public static void write( String event, String message, Throwable exception )
	{
		if (Environment.getLogTraceFile( ))
		{
			init( ) ;
			ExceptionWriter ew = new ExceptionWriter( ) ;
			exception.printStackTrace( ew ) ;
			String stackTrace = ew.getBuffer( ).toString( ) ;
			traceLogger.info( event + " " + format( message, FORMATTER_MESSAGE_DEBUG ) + " "
					+ stackTrace ) ;
			close( ) ;
		}
	}

	public static void write( String event, String id, String message, String type )
	{
		if (Environment.getLogTraceFile( ))
		{
			init( ) ;
			errorsLogger.info( event + " " + id + " " + message + " " + type ) ;
			close( ) ;
		}
	}

	public static void write( String event, String id, String message )
	{
		if (Environment.getLogTraceFile( ))
		{
			init( ) ;
			errorsLogger.info( event + " " + id + " " + message ) ;
			close( ) ;
		}
	}

	/**
	 * Método utilizado para registrar informações no log.
	 * 
	 * @param event
	 *            informa o nível na camada da arquitetura
	 * @param errorCode
	 *            informa um código de erro do sistema
	 * @param object
	 *            informa uma exceção lançada do objeto
	 * @param message
	 *            informa a mensagem a ser logada
	 */
	public static void write( String event, String errorCode, Object object, String message )
	{
		if (Environment.getLogTraceFile( ))
		{
			init( ) ;
			if (object instanceof Exception)
			{
				errorsLogger.info( event
						+ " "
						+ format( errorCode, FORMATTER_ERROR_CODE_SIZE )
						+ " "
						+ format( ( ( Exception ) object ).getMessage( ),
								FORMATTER_BASE_ERROR_CODE_SIZE ) + " "
						+ format( message, FORMATTER_MESSAGE_SISTEMA ) ) ;
			}
			else if (object instanceof String)
			{
				errorsLogger.info( event + " " + format( errorCode, FORMATTER_ERROR_CODE_SIZE )
						+ " " + format( ( String ) object, FORMATTER_BASE_ERROR_CODE_SIZE ) + " "
						+ format( message, FORMATTER_MESSAGE_SISTEMA ) ) ;
			}
			close( ) ;
		}
	}

	/**
	 * Método utilizado para formatar os campos do arquivo de log.
	 * 
	 * @param token
	 *            informa campo a ser formatado
	 * @param finalSize
	 *            informa o tamanho que um determinado campo deve possuir. Caso
	 *            o tamanho seja menor que o campo este será truncado. Caso
	 *            contrario, este será preenchido com espaço em branco.
	 */
	public static String format( String token, int finalSize )
	{
		int size = token.length( ) ;
		if (size < finalSize)
			while (size < finalSize)
			{
				size += 1 ;
				token += " " ;
			}
		else
			token = token.substring( 0, finalSize ) ;
		return token ;
	}
}
