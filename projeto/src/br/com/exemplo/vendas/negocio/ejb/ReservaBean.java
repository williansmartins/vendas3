package br.com.exemplo.vendas.negocio.ejb ;

import java.rmi.RemoteException;
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
			try{
				Cliente cliente = DaoFactory.getClienteDAO(em).localizarPorLogin(vo.getCliente().getLogin());
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
	
	public ServiceDTO excluirReserva(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ReservaVO vo = (ReservaVO) requestDTO.get("reservaVO");
		if(vo != null){
			try{
				Reserva reserva = DaoFactory.getReservaDAO(em).localizarPorCodigo(vo.getCodigo());
				if(DaoFactory.getReservaDAO(em).excluir(reserva)) {
					responseDTO.set("resposta", new Boolean(true));
				}else{
					responseDTO.set("resposta", new Boolean(false));
				}
			}catch(Exception e){
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO;
	}
	
	public ServiceDTO excluirReservaPorCodigo(ServiceDTO requestDTO) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		Long codigo = (Long) requestDTO.get("codigoReserva");
		if(codigo != null){
			if(DaoFactory.getReservaDAO(em).excluir(codigo)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO;
	}
	
	public ServiceDTO selecionarTodasReservas(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<Reserva> lista = DaoFactory.getReservaDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			ReservaVO[] reservaVOs = new ReservaVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				Reserva reserva = (Reserva) lista.get(i);
				ReservaVO reservaVO = ReservaVO.create(reserva);
				reservaVO.setCliente(Cliente.create(reserva.getCliente()));
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
			reservaVO.setCliente(Cliente.create(reserva.getCliente()));
			responseDTO.set("getReserva", reservaVO);
		}catch(Exception e){
			responseDTO.set("getReserva", null);
		}
		return responseDTO;
	}
}