package br.com.exemplo.vendas.util.exception ;

import br.com.exemplo.vendas.util.log.LoggerGenerator ;

/**
 * Responsável por criacao das Exceções de Sistema
 * 
 * @version 1.0
 */
public class SysExceptionFactory
{

	public static DAOException getException( Exception exception )
	{
		String errorMessage = new String( exception.getMessage( ) ) ;

		MsgException message = new MsgException( exception.getMessage( ) ) ;

		if (Environment.getLogTraceSystemOut( ))
		{
			System.out
					.println( "--------------------------------:::   Exception Details	:::---------------------------------------" ) ;
			System.out.println( " Exception			 ->  [message]: " + exception.getMessage( ) ) ;
			System.out.println( " Exception Details	 ->  [info] " ) ;
			exception.printStackTrace( System.out ) ;
			System.out
					.println( "--------------------------------::: [DAOException created] :::---------------------------------------" ) ;
			LoggerGenerator.write( LoggerGenerator.EVENT_ACESSO_BD, errorMessage ) ;
		}

		if (Environment.getLogTraceFile( ))
		{
			LoggerGenerator.write( LoggerGenerator.EVENT_ACESSO_BD, errorMessage, exception ) ;
		}

		return new DAOException( message ) ;
	}

	public static SysException getSysException( Exception exception )
	{
		String errorMessage = new String( exception.getMessage( ) ) ;

		if (Environment.getLogTraceSystemOut( ))
		{
			System.out
					.println( "--------------------------------:::   Exception Details	:::---------------------------------------" ) ;
			System.out.println( " Exception			 ->  [message]: " + exception.getMessage( ) ) ;
			System.out.println( " Exception Details	 ->  [info] " ) ;
			exception.printStackTrace( System.out ) ;
			System.out
					.println( "--------------------------------::: [DAOException created] :::---------------------------------------" ) ;
			LoggerGenerator.write( LoggerGenerator.EVENT_ACESSO_BD, errorMessage ) ;
		}

		if (Environment.getLogTraceFile( ))
		{
			LoggerGenerator.write( LoggerGenerator.EVENT_ACESSO_BD, errorMessage, exception ) ;
		}

		return new TechnicalException( ) ;
	}

}
