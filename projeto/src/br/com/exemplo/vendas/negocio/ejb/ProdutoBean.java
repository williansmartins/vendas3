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
	@PersistenceContext(unitName = "Vendas")
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

	public ServiceDTO selecionarTodosProdutos(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<Produto> lista = DaoFactory.getProdutoDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			ProdutoVO[] produtos = new ProdutoVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				Produto produto = (Produto) lista.get(i);
				ProdutoVO produtoVO = ProdutoVO.create(produto);
				produtos[i] = produtoVO;
			}
			responseDTO.set("listaProduto", produtos);
		}else{
			responseDTO.set("listaProduto", new ProdutoVO[0]);
		}
		return responseDTO ;
	}

	public ServiceDTO getProduto(ServiceDTO requestDTO, Long codigo) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			Produto produto = DaoFactory.getProdutoDAO(em).localizarPorCodigo(codigo);
			ProdutoVO produtoVO = ProdutoVO.create(produto);
			responseDTO.set( "getProduto", produtoVO);
		}catch(Exception e){
			responseDTO.set( "getProduto", null);
		}
		return responseDTO;
	}
}