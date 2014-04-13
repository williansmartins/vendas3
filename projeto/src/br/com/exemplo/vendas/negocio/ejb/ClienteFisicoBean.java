package br.com.exemplo.vendas.negocio.ejb ;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteFisicoLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteFisicoRemote;
import br.com.exemplo.vendas.negocio.entity.ClienteFisico;
import br.com.exemplo.vendas.negocio.model.vo.ClienteFisicoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ClienteFisicoBean implements ClienteFisicoRemote, ClienteFisicoLocal {
	
	@PersistenceContext(unitName = "Vendas")
	EntityManager em ;

	public ServiceDTO inserirClienteFisico(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteFisicoVO vo = (ClienteFisicoVO) requestDTO.get("clienteFisicoVO");
		if(vo != null) {
			ClienteFisico clienteFisico = ClienteFisico.create(vo);
			if(DaoFactory.getClienteFisicoDAO(em).inserir(clienteFisico)){
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO ;
	}

	public ServiceDTO alterarClienteFisico(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteFisicoVO vo = (ClienteFisicoVO) requestDTO.get("clienteFisicoVO");
		if (vo != null) {
			ClienteFisico clienteFisico = ClienteFisico.create(vo);
			if(DaoFactory.getClienteFisicoDAO(em).alterar(clienteFisico)){
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO excluirClienteFisico(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteFisicoVO vo = (ClienteFisicoVO) requestDTO.get("clienteFisicoVO");
		if(vo != null){
			try{
				ClienteFisico clienteFisico = DaoFactory.getClienteFisicoDAO(em).localizarPorLogin(vo.getLogin());
				if(DaoFactory.getClienteFisicoDAO(em).excluir(clienteFisico)){
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
	
	public ServiceDTO excluirClienteFisicoPorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		String login = (String) requestDTO.get("loginClienteFisico");
		if(login != null){
			if(DaoFactory.getClienteFisicoDAO(em).excluir(login)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO;
	}

	public ServiceDTO selecionarTodosClientesFisicos(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		List<ClienteFisico> clienteFisicos = DaoFactory.getClienteFisicoDAO(em).listar();
		if((clienteFisicos != null) && (!clienteFisicos.isEmpty())) {
			ClienteFisicoVO[] clienteFisicoVOs = new ClienteFisicoVO[clienteFisicos.size()];
			for(int i = 0; i < clienteFisicos.size(); i++){
				ClienteFisico clienteFisico = (ClienteFisico) clienteFisicos.get(i);
				ClienteFisicoVO clienteFisicoVO = ClienteFisicoVO.create(clienteFisico);
				clienteFisicoVOs[i] = clienteFisicoVO;
			}
			responseDTO.set("listaClienteFisico", clienteFisicoVOs);
		}else{
			responseDTO.set("listaClienteFisico", new ClienteFisicoVO[0]);
		}
		return responseDTO;
	}

	public ServiceDTO getClienteFisico(ServiceDTO requestDTO, String login) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			ClienteFisico clienteFisico = DaoFactory.getClienteFisicoDAO(em).localizarPorLogin(login);
			if(clienteFisico != null) {
				ClienteFisicoVO clienteFisicoVO = ClienteFisicoVO.create(clienteFisico);
				responseDTO.set("getClienteFisico", clienteFisicoVO);
			}else{
				responseDTO.set("getClienteFisico", null);
			}
		}catch(Exception e){
			responseDTO.set("getClienteFisico", null);
		}
		return responseDTO;
	}
}