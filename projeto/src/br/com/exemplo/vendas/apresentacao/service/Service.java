package br.com.exemplo.vendas.apresentacao.service ;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import br.com.exemplo.vendas.apresentacao.delegate.BusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class Service implements Serializable {
	
	public String enviaMensagem(String mensagem) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("teste", mensagem);
		responseDTO = BusinessDelegate.getInstance().inserirQueue(requestDTO);
		return (String) responseDTO.get("ticket");
	}
	
	public Boolean inserirUsuario(UsuarioVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("usuarioVO", vo);
		responseDTO = BusinessDelegate.getInstance().inserirUsuario(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		return sucesso;
	}

	public List<UsuarioVO> listarUsuarios() throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		responseDTO = BusinessDelegate.getInstance().selectionarTodosUsuarios(requestDTO);
		UsuarioVO[] usuarios = (UsuarioVO[])responseDTO.get("listaUsuario");
		List<UsuarioVO> lista = Arrays.asList(usuarios);
		return lista;
	}

	public UsuarioVO buscarUsuario( UsuarioVO vo ) throws LayerException {
	    ServiceDTO requestDTO = new ServiceDTO();
	    ServiceDTO responseDTO = new ServiceDTO();
	    
	    requestDTO.set( "usuarioVO", vo ) ;
	    responseDTO = BusinessDelegate.getInstance().buscarUsuario( requestDTO );
	    UsuarioVO usuario = (UsuarioVO)responseDTO.get("getUsuario");

	    return usuario;
	}

	public Boolean alterarUsuario( UsuarioVO vo ) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set( "usuarioVO", vo ) ;
		responseDTO = BusinessDelegate.getInstance().alterarUsuario(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}

	public Boolean excluirUsuario(UsuarioVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("usuarioVO", vo);
		responseDTO = BusinessDelegate.getInstance().excluirUsuario(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}
	
	public List<ClienteVO> listarClientes() throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		responseDTO = BusinessDelegate.getInstance().listarClientes(requestDTO);
		return Arrays.asList((ClienteVO[])responseDTO.get("resposta"));
	}
		
	public Boolean inserirProduto(ProdutoVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("produtoVO", vo);
		responseDTO = BusinessDelegate.getInstance().inserirProduto(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		return sucesso;
	}
	
	public List<ProdutoVO> listarProdutos() throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		responseDTO = BusinessDelegate.getInstance().selectionarTodosProdutos(requestDTO);
		ProdutoVO[] produtos = (ProdutoVO[])responseDTO.get("listaProduto");
		List<ProdutoVO> lista = Arrays.asList(produtos);
		return lista;
	}
	
	public ProdutoVO buscarProduto( ProdutoVO vo ) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		requestDTO.set( "produtoVO", vo ) ;
		responseDTO = BusinessDelegate.getInstance().getProduto( requestDTO);
		ProdutoVO produto = (ProdutoVO)responseDTO.get("getProduto");
		
		return produto;
	}
	
	public Boolean alterarProduto( ProdutoVO vo ) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set( "produtoVO", vo ) ;
		responseDTO = BusinessDelegate.getInstance().alterarProduto(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}
	
	public Boolean excluirProduto(ProdutoVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("produtoVO", vo);
		responseDTO = BusinessDelegate.getInstance().excluirProduto(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}
	public Boolean inserirCompra(CompraVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("compraVO", vo);
		responseDTO = BusinessDelegate.getInstance().inserirCompra(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		return sucesso;
	}
	
	public List<CompraVO> listarCompras() throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		responseDTO = BusinessDelegate.getInstance().selectionarTodasCompras(requestDTO);
		CompraVO[] compras = (CompraVO[])responseDTO.get("listaCompra");
		List<CompraVO> lista = Arrays.asList(compras);
		return lista;
	}
	
	public CompraVO getCompra( CompraVO vo ) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		
		requestDTO.set( "compraVO", vo ) ;
		responseDTO = BusinessDelegate.getInstance().getCompra( requestDTO );
		CompraVO compra = (CompraVO)responseDTO.get("getCompra");
		
		return compra;
	}
	
	public Boolean alterarCompra( CompraVO vo ) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set( "compraVO", vo ) ;
		responseDTO = BusinessDelegate.getInstance().alterarCompra(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}
	
	public Boolean excluirCompra(CompraVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("compraVO", vo);
		responseDTO = BusinessDelegate.getInstance().excluirCompra(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}
	
	public Boolean excluirCompraPorNumero(CompraVO vo) throws LayerException, RemoteException{
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("compraVO", vo);
		responseDTO = BusinessDelegate.getInstance().excluirCompraPorNumero(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}
	
	//Metodo para webservices.
		public CompraVO[] localizarComprasPorValorAbaixoDe500() throws LayerException {
			ServiceDTO requestDTO = new ServiceDTO();
			ServiceDTO responseDTO = new ServiceDTO();
			requestDTO.set("comprasPorValorAbaixoDe500", new BigDecimal(500));
			responseDTO = BusinessDelegate.getInstance().localizarComprasPorValorAbaixoDe500(requestDTO);
			return (CompraVO[]) responseDTO.get("compraVOs");
		}
		
		//Metodo para webservices.
		public ClienteVO[] localizarClientesComComprasRealizadas() throws LayerException {
			ServiceDTO requestDTO = new ServiceDTO();
			ServiceDTO responseDTO = new ServiceDTO();
			responseDTO = BusinessDelegate.getInstance().localizarClientesComComprasRealizadas(requestDTO);
			return (ClienteVO[]) responseDTO.get("clientesPorCompraRealizadas");
		}
		
		public ProdutoVO[] localizarProdutosPorQuantidadeAcimaDe2UnidadesEPrecoAbaixoDe1000() throws LayerException {
			ServiceDTO requestDTO = new ServiceDTO();
			ServiceDTO responseDTO = new ServiceDTO();
			requestDTO.set("preco", new BigDecimal(1000));
			requestDTO.set("quantidadeEstoque", 2);
			responseDTO = BusinessDelegate.getInstance().localizarClientesComComprasRealizadas(requestDTO);
			return (ProdutoVO[]) responseDTO.get("produtosPorQuantidadeAcimaDeEPrecoAbaixoDe");
		}
	
}