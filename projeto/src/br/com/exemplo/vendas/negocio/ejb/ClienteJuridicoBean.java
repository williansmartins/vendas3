package br.com.exemplo.vendas.negocio.ejb ;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteJuridicoLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteJuridicoRemote;
import br.com.exemplo.vendas.negocio.entity.ClienteJuridico;
import br.com.exemplo.vendas.negocio.model.vo.ClienteJuridicoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ClienteJuridicoBean implements ClienteJuridicoRemote, ClienteJuridicoLocal {
	
	@PersistenceContext(unitName = "Vendas")
	EntityManager em ;

	public ServiceDTO inserirClienteJuridico(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteJuridicoVO vo = (ClienteJuridicoVO) requestDTO.get("clienteJuridicoVO");
		if(vo != null) {
			ClienteJuridico clienteJuridico = ClienteJuridico.create(vo);
			if(DaoFactory.getClienteJuridicoDAO(em).inserir(clienteJuridico)){
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO ;
	}

	public ServiceDTO alterarClienteJuridico(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteJuridicoVO vo = (ClienteJuridicoVO) requestDTO.get("clienteJuridicoVO");
		if (vo != null) {
			ClienteJuridico clienteJuridico = ClienteJuridico.create(vo);
			if(DaoFactory.getClienteJuridicoDAO(em).alterar(clienteJuridico)){
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO excluirClienteJuridico(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteJuridicoVO vo = (ClienteJuridicoVO) requestDTO.get("clienteJuridicoVO");
		if(vo != null){
			try{
				ClienteJuridico clienteJuridico = DaoFactory.getClienteJuridicoDAO(em).localizarPorLogin(vo.getLogin());
				if(DaoFactory.getClienteJuridicoDAO(em).excluir(clienteJuridico)){
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
	
	public ServiceDTO excluirClienteJuridicoPorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		String login = (String) requestDTO.get("loginClienteJuridico");
		if(login != null){
			if(DaoFactory.getClienteJuridicoDAO(em).excluir(login)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO;
	}

	public ServiceDTO selecionarTodosClientesJuridicos(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<ClienteJuridico> clienteJuridicos = DaoFactory.getClienteJuridicoDAO(em).listar();
		if((clienteJuridicos != null) && (!clienteJuridicos.isEmpty())) {
			ClienteJuridicoVO[] clienteJuridicoVOs = new ClienteJuridicoVO[clienteJuridicos.size()];
			for(int i = 0; i < clienteJuridicos.size(); i++){
				ClienteJuridico clienteJuridico = (ClienteJuridico) clienteJuridicos.get(i);
				ClienteJuridicoVO clienteJuridicoVO = ClienteJuridicoVO.create(clienteJuridico);
				clienteJuridicoVOs[i] = clienteJuridicoVO;
			}
			responseDTO.set("listaClienteJuridico", clienteJuridicoVOs);
		}else{
			responseDTO.set("listaClienteJuridico", new ClienteJuridicoVO[0]);
		}
		return responseDTO;
	}

	public ServiceDTO getClienteJuridico(ServiceDTO requestDTO, String login) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			ClienteJuridico clienteJuridico = DaoFactory.getClienteJuridicoDAO(em).localizarPorLogin(login);
			if(clienteJuridico != null) {
				ClienteJuridicoVO clienteJuridicoVO = ClienteJuridicoVO.create(clienteJuridico);
				responseDTO.set("getClienteJuridico", clienteJuridicoVO);
			}else{
				responseDTO.set("getClienteJuridico", null);
			}
		}catch(Exception e){
			responseDTO.set("getClienteJuridico", null);
		}
		return responseDTO;
	}
}