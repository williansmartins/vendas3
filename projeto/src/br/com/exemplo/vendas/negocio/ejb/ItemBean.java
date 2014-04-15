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
		ItemVO vo = (ItemVO) requestDTO.get("itemVO");
		if(vo != null) {
			Item item = Item.create(vo);
			try{
				Reserva reserva = DaoFactory.getReservaDAO(em).localizarPorCodigo(vo.getCodigoReserva());
				item.setReserva(reserva);
			}catch(Exception e){
				e.printStackTrace();
				return new ServiceDTO("resposta", new Boolean(false));
			}
			try{
				Produto produto = DaoFactory.getProdutoDAO(em).localizarPorCodigo(vo.getCodigoPrduto());
				item.setProduto(produto);
			}catch(Exception e){
				e.printStackTrace();
				return new ServiceDTO("resposta", new Boolean(false));
			}
			try{
				Compra compra = DaoFactory.getCompraDAO(em).localizarPorNumero(vo.getNumeroCompra());
				item.setCompra(compra);
			}catch(Exception e){
				e.printStackTrace();
				return new ServiceDTO("resposta", new Boolean(false));
			}
			if(DaoFactory.getItemDAO(em).inserir(item)) {
				return new ServiceDTO("resposta", new Boolean(true));
			}else{
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}

	public ServiceDTO alterarItem(ServiceDTO requestDTO) throws LayerException {
		ItemVO vo = (ItemVO) requestDTO.get("itemVO");
		if(vo != null){
			Item item = Item.create(vo);
			try{
				Reserva reserva = DaoFactory.getReservaDAO(em).localizarPorCodigo(vo.getCodigoReserva());
				item.setReserva(reserva);
			}catch(Exception e){
				e.printStackTrace();
				return new ServiceDTO("resposta", new Boolean(false));
			}
			try{
				Produto produto = DaoFactory.getProdutoDAO(em).localizarPorCodigo(vo.getCodigoPrduto());
				item.setProduto(produto);
			}catch(Exception e){
				e.printStackTrace();
				return new ServiceDTO("resposta", new Boolean(false));
			}
			try{
				Compra compra = DaoFactory.getCompraDAO(em).localizarPorNumero(vo.getNumeroCompra());
				item.setCompra(compra);
			}catch(Exception e){
				e.printStackTrace();
				return new ServiceDTO("resposta", new Boolean(false));
			}
			if(DaoFactory.getItemDAO(em).alterar(item)) {
				return new ServiceDTO("resposta", new Boolean(true));
			}else{
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}

	public ServiceDTO excluirItem(ServiceDTO requestDTO) throws LayerException {
		ItemVO vo = (ItemVO) requestDTO.get("itemVO");
		if(vo != null){
			Item item = DaoFactory.getItemDAO(em).localizar(vo.getId());
			if(DaoFactory.getItemDAO(em).excluir(item)){
				return new ServiceDTO("listaItem", new Boolean(true));
			}else{
				return new ServiceDTO("listaItem", new Boolean(false));
			}
		}else{
			return new ServiceDTO("listaItem", new Boolean(false));
		}
	}
	
	public ServiceDTO excluirItemPorId(ServiceDTO requestDTO) throws LayerException {
		Long id = (Long) requestDTO.get("idItem");
		if(id != null){
			Item item = DaoFactory.getItemDAO(em).localizar(id);
			if(DaoFactory.getItemDAO(em).excluir(item)){
				return new ServiceDTO("listaItem", new Boolean(true));
			}else{
				return new ServiceDTO("listaItem", new Boolean(false));
			}
		}else{
			return new ServiceDTO("listaItem", new Boolean(false));
		}
	}
	
	public ServiceDTO excluirItemPorReservaCompraProduto(ServiceDTO requestDTO) throws LayerException {
		ItemVO vo = (ItemVO) requestDTO.get("itemVO");
		if(vo != null){
			try{
				Item item = DaoFactory.getItemDAO(em).localizarPorReservaCompraProduto(vo.getCodigoReserva(), vo.getNumeroCompra(), vo.getCodigoPrduto());
				if(DaoFactory.getItemDAO(em).excluir(item)){
					return new ServiceDTO("listaItem", new Boolean(true));
				}else{
					return new ServiceDTO("listaItem", new Boolean(false));
				}
			}catch(Exception e){
				e.printStackTrace();
				return new ServiceDTO("listaItem", new Boolean(false));
			}
		}else{
			return new ServiceDTO("listaItem", new Boolean(false));
		}
	}

	public ServiceDTO selecionarTodosItens(ServiceDTO requestDTO) throws LayerException {
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
			return new ServiceDTO("listaItem", items);
		}else{
			return new ServiceDTO("listaItem", new ItemVO[0]);
		}
	}

	public ServiceDTO getItem(ServiceDTO requestDTO, Long codigoReserva, Long numeroCompra, Long codigoProduto) throws LayerException {
		try{
			Item item = DaoFactory.getItemDAO(em).localizarPorReservaCompraProduto(codigoReserva, numeroCompra, codigoProduto);
			ItemVO itemVO = ItemVO.create(item);
			itemVO.setCodigoReserva(item.getReserva().getCodigo());
			ReservaVO reservaVO = ReservaVO.create(item.getReserva());
			itemVO.setReservaVO(reservaVO);
			CompraVO compraVO = CompraVO.create(item.getCompra());
			itemVO.setCompraVO(compraVO);
			ProdutoVO produtoVO = ProdutoVO.create(item.getProduto());
			itemVO.setProdutoVO(produtoVO);
			return new ServiceDTO("listaItem", itemVO);
		}catch(Exception e){
			e.printStackTrace();
			return new ServiceDTO("listaItem", null);
		}
	}
}