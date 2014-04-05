package br.com.exemplo.vendas.negocio.ejb ;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ItemLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ItemRemote;
import br.com.exemplo.vendas.negocio.entity.Compra;
import br.com.exemplo.vendas.negocio.entity.Item;
import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.negocio.entity.Reserva;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.ItemVO;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ItemBean implements ItemRemote, ItemLocal {
	@PersistenceContext(unitName = "Vendas")
	EntityManager em ;

	public ServiceDTO inserirItem(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ItemVO vo = (ItemVO) requestDTO.get("itemVO");
		if(vo != null) {
			try{
				Reserva reserva = DaoFactory.getReservaDAO(em).localizarPorCodigo(vo.getCodigoReserva());
				Produto produto = DaoFactory.getProdutoDAO(em).localizarPorCodigo(vo.getCodigoPrduto());
				Compra compra = DaoFactory.getCompraDAO(em).localizarPorNumero(vo.getNumeroCompra());
				Item item = Item.create(vo);
				item.setReserva(reserva);
				item.setCompra(compra);
				item.setProduto(produto);
				if(DaoFactory.getItemDAO(em).inserir(item)) {
					responseDTO.set("resposta", new Boolean(true));
				}else{
					responseDTO.set("resposta", new Boolean(false));
				}
			}catch(Exception e){
				e.printStackTrace();
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO alterarItem(ServiceDTO requestDTO) throws LayerException {
		/*ServiceDTO responseDTO = new ServiceDTO();
		ItemVO vo = (ItemVO) requestDTO.get("itemVO");
		if (vo != null) {
			Item item = new Item();
			item.setLogin(vo.getLogin());
			item.setSenha(vo.getSenha());
			item.setGrupo(vo.getGrupo());
			item.setPerfil(vo.getPerfil());
			item.setBloqueado(Boolean.valueOf(vo.getBloqueado()));
			item.setUltimoAcesso(vo.getUltimoAcesso());
			item.setCodigo(vo.getCodigo());
			item.setNome(vo.getNome());
			item.setEndereco(vo.getEndereco());
			item.setTelefone(vo.getTelefone());
			item.setSituacao(vo.getSituacao());
			if(DaoFactory.getItemDAO(em).alterar(item)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		
		return responseDTO;
		*/
		return null;
	}

	public ServiceDTO excluirItem( ServiceDTO requestDTO ) throws LayerException {
		/*
		ServiceDTO responseDTO = new ServiceDTO();
		ItemVO vo = (ItemVO) requestDTO.get("itemVO");
		if(vo != null) {
			Item item = new Item();
			item.setLogin(vo.getLogin());
			if(DaoFactory.getItemDAO(em).excluir(item)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
		*/
		return null;
	}

	public ServiceDTO selecionarTodosItens(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<Item> lista = DaoFactory.getItemDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			ItemVO[] items = new ItemVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				Item item = (Item) lista.get(i);
				ItemVO itemVO = ItemVO.create(item);
				itemVO.setCodigoReserva(item.getReserva().getCodigo());
				ReservaVO reservaVO = ReservaVO.create(item.getReserva());
				itemVO.setReservaVO(reservaVO);
				CompraVO compraVO = CompraVO.create(item.getCompra());
				itemVO.setCompraVO(compraVO);
				ProdutoVO produtoVO = ProdutoVO.create(item.getProduto());
				itemVO.setProdutoVO(produtoVO);
				items[i] = itemVO;
			}
			responseDTO.set("listaItem", items);
		}else{
			responseDTO.set("listaItem", new ItemVO[0]);
		}
		return responseDTO ;
	}

	public ServiceDTO getItem(ServiceDTO requestDTO, Long codigoReserva, Long numeroCompra, Long codigoProduto) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			Item item = DaoFactory.getItemDAO(em).localizar(codigoReserva, numeroCompra, codigoProduto);
			ItemVO itemVO = ItemVO.create(item);
			itemVO.setCodigoReserva(item.getReserva().getCodigo());
			ReservaVO reservaVO = ReservaVO.create(item.getReserva());
			itemVO.setReservaVO(reservaVO);
			CompraVO compraVO = CompraVO.create(item.getCompra());
			itemVO.setCompraVO(compraVO);
			ProdutoVO produtoVO = ProdutoVO.create(item.getProduto());
			itemVO.setProdutoVO(produtoVO);
			responseDTO.set("getItem", itemVO);
		}catch(Exception e){
			responseDTO.set("getItem", null);
		}
		return responseDTO;
	}
}