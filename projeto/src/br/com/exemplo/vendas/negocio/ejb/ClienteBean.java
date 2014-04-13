package br.com.exemplo.vendas.negocio.ejb ;

import java.rmi.RemoteException;
import java.util.List ;

import javax.ejb.Stateless ;
import javax.persistence.EntityManager ;
import javax.persistence.PersistenceContext ;

import br.com.exemplo.vendas.negocio.dao.DaoFactory ;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteLocal ;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteRemote ;
import br.com.exemplo.vendas.negocio.entity.Cliente ;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO ;
import br.com.exemplo.vendas.util.dto.ServiceDTO ;
import br.com.exemplo.vendas.util.exception.LayerException ;

@Stateless
public class ClienteBean implements ClienteRemote, ClienteLocal {
	
	@PersistenceContext(unitName = "Vendas")
	EntityManager em ;

	public ServiceDTO inserirCliente(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteVO vo = (ClienteVO) requestDTO.get("clienteVO");
		if(vo != null) {
			Cliente cliente = Cliente.create(vo);
			if(DaoFactory.getClienteDAO(em).inserir(cliente)){
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO ;
	}

	public ServiceDTO alterarCliente(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteVO vo = (ClienteVO) requestDTO.get("clienteVO");
		if (vo != null) {
			Cliente cliente = Cliente.create(vo);
			if(DaoFactory.getClienteDAO(em).alterar(cliente)){
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO excluirCliente(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteVO vo = (ClienteVO) requestDTO.get("clienteVO");
		if(vo != null){
			try{
				Cliente cliente = DaoFactory.getClienteDAO(em).localizarPorLogin(vo.getLogin());
				if(DaoFactory.getClienteDAO(em).excluir(cliente)){
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
	
	public ServiceDTO excluirClientePorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		String login = (String) requestDTO.get("loginCliente");
		if(login != null){
			if(DaoFactory.getClienteDAO(em).excluir(login)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO;
	}

	public ServiceDTO selecionarTodosClientes(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<Cliente> clientes = DaoFactory.getClienteDAO(em).listar();
		if((clientes != null) && (!clientes.isEmpty())) {
			ClienteVO[] clienteVOs = new ClienteVO[clientes.size()];
			for(int i = 0; i < clientes.size(); i++){
				Cliente cliente = (Cliente) clientes.get(i);
				ClienteVO clienteVO = ClienteVO.create(cliente);
				clienteVOs[i] = clienteVO;
			}
			responseDTO.set("listaCliente", clienteVOs);
		}else{
			responseDTO.set("listaCliente", new ClienteVO[0]);
		}
		return responseDTO;
	}

	public ServiceDTO getCliente(ServiceDTO requestDTO, String login) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			Cliente cliente = DaoFactory.getClienteDAO(em).localizarPorLogin(login);
			if(cliente != null) {
				ClienteVO clienteVO = ClienteVO.create(cliente);
				responseDTO.set("getCliente", clienteVO);
			}else{
				responseDTO.set("getCliente", null);
			}
		}catch(Exception e){
			responseDTO.set("getCliente", null);
		}
		return responseDTO;
	}

	@Override
	public ServiceDTO localizarClientesPorCompra(ServiceDTO requestDTO) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			List<Cliente> clientes = DaoFactory.getClienteDAO(em).localizarPorCompra();
			if((clientes != null) && (!clientes.isEmpty())) {
				ClienteVO[] clienteVOs = new ClienteVO[clientes.size()];
				for(int i = 0; i < clientes.size(); i++){
					Cliente cliente = (Cliente) clientes.get(i);
					ClienteVO clienteVO = ClienteVO.create(cliente);
					clienteVOs[i] = clienteVO;
				}
				responseDTO.set("clientesPorCompra", clienteVOs);
			}else{
				responseDTO.set("clientesPorCompra", new ClienteVO[0]);
			}
		}catch(Exception e){
			responseDTO.set("clientesPorCompra", new ClienteVO[0]);
		}
		return responseDTO;
	}
}