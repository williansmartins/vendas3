package br.com.exemplo.vendas.util.exception ;

/**
 * Responsável por lançar a exceção GeneralException
 * 
 * @version 1.0
 */
public final class GeneralException extends SysException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L ;

	/**
	 * Método construtor para GeneralException
	 */
	GeneralException( MsgException msg )
	{
		super( msg ) ;
	}

	/**
	 * Método construtor para GeneralException
	 */
	GeneralException( MsgExceptionList list )
	{
		super( list ) ;
	}
}
