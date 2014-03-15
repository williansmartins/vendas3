package br.com.exemplo.vendas.util.exception ;

import java.io.Serializable ;
import java.util.ArrayList ;
import java.util.Collection ;

/**
 * Responsável por manipular uma coleção de MsgException
 * 
 * @version 1.0
 */
public class MsgExceptionList implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L ;
	private Collection msgException = null ;

	/**
	 * Método construtor para MsgExceptionList
	 */
	public MsgExceptionList( )
	{
		this.msgException = new ArrayList( ) ;
	}

	/**
	 * Método construtor para MsgExceptionList
	 */
	public MsgExceptionList( MsgException msg )
	{
		this.msgException = new ArrayList( ) ;
		this.msgException.add( msg ) ;
	}

	/**
	 * Método utilizado para obter uma coleção de MsgException
	 * 
	 * @return Collection
	 */
	public Collection getMsgException( )
	{
		return msgException ;
	}

	/**
	 * Método utilizado para informar uma coleção de MsgException
	 * 
	 * @param msgException
	 *            informar uma coleção de MsgException a ser configurada
	 */
	public void setMsgException( Collection msgException )
	{
		this.msgException = new ArrayList( ) ;
		this.msgException = msgException ;
	}

	/**
	 * Método utilizado para adicionar uma coleção de MsgException
	 * 
	 * @param msg
	 *            informar uma MsgException para ser adicionada a coleção de
	 *            MsgException
	 */
	public void addMsgException( MsgException msg )
	{
		this.msgException.add( msg ) ;
	}

	/**
	 * Método utilizado para remover uma coleção de MsgException
	 * 
	 * @param msg
	 *            informar uma MsgException para ser removida da coleção de
	 *            MsgException
	 */
	public void removeMsgException( MsgException msg )
	{
		this.msgException.remove( msg ) ;
	}
}