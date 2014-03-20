package br.com.exemplo.vendas.negocio.ejb ;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ProdutoLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ProdutoRemote;
import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ProdutoBean implements ProdutoRemote, ProdutoLocal {
	@PersistenceContext( unitName = "Vendas" )
	EntityManager em ;

	public ServiceDTO inserirProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ProdutoVO vo = (ProdutoVO) requestDTO.get("produtoVO");
		if(vo != null) {
			Produto produto = new Produto();
			produto.setCodigo(vo.getCodigo());
			produto.setDescricao(vo.getDescricao());
			produto.setPreco(vo.getPreco());
			produto.setEstoque(vo.getEstoque());
			if(DaoFactory.getProdutoDAO(em).inserir(produto)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO ;
	}

	public ServiceDTO alterarProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ProdutoVO vo = (ProdutoVO) requestDTO.get("produtoVO");
		if (vo != null) {
			Produto produto = new Produto();
			produto.setCodigo(vo.getCodigo());
			produto.setDescricao(vo.getDescricao());
			produto.setPreco(vo.getPreco());
			produto.setEstoque(vo.getEstoque());
			if(DaoFactory.getProdutoDAO(em).alterar(produto)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO excluirProduto( ServiceDTO requestDTO ) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ProdutoVO vo = (ProdutoVO) requestDTO.get("produtoVO");
		if(vo != null) {
			Produto produto = new Produto();
			produto.setCodigo(vo.getCodigo());
			if(DaoFactory.getProdutoDAO(em).excluir(produto)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO selecionarTodosProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		Produto produto = null ;
		List<Produto> lista = DaoFactory.getProdutoDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			ProdutoVO[] produtos = new ProdutoVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				produto = (Produto) lista.get(i);
				ProdutoVO produtoVO = new ProdutoVO();
				produtoVO.setCodigo(produto.getCodigo());
				produtoVO.setDescricao(produto.getDescricao());
				produtoVO.setPreco(produto.getPreco());
				produtoVO.setEstoque(produto.getEstoque());
				produtos[i] = produtoVO ;
			}
			responseDTO.set("listaProduto", produtos);
		}
		return responseDTO ;
	}

	public ServiceDTO getProduto(ServiceDTO requestDTO, Long codigo) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		Produto lista = DaoFactory.getProdutoDAO(em).localizarPorLogin(produto);
		if(lista != null) {
			produto = (Produto) lista;
			ProdutoVO produtoVO = new ProdutoVO();
			produtoVO.setCodigo(produto.getCodigo());
			produtoVO.setDescricao(produto.getDescricao());
			produtoVO.setPreco(produto.getPreco());
			produtoVO.setEstoque(produto.getEstoque());
			responseDTO.set( "getProduto", produtoVO ) ;
		}
		return responseDTO;
	}
}