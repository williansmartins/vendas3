package br.com.exemplo.vendas.negocio.ejb ;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.CompraLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.CompraRemote;
import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.negocio.entity.Compra;
import br.com.exemplo.vendas.negocio.entity.Reserva;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class CompraBean implements CompraRemote, CompraLocal {
	@PersistenceContext(unitName = "Vendas")
	EntityManager em ;

	public ServiceDTO inserirCompra(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		CompraVO vo = (CompraVO) requestDTO.get("compraVO");
		if(vo != null) {
			try{
				Cliente cliente = DaoFactory.getClienteDAO(em).localizarPorLogin(vo.getCliente().getLogin());
				Reserva reserva = DaoFactory.getReservaDAO(em).localizarPorCodigo(vo.getReserva().getCodigo());
				Compra compra = Compra.create(vo);
				compra.setCliente(cliente);
				compra.setReserva(reserva);
				if(DaoFactory.getCompraDAO(em).inserir(compra)) {
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
		ServiceDTO responseDTO = new ServiceDTO();
		CompraVO vo = (CompraVO) requestDTO.get("compraVO");
		if(vo != null){
			try{
				Compra compra = DaoFactory.getCompraDAO(em).localizarPorNumero(vo.getNumero());
				if(DaoFactory.getCompraDAO(em).excluir(compra)) {
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
	
	public ServiceDTO excluirCompraPorNumero(ServiceDTO requestDTO) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		Long numero = (Long) requestDTO.get("numeroCompra");
		if(numero != null){
			if(DaoFactory.getCompraDAO(em).excluir(numero)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO;
	}

	public ServiceDTO selecionarTodosCompras(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<Compra> lista = DaoFactory.getCompraDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())){
			CompraVO[] compras = new CompraVO[lista.size()];
			for(int i = 0; i < lista.size(); i++){
				Compra compra = (Compra) lista.get(i);
				CompraVO compraVO = Compra.create(compra);
				ReservaVO reservaVO = ReservaVO.create(compra.getReserva());
				reservaVO.setCodigo(compra.getReserva().getCodigo());
				compraVO.setReserva(reservaVO);
				ClienteVO clienteVO = Cliente.create(compra.getCliente());
				clienteVO.setLogin(compra.getCliente().getLogin());
				compraVO.setCliente(clienteVO);
				compras[i] = compraVO;
			}
			responseDTO.set("listaCompra", compras);
		}else{
			responseDTO.set("listaCompra", new CompraVO[0]);
		}
		return responseDTO ;
	}

	public ServiceDTO getCompra(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			CompraVO vo = (CompraVO) requestDTO.get("compraVO");
			Compra compra = DaoFactory.getCompraDAO(em).localizarPorNumero(vo.getNumero());
			CompraVO compraVO = Compra.create(compra);
			ReservaVO reservaVO = ReservaVO.create(compra.getReserva());
			reservaVO.setCodigo(compra.getReserva().getCodigo());
			compraVO.setReserva(reservaVO);
			ClienteVO clienteVO = Cliente.create(compra.getCliente());
			clienteVO.setLogin(compra.getCliente().getLogin());
			compraVO.setCliente(clienteVO);
			responseDTO.set("getCompra", compraVO);
		}catch(Exception e){
			responseDTO.set("getCompra", null);
		}
		return responseDTO;
	}
	
	public ServiceDTO localizarComprasPorValorAbaixoDe(ServiceDTO requestDTO) throws LayerException, RemoteException {
		BigDecimal valor = (BigDecimal) requestDTO.get("comprasPorValorAbaixoDe500");
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			List<Compra> compras = DaoFactory.getCompraDAO(em).localizarPorValorAbaixoDe(valor);
			if((compras != null) && (!compras.isEmpty())) {
				CompraVO[] compraVOs = new CompraVO[compras.size()];
				for(int i = 0; i < compras.size(); i++) {
					Compra compra = (Compra) compras.get(i);
					CompraVO compraVO = Compra.create(compra);
					ReservaVO reservaVO = ReservaVO.create(compra.getReserva());
					reservaVO.setCodigo(compra.getReserva().getCodigo());
					compraVO.setReserva(reservaVO);
					ClienteVO clienteVO = Cliente.create(compra.getCliente());
					clienteVO.setLogin(compra.getCliente().getLogin());
					compraVO.setCliente(clienteVO);
					compraVOs[i] = compraVO;
				}
				responseDTO.set("compraVOs", compraVOs);
			}else{
				responseDTO.set("compraVOs", new CompraVO[0]);
			}
		}catch(Exception e){
			responseDTO.set("compraVOs", new CompraVO[0]);
		}
		return responseDTO;
	}
}