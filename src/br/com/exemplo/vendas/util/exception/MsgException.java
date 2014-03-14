package br.com.exemplo.vendas.util.exception ;

import java.io.Serializable ;

/**
 * Responsável por manipular uma MsgException
 * 
 * @version 1.0
 */
public class MsgException implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L ;
	private String code ;
	private Object[ ] params ;
	private Object details = "" ;

	/**
	 * Método construtor para MsgException
	 */
	public MsgException( String code, Object[ ] params )
	{
		this.code = code ;
		this.params = params ;
	}

	/**
	 * Método construtor para MsgException
	 */
	public MsgException( String code, Object[ ] params, Object details )
	{
		this.code = code ;
		this.params = params ;
		this.details = details ;
	}

	/**
	 * Método construtor para MsgException
	 */
	public MsgException( String code, Object details )
	{
		this.code = code ;
		this.details = details ;
	}

	/**
	 * Método construtor para MsgException
	 */
	public MsgException( Object[ ] params, Object details )
	{
		this.params = params ;
		this.details = details ;
	}

	/**
	 * Método construtor para MsgException
	 */
	public MsgException( String code )
	{
		this.code = code ;
	}

	/**
	 * Método construtor para MsgException
	 */
	public MsgException( Object[ ] params )
	{
		this.params = params ;
	}

	/**
	 * Método construtor para MsgException
	 */
	public MsgException( Object details )
	{
		this.details = details ;
	}

	/**
	 * Método utilizado para obter o código de uma MsgException
	 * 
	 * @return String
	 */
	public String getCode( )
	{
		return code ;
	}

	/**
	 * Método utilizado para obter os parâmetros de uma MsgException
	 * 
	 * @return Object[]
	 */
	public Object[ ] getParams( )
	{
		return this.params ;
	}

	/**
	 * Método utilizado para obter os detalhes de uma MsgException
	 * 
	 * @return Object
	 */
	public Object getDetails( )
	{
		return this.details ;
	}
}
