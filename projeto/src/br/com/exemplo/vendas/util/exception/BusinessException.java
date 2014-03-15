package br.com.exemplo.vendas.util.exception ;

/**
 * Responsável por lançar a exceção BusinessException
 * 
 * @version 1.0
 */
public class BusinessException extends LayerException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L ;

	/**
	 * Método construtor para BusinessException
	 */
	public BusinessException( MsgException msg )
	{
		super( msg ) ;
	}

	/**
	 * Método construtor para BusinessException
	 */
	public BusinessException( MsgExceptionList list )
	{
		super( list ) ;
	}

}
