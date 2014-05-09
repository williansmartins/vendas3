package br.com.exemplo.vendas.negocio.ejb.test;

import java.math.BigDecimal;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ProdutoInterface;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesteProduto {

	/**
	 * Inserir Produto.java
	 * TBL_PRODUTO
	 * 
	 * 1
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main1(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ProdutoVO vo = new ProdutoVO(new Long(12), "Produto teste", new BigDecimal(100), "Sei la", new Integer(1000));
		requestDTO.set("produtoVO", vo);
		responseDTO = remoteProduto.inserirProduto(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Grava\u00e7\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Excluir Produto.java
	 * TBL_PRODUTO
	 * 
	 * 2
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main2(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("codigoProduto", new Long(11));
		responseDTO = remoteProduto.excluirProdutoPorCodigo(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Exclus\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a exclus\u00e3o.");
		}
	}
	
	/**
	 * Buscar Produto.java por codigo
	 * 
	 * 3
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main3(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteProduto.selecionarTodosProdutos(requestDTO);
		ProdutoVO[] lista = (ProdutoVO[]) responseDTO.get("listaProduto");
		if(lista != null){
			for(int i = 0; i < lista.length; i++){
				ProdutoVO produtoVO = (ProdutoVO) lista[i];
				System.out.println(produtoVO);
			}
		}
	}
	
	/**
	 * Inserir varios Produto.java
	 * TBL_PRODUTO
	 * 
	 * 4
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main4(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		Integer[] quantidades = new Integer[]{1000, 980, 10, 1, 2900, 28, 35, 23, 5000, 1, 1};
		for(int x=0, y=quantidades.length; x<y; x++){
			ProdutoVO vo = new ProdutoVO(new Long(x+1), "Produto (" + (x+1) + ")", new BigDecimal(quantidades[x]*10), "Estoque (" + (x+1) + ")", quantidades[x]);
			requestDTO.set("produtoVO", vo);
			responseDTO = remoteProduto.inserirProduto(requestDTO);
			Boolean sucesso = (Boolean) responseDTO.get("resposta");
			if(sucesso){
				System.out.println("Grava\u00e7\u00e3o realizada com sucesso.");
			}else{
				System.out.println("N\u00e3o foi possivel efetuar a grava\u00e7\u00e3o.");
			}
		}
	}
	
	/**
	 * Excluir varios Produto.java
	 * TBL_PRODUTO
	 * 
	 * 5
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main5(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		for(int x=0; x<10; x++){
			requestDTO.set("codigoProduto", new Long(x+1));
			responseDTO = remoteProduto.excluirProdutoPorCodigo(requestDTO);
			Boolean sucesso = (Boolean) responseDTO.get("resposta");
			if(sucesso){
				System.out.println("Exclus\u00e3o realizada com sucesso.");
			}else{
				System.out.println("N\u00e3o foi possivel efetuar a exclus\u00e3o.");
			}
		}
	}

	/**
	 * Alterar Produto.java
	 * TBL_PRODUTO
	 * 
	 * 6
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		ProdutoVO vo = new ProdutoVO(new Long(1), "Produto teste alterado", new BigDecimal(100), "Sei la", new Integer(1000));
		requestDTO.set("produtoVO", vo);
		responseDTO = remoteProduto.alterarProduto(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if(sucesso){
			System.out.println("Altera\u00e7\u00e3o realizada com sucesso.");
		}else{
			System.out.println("N\u00e3o foi possivel efetuar a altera\u00e7\u00e3o.");
		}
	}
	
	/**
	 * Teste webservices: Localizar produtos com preco abaixo de R$ 1000 e com mais de 2
	 * unidade em estoque.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void mainw(String[] args) throws Exception {
		Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(prop);
		ProdutoInterface remoteProduto = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		responseDTO = remoteProduto.localizarProdutosPorQuantidadeAcimaDeEPrecoAbaixoDe(requestDTO);
		ProdutoVO[] produtoVOs = (ProdutoVO[]) responseDTO.get("produtosPorQuantidadeAcimaDeEPrecoAbaixoDe");
		if(produtoVOs != null){
			for(int i = 0; i < produtoVOs.length; i++){
				ProdutoVO produtoVO = (ProdutoVO) produtoVOs[i];
				System.out.println(produtoVO);
			}
		}
	}
}