package br.com.exemplo.vendas.negocio.ejb.test;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;

import org.junit.Test;

import br.com.exemplo.vendas.negocio.interfaces.CompraInterface;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class CompraEJBTest
{
    Context context;
    Properties properties = new Properties();
    CompraInterface remoteCompra;
    ServiceDTO requestDTO = new ServiceDTO();
    ServiceDTO responseDTO = new ServiceDTO();
    Boolean sucesso1;
    Boolean sucesso2;

    {

	try
	{
	    properties.put( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
	    properties.put( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
	    properties.put( "java.naming.provider.url", "localhost:1099" );
	    Context ctx = new InitialContext( properties );
	    remoteCompra = (CompraInterface) ctx.lookup( "CompraBean/remote" );

	} catch ( NamingException e )
	{
	    System.out.println( "Erro ao fazer lookup" );
	    e.printStackTrace();
	}

    }

    @Test
    public void inserirLocalizarExcluirCompra( )
    {
	CompraVO vo = new CompraVO(1l, new Date(), "A", new BigDecimal(11), 1L, "cliente1");
	requestDTO.set( "compraVO", vo );
	try
	{
		UsuarioVO usuarioVO = null;
	    //Testa o inserir
		Assert.assertTrue((usuarioVO = inserirUsuario())!=null);
		
	    responseDTO = remoteCompra.inserirCompra( requestDTO );
	    Boolean sucesso1 = (Boolean) responseDTO.get( "resposta" );
	    Assert.assertTrue( sucesso1 );
	    
	    //Testa o localizar
	    /*requestDTO.set("preco", new BigDecimal(13));
		requestDTO.set("quantidadeEstoque", 0);*/
	    requestDTO.set("compraVO", vo);
	    responseDTO = remoteCompra.getCompra(requestDTO);
	    vo = (CompraVO) responseDTO.get( "getCompra" );
	    Assert.assertTrue( vo != null );
	    
	    //Testa o excluir
	    Boolean sucesso2 = (Boolean) remoteCompra.excluirCompra( requestDTO ).get( "resposta" );
	    Assert.assertTrue( sucesso2 );
	    
	    
	} catch ( RemoteException | LayerException e )
	{
	    e.printStackTrace();
	}
    }
    
    private UsuarioVO inserirUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
    public void inserirElistarTodosComprasInseridos( )
    {
	CompraVO vo1 = new CompraVO(1l, new Date(), "A", new BigDecimal(11), 1L, "cliente1");
	CompraVO vo2 = new CompraVO(2l, new Date(), "A", new BigDecimal(12), 2L, "cliente2");
	CompraVO vo3 = new CompraVO(3l, new Date(), "A", new BigDecimal(13), 3L, "cliente3");
	
	
	try
	{
	    //Testamos o inserir
	    requestDTO.set( "compraVO", vo1 );
	    responseDTO = remoteCompra.inserirCompra( requestDTO );
	    sucesso1 = (Boolean) responseDTO.get( "resposta" );
	    Assert.assertTrue( sucesso1 );
	    
	    requestDTO.set( "compraVO", vo2 );
	    responseDTO = remoteCompra.inserirCompra( requestDTO );
	    sucesso1 = (Boolean) responseDTO.get( "resposta" );
	    Assert.assertTrue( sucesso1 );
	    
	    requestDTO.set( "compraVO", vo3 );
	    responseDTO = remoteCompra.inserirCompra( requestDTO );
	    sucesso1 = (Boolean) responseDTO.get( "resposta" );
	    Assert.assertTrue( sucesso1 );
	    
	    //Testa o listar
	    responseDTO = remoteCompra.selecionarTodosCompras( requestDTO );
	    CompraVO[] compras = (CompraVO[]) responseDTO.get( "listaCompra" );
	    Assert.assertFalse( compras == null );
	    Assert.assertTrue( compras.length >= 3 );
	    
	    //Testa o excluir
	    requestDTO.set( "compraVO", vo1 );
	    sucesso2 = (Boolean) remoteCompra.excluirCompra( requestDTO ).get( "resposta" );
	    Assert.assertTrue( sucesso2 );

	    requestDTO.set( "compraVO", vo2 );
	    sucesso2 = (Boolean) remoteCompra.excluirCompra( requestDTO ).get( "resposta" );
	    Assert.assertTrue( sucesso2 );
	    
	    requestDTO.set( "compraVO", vo3 );
	    sucesso2 = (Boolean) remoteCompra.excluirCompra( requestDTO ).get( "resposta" );
	    Assert.assertTrue( sucesso2 );
	    
	} catch ( RemoteException | LayerException e )
	{
	    e.printStackTrace();
	}
	
    }

//	public static void main(String[] args) {
//		(new Long(1), new Date(), "A", new BigDecimal(50.00), 1L, "cliente");
//	}

}
