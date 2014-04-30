package br.com.exemplo.vendas.apresentacao.service ;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import br.com.exemplo.vendas.apresentacao.delegate.BusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class Service {
	
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