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
		ClienteVO vo = (ClienteVO) requestDTO.get("clienteVO");
		if(vo != null) {
			Cliente cliente = Cliente.create(vo);
			if(DaoFactory.getClienteDAO(em).inserir(cliente)){
				return new ServiceDTO("resposta", new Boolean(true));
			}else{
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}

	public ServiceDTO alterarCliente(ServiceDTO requestDTO) throws LayerException {
		ClienteVO vo = (ClienteVO) requestDTO.get("clienteVO");
		if(vo != null){
			Cliente cliente = Cliente.create(vo);
			if(DaoFactory.getClienteDAO(em).alterar(cliente)){
				return new ServiceDTO("resposta", new Boolean(true));
			}else{
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}

	public ServiceDTO excluirCliente(ServiceDTO requestDTO) throws LayerException {
		ClienteVO vo = (ClienteVO) requestDTO.get("clienteVO");
		if(vo != null){
			try{
				Cliente cliente = DaoFactory.getClienteDAO(em).localizarPorLogin(vo.getLogin());
				if(DaoFactory.getClienteDAO(em).excluir(cliente)){
					return new ServiceDTO("resposta", new Boolean(true));
				}else{
					return new ServiceDTO("resposta", new Boolean(false));
				}
			}catch(Exception e){
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}
	
	public ServiceDTO excluirClientePorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException {
		String login = (String) requestDTO.get("loginCliente");
		if(login != null){
			if(DaoFactory.getClienteDAO(em).excluir(login)){
				return new ServiceDTO("resposta", new Boolean(true));
			}else{
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}

	public ServiceDTO selecionarTodosClientes(ServiceDTO requestDTO) throws LayerException {
		List<Cliente> clientes = DaoFactory.getClienteDAO(em).listar();
		if((clientes != null) && (!clientes.isEmpty())) {
			ClienteVO[] clienteVOs = new ClienteVO[clientes.size()];
			for(int i = 0; i < clientes.size(); i++){
				Cliente cliente = (Cliente) clientes.get(i);
				ClienteVO clienteVO = Cliente.create(cliente);
				clienteVOs[i] = clienteVO;
			}
			return new ServiceDTO("resposta", clienteVOs);
		}else{
			return new ServiceDTO("resposta", new ClienteVO[0]);
		}
	}

	public ServiceDTO getCliente(ServiceDTO requestDTO, String login) throws LayerException {
		try{
			Cliente cliente = DaoFactory.getClienteDAO(em).localizarPorLogin(login);
			if(cliente != null) {
				ClienteVO clienteVO = Cliente.create(cliente);
				return new ServiceDTO("resposta", clienteVO);
			}else{
				return new ServiceDTO("resposta", null);
			}
		}catch(Exception e){
			return new ServiceDTO("resposta", null);
		}
	}

	@Override
	public ServiceDTO localizarClientesPorCompra(ServiceDTO requestDTO) throws LayerException, RemoteException {
		try{
			List<Cliente> clientes = DaoFactory.getClienteDAO(em).localizarPorCompra();
			if((clientes != null) && (!clientes.isEmpty())) {
				ClienteVO[] clienteVOs = new ClienteVO[clientes.size()];
				for(int i = 0; i < clientes.size(); i++){
					Cliente cliente = (Cliente) clientes.get(i);
					ClienteVO clienteVO = Cliente.create(cliente);
					clienteVOs[i] = clienteVO;
				}
				return new ServiceDTO("clientesPorCompraRealizadas", clienteVOs);
			}else{
				return new ServiceDTO("clientesPorCompraRealizadas", new ClienteVO[0]);
			}
		}catch(Exception e){
			return new ServiceDTO("clientesPorCompraRealizadas", new ClienteVO[0]);
		}
	}
}