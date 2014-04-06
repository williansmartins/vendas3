package br.com.exemplo.vendas.negocio.ejb ;

import java.util.List ;

import javax.ejb.Stateless ;
import javax.persistence.EntityManager ;
import javax.persistence.PersistenceContext ;

import br.com.exemplo.vendas.negocio.dao.DaoFactory ;
import br.com.exemplo.vendas.negocio.ejb.interfaces.CompraLocal ;
import br.com.exemplo.vendas.negocio.ejb.interfaces.CompraRemote ;
import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.negocio.entity.Compra ;
import br.com.exemplo.vendas.negocio.entity.Reserva;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO ;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO ;
import br.com.exemplo.vendas.util.exception.LayerException ;

@Stateless
public class CompraBean implements CompraRemote, CompraLocal {
	@PersistenceContext(unitName = "Vendas")
	EntityManager em ;

	public ServiceDTO inserirCompra(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		CompraVO vo = (CompraVO) requestDTO.get("compraVO");
		if(vo != null) {
			try{
				Cliente cliente = DaoFactory.getClienteDAO(em).localizarPorLogin(vo.getLoginCliente());
				Reserva reserva = DaoFactory.getReservaDAO(em).localizarPorCodigo(vo.getCodigoReserva());
				Compra compra = Compra.create(vo);
				compra.setCliente(cliente);
				compra.setReserva(reserva);
				if(DaoFactory.getCompraDAO(em).inserir(compra)) {
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

	public ServiceDTO alterarCompra(ServiceDTO requestDTO) throws LayerException {
		/*ServiceDTO responseDTO = new ServiceDTO();
		CompraVO vo = (CompraVO) requestDTO.get("compraVO");
		if (vo != null) {
			Compra compra = new Compra();
			compra.setLogin(vo.getLogin());
			compra.setSenha(vo.getSenha());
			compra.setGrupo(vo.getGrupo());
			compra.setPerfil(vo.getPerfil());
			compra.setBloqueado(Boolean.valueOf(vo.getBloqueado()));
			compra.setUltimoAcesso(vo.getUltimoAcesso());
			compra.setCodigo(vo.getCodigo());
			compra.setNome(vo.getNome());
			compra.setEndereco(vo.getEndereco());
			compra.setTelefone(vo.getTelefone());
			compra.setSituacao(vo.getSituacao());
			if(DaoFactory.getCompraDAO(em).alterar(compra)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		
		return responseDTO;
		*/
		return null;
	}

	public ServiceDTO excluirCompra( ServiceDTO requestDTO ) throws LayerException {
		/*
		ServiceDTO responseDTO = new ServiceDTO();
		CompraVO vo = (CompraVO) requestDTO.get("compraVO");
		if(vo != null) {
			Compra compra = new Compra();
			compra.setLogin(vo.getLogin());
			if(DaoFactory.getCompraDAO(em).excluir(compra)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
		*/
		return null;
	}

	public ServiceDTO selecionarTodosCompras(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<Compra> lista = DaoFactory.getCompraDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			CompraVO[] compras = new CompraVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				Compra compra = (Compra) lista.get(i);
				CompraVO compraVO = CompraVO.create(compra);
				compraVO.setCodigoReserva(compra.getReserva().getCodigo());
				ReservaVO reservaVO = ReservaVO.create(compra.getReserva());
				compraVO.setReservaVO(reservaVO);
				compraVO.setLoginCliente(compra.getCliente().getLogin());
				ClienteVO clienteVO = ClienteVO.create(compra.getCliente());
				compraVO.setClienteVO(clienteVO);
				compras[i] = compraVO;
			}
			responseDTO.set("listaCompra", compras);
		}else{
			responseDTO.set("listaCompra", new CompraVO[0]);
		}
		return responseDTO ;
	}

	public ServiceDTO getCompra(ServiceDTO requestDTO, Long numero) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			Compra compra = DaoFactory.getCompraDAO(em).localizarPorNumero(numero);
			CompraVO compraVO = CompraVO.create(compra);
			compraVO.setCodigoReserva(compra.getReserva().getCodigo());
			ReservaVO reservaVO = ReservaVO.create(compra.getReserva());
			compraVO.setReservaVO(reservaVO);
			compraVO.setLoginCliente(compra.getCliente().getLogin());
			ClienteVO clienteVO = ClienteVO.create(compra.getCliente());
			compraVO.setClienteVO(clienteVO);
			responseDTO.set("getCompra", compraVO);
		}catch(Exception e){
			responseDTO.set("getCompra", null);
		}
		return responseDTO;
	}
}