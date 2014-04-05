package br.com.exemplo.vendas.negocio.ejb ;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ReservaLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ReservaRemote;
import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.negocio.entity.Reserva;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ReservaBean implements ReservaRemote, ReservaLocal {
	@PersistenceContext(unitName = "Vendas")
	EntityManager em ;

	public ServiceDTO inserirReserva(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ReservaVO vo = (ReservaVO) requestDTO.get("reservaVO");
		if(vo != null) {
			Cliente cliente = null;
			try{
				cliente = DaoFactory.getClienteDAO(em).localizarPorLogin(vo.getLoginCliente());
				Reserva reserva = Reserva.create(vo);
				reserva.setCliente(cliente);
				if(DaoFactory.getReservaDAO(em).inserir(reserva)) {
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
	
	public ServiceDTO alterarReserva(ServiceDTO requestDTO) throws LayerException {
		return null;
	}
	
	public ServiceDTO excluirReserva( ServiceDTO requestDTO ) throws LayerException {
		return null;
	}
	
	public ServiceDTO selecionarTodosReserva(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<Reserva> lista = DaoFactory.getReservaDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			ReservaVO[] reservaVOs = new ReservaVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				Reserva reserva = (Reserva) lista.get(i);
				ReservaVO reservaVO = new ReservaVO();
				reservaVO.setCodigo(reserva.getCodigo());
				reservaVO.setData(reserva.getData());
				reservaVO.setAtendente(reserva.getAtendente());
				reservaVO.setSituacao(reserva.getSituacao());
				reservaVO.setValor(reserva.getValor());
				reservaVO.setClienteVO(ClienteVO.create(reserva.getCliente()));
				reservaVO.setLoginCliente(reserva.getCliente().getLogin());
				reservaVOs[i] = reservaVO;
			}
			responseDTO.set("listaReserva", reservaVOs);
		}else{
			responseDTO.set("listaReserva", new ReservaVO[0]);
		}
		return responseDTO;
	}
	
	public ServiceDTO getReserva(ServiceDTO requestDTO, Long codigo) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			Reserva reserva = DaoFactory.getReservaDAO(em).localizarPorCodigo(codigo);
			ReservaVO reservaVO = new ReservaVO();
			reservaVO.setCodigo(reserva.getCodigo());
			reservaVO.setData(reserva.getData());
			reservaVO.setAtendente(reserva.getAtendente());
			reservaVO.setSituacao(reserva.getSituacao());
			reservaVO.setValor(reserva.getValor());
			reservaVO.setClienteVO(ClienteVO.create(reserva.getCliente()));
			reservaVO.setLoginCliente(reserva.getCliente().getLogin());
			responseDTO.set("getReserva", reservaVO);
		}catch(Exception e){
			responseDTO.set("getReserva", null);
		}
		return responseDTO;
	}
/*
	public ServiceDTO alterarReserva(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ReservaVO vo = (ReservaVO) requestDTO.get("produtoVO");
		if (vo != null) {
			Reserva produto = new Reserva();
			produto.setCodigo(vo.getCodigo());
			produto.setDescricao(vo.getDescricao());
			produto.setPreco(vo.getPreco());
			produto.setEstoque(vo.getEstoque());
			if(DaoFactory.getReservaDAO(em).alterar(produto)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO excluirReserva( ServiceDTO requestDTO ) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ReservaVO vo = (ReservaVO) requestDTO.get("produtoVO");
		if(vo != null) {
			Reserva produto = new Reserva();
			produto.setCodigo(vo.getCodigo());
			if(DaoFactory.getReservaDAO(em).excluir(produto)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}*/
}