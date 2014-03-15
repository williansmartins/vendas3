package br.com.exemplo.vendas.negocio.ejb ;

import java.util.Date ;

import javax.ejb.Stateless ;
import javax.jms.JMSException ;
import javax.jms.ObjectMessage ;
import javax.jms.Queue ;
import javax.jms.QueueConnection ;
import javax.jms.QueueConnectionFactory ;
import javax.jms.QueueSender ;
import javax.jms.QueueSession ;
import javax.naming.Context ;
import javax.naming.InitialContext ;
import javax.naming.NamingException ;

import br.com.exemplo.vendas.negocio.ejb.interfaces.RecebeRequisicaoLocal ;
import br.com.exemplo.vendas.negocio.ejb.interfaces.RecebeRequisicaoRemote ;
import br.com.exemplo.vendas.util.dto.ServiceDTO ;
import br.com.exemplo.vendas.util.exception.LayerException ;

@Stateless
public class RecebeRequisicaoBean implements RecebeRequisicaoRemote, RecebeRequisicaoLocal
{
	public ServiceDTO inserirFila( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( ) ;
		String ticket = "" ;
		Context ctx ;
		try
		{
			ctx = new InitialContext( ) ;
			Queue queue = ( Queue ) ctx.lookup( "queue/RecebeRequisicaoQueue" ) ;
			QueueConnectionFactory factory = ( QueueConnectionFactory ) ctx
					.lookup( "ConnectionFactory" ) ;
			QueueConnection cnn = factory.createQueueConnection( ) ;
			QueueSession session = cnn.createQueueSession( false, QueueSession.AUTO_ACKNOWLEDGE ) ;

			String mensagem = ( String ) requestDTO.get( "teste" ) ;

			ObjectMessage message = session.createObjectMessage( mensagem ) ;

			QueueSender sender = session.createSender( queue ) ;
			synchronized (ticket)
			{
				ticket = Long.toString( new Date( ).getTime( ) ) ;
			}
			message.setJMSCorrelationID( ticket ) ;
			sender.send( message ) ;

			responseDTO.set( "resposta", new Boolean( true ) ) ;
			responseDTO.set( "ticket", ticket ) ;
		}
		catch (NamingException e)
		{
			e.printStackTrace( ) ;
		}
		catch (JMSException e)
		{
			e.printStackTrace( ) ;
		}
		return responseDTO ;
	}

}