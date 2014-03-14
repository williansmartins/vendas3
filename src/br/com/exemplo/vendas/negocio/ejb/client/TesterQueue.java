package br.com.exemplo.vendas.negocio.ejb.client ;

import java.util.Date ;
import java.util.Hashtable ;

import javax.naming.Context ;
import javax.naming.InitialContext ;

import br.com.exemplo.vendas.negocio.interfaces.RecebeRequisicaoInterface ;
import br.com.exemplo.vendas.util.dto.ServiceDTO ;

public class TesterQueue
{

	public static void main( String[ ] args ) throws Exception
	{
		Hashtable prop = new Hashtable( ) ;
		prop.put( InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" ) ;
		prop.put( InitialContext.PROVIDER_URL, "jnp://localhost:1099" ) ;

		Context context = new InitialContext( prop ) ;
		RecebeRequisicaoInterface remote = ( RecebeRequisicaoInterface ) context
				.lookup( "RecebeRequisicaoBean/remote" ) ;

		ServiceDTO requestDTO = new ServiceDTO( ) ;
		ServiceDTO responseDTO = new ServiceDTO( ) ;

		requestDTO.set( "teste", new String( "teste" ) ) ;
		responseDTO = remote.inserirFila( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get( "resposta" ) ;
		if (sucesso.booleanValue( ))
		{
			System.out
					.println( "Sucesso na execu��o do processo!" + responseDTO.get( "ticket" ) ) ;
		}
	}
}
