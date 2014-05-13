package br.com.exemplo.vendas.negocio.ejb.test;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;

import org.junit.Test;

import br.com.exemplo.vendas.negocio.interfaces.ProdutoInterface;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class ProdutoEJBTest
{
    Context context;
    Properties properties = new Properties();
    ProdutoInterface remoteProduto;
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
	    remoteProduto = (ProdutoInterface) ctx.lookup( "ProdutoBean/remote" );

	} catch ( NamingException e )
	{
	    System.out.println( "Erro ao fazer lookup" );
	    e.printStackTrace();
	}

    }

    @Test
    public void inserirLocalizarExcluirProduto( )
    {
	ProdutoVO vo = new ProdutoVO(1l, "descricao", new BigDecimal(12), "1", 1);
	requestDTO.set( "produtoVO", vo );
	try
	{
	    //Testa o inserir
	    responseDTO = remoteProduto.inserirProduto( requestDTO );
	    Boolean sucesso1 = (Boolean) responseDTO.get( "resposta" );
	    Assert.assertTrue( sucesso1 );
	    
	    //Testa o localizar
	    /*requestDTO.set("preco", new BigDecimal(13));
		requestDTO.set("quantidadeEstoque", 0);*/
	    requestDTO.set("produtoVO", vo);
	    responseDTO = remoteProduto.getProduto(requestDTO);
	    vo = (ProdutoVO) responseDTO.get( "getProduto" );
	    Assert.assertTrue( vo != null );
	    
	    //Testa o excluir
	    Boolean sucesso2 = (Boolean) remoteProduto.excluirProduto( requestDTO ).get( "resposta" );
	    Assert.assertTrue( sucesso2 );
	    
	    
	} catch ( RemoteException | LayerException e )
	{
	    e.printStackTrace();
	}
    }
    
    @Test
    public void inserirElistarTodosProdutosInseridos( )
    {
	ProdutoVO vo1 = new ProdutoVO(1l, "descricao1", new BigDecimal(11), "1", 1);
	ProdutoVO vo2 = new ProdutoVO(2l, "descricao2", new BigDecimal(12), "2", 2);
	ProdutoVO vo3 = new ProdutoVO(3l, "descricao3", new BigDecimal(13), "3", 3);
	
	
	try
	{
	    //Testamos o inserir
	    requestDTO.set( "produtoVO", vo1 );
	    responseDTO = remoteProduto.inserirProduto( requestDTO );
	    sucesso1 = (Boolean) responseDTO.get( "resposta" );
	    Assert.assertTrue( sucesso1 );
	    
	    requestDTO.set( "produtoVO", vo2 );
	    responseDTO = remoteProduto.inserirProduto( requestDTO );
	    sucesso1 = (Boolean) responseDTO.get( "resposta" );
	    Assert.assertTrue( sucesso1 );
	    
	    requestDTO.set( "produtoVO", vo3 );
	    responseDTO = remoteProduto.inserirProduto( requestDTO );
	    sucesso1 = (Boolean) responseDTO.get( "resposta" );
	    Assert.assertTrue( sucesso1 );
	    
	    //Testa o listar
	    responseDTO = remoteProduto.selecionarTodosProdutos( requestDTO );
	    ProdutoVO[] produtos = (ProdutoVO[]) responseDTO.get( "listaProduto" );
	    Assert.assertFalse( produtos == null );
	    Assert.assertTrue( produtos.length >= 3 );
	    
	    //Testa o excluir
	    requestDTO.set( "produtoVO", vo1 );
	    sucesso2 = (Boolean) remoteProduto.excluirProduto( requestDTO ).get( "resposta" );
	    Assert.assertTrue( sucesso2 );

	    requestDTO.set( "produtoVO", vo2 );
	    sucesso2 = (Boolean) remoteProduto.excluirProduto( requestDTO ).get( "resposta" );
	    Assert.assertTrue( sucesso2 );
	    
	    requestDTO.set( "produtoVO", vo3 );
	    sucesso2 = (Boolean) remoteProduto.excluirProduto( requestDTO ).get( "resposta" );
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
