package br.com.exemplo.vendas.util.exception ;

/**
 * Responsável por lançar a exceção DAOException
 * 
 * @version 1.0
 */
public final class DAOException extends SysException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L ;

	/**
	 * Método construtor para DAOException
	 */
	DAOException( MsgException msg )
	{
		super( msg ) ;
	}

	/**
	 * Método construtor para DAOException
	 */
	DAOException( MsgExceptionList list )
	{
		super( list ) ;
	}

}
