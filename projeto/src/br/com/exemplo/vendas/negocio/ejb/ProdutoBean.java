package br.com.exemplo.vendas.negocio.ejb ;

import java.math.BigDecimal;
import java.rmi.RemoteException;
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
	EntityManager em;

	public ServiceDTO inserirProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ProdutoVO vo = (ProdutoVO) requestDTO.get("produtoVO");
		if(vo != null) {
			Produto produto = Produto.create(vo);
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
			Produto produto = Produto.create(vo);
			if(DaoFactory.getProdutoDAO(em).alterar(produto)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO excluirProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ProdutoVO vo = (ProdutoVO) requestDTO.get("produtoVO");
		if(vo != null){
			try{
				Produto produto = DaoFactory.getProdutoDAO(em).localizarPorCodigo(vo.getCodigo());
				if(DaoFactory.getProdutoDAO(em).excluir(produto)) {
					responseDTO.set("resposta", new Boolean(true));
				}else{
					responseDTO.set("resposta", new Boolean(false));
				}
			}catch(Exception e){
				responseDTO.set("resposta", new Boolean(false));
			}
			
		}
		return responseDTO;
	}
	
	public ServiceDTO excluirProdutoPorCodigo(ServiceDTO requestDTO) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		Long codigo = (Long) requestDTO.get("codigoProduto");
		if(codigo != null){
			if(DaoFactory.getProdutoDAO(em).excluir(codigo)) {
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
			for(int i = 0; i < lista.size(); i++){
				Produto produto = (Produto) lista.get(i);
				ProdutoVO produtoVO = ProdutoVO.create(produto);
				produtos[i] = produtoVO;
			}
			responseDTO.set("listaProduto", produtos);
		}else{
			responseDTO.set("listaProduto", new ProdutoVO[0]);
		}
		return responseDTO;
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

	@Override
	public ServiceDTO localizarProdutosPorQuantidadeAcimaDeEPrecoAbaixoDe(ServiceDTO requestDTO, BigDecimal preco, Integer quantidadeEstoque) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			List<Produto> produtos = DaoFactory.getProdutoDAO(em).localizarPorQuantidadeAcimaDeEPrecoAbaixoDe(preco, quantidadeEstoque);
			if((produtos != null) && (!produtos.isEmpty())) {
				ProdutoVO[] produtoVOs = new ProdutoVO[produtos.size()];
				for(int i = 0; i < produtos.size(); i++){
					Produto produto = (Produto) produtos.get(i);
					ProdutoVO produtoVO = ProdutoVO.create(produto);
					produtoVOs[i] = produtoVO;
				}
				responseDTO.set("produtosPorQuantidadeAcimaDeEPrecoAbaixoDe", produtoVOs);
			}else{
				responseDTO.set("produtosPorQuantidadeAcimaDeEPrecoAbaixoDe", new ProdutoVO[0]);
			}
		}catch(Exception e){
			responseDTO.set("produtosPorQuantidadeAcimaDeEPrecoAbaixoDe", new ProdutoVO[0]);
		}
		return responseDTO;
	}
}