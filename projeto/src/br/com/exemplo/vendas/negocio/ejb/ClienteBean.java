package br.com.exemplo.vendas.negocio.ejb ;

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
			Cliente cliente = new Cliente();
			cliente.setLogin(vo.getLogin());
			cliente.setSenha(vo.getSenha());
			cliente.setGrupo(vo.getGrupo());
			cliente.setPerfil(vo.getPerfil());
			cliente.setBloqueado(Boolean.valueOf(vo.getBloqueado()));
			cliente.setUltimoAcesso(vo.getUltimoAcesso());
			cliente.setCodigo(vo.getCodigo());
			cliente.setNome(vo.getNome());
			cliente.setEndereco(vo.getEndereco());
			cliente.setTelefone(vo.getTelefone());
			cliente.setSituacao(vo.getSituacao());
			if(DaoFactory.getClienteDAO(em).inserir(cliente)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO ;
	}

	public ServiceDTO alterarCliente(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteVO vo = (ClienteVO) requestDTO.get("clienteVO");
		if (vo != null) {
			Cliente cliente = new Cliente();
			cliente.setLogin(vo.getLogin());
			cliente.setSenha(vo.getSenha());
			cliente.setGrupo(vo.getGrupo());
			cliente.setPerfil(vo.getPerfil());
			cliente.setBloqueado(Boolean.valueOf(vo.getBloqueado()));
			cliente.setUltimoAcesso(vo.getUltimoAcesso());
			cliente.setCodigo(vo.getCodigo());
			cliente.setNome(vo.getNome());
			cliente.setEndereco(vo.getEndereco());
			cliente.setTelefone(vo.getTelefone());
			cliente.setSituacao(vo.getSituacao());
			if(DaoFactory.getClienteDAO(em).alterar(cliente)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO excluirCliente( ServiceDTO requestDTO ) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		ClienteVO vo = (ClienteVO) requestDTO.get("clienteVO");
		if(vo != null) {
			Cliente cliente = new Cliente();
			cliente.setLogin(vo.getLogin());
			if(DaoFactory.getClienteDAO(em).excluir(cliente)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO selecionarTodosCliente(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		Cliente cliente = null ;
		List<Cliente> lista = DaoFactory.getClienteDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			ClienteVO[] clientes = new ClienteVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				cliente = (Cliente) lista.get(i);
				ClienteVO clienteVO = new ClienteVO();
				clienteVO.setLogin(cliente.getLogin());
				clienteVO.setSenha(cliente.getSenha());
				clienteVO.setGrupo(cliente.getGrupo());
				clienteVO.setPerfil(cliente.getPerfil());
				clienteVO.setBloqueado(cliente.getBloqueado());
				clienteVO.setUltimoAcesso(cliente.getUltimoAcesso());
				clienteVO.setCodigo(cliente.getCodigo());
				clienteVO.setNome(cliente.getNome());
				clienteVO.setEndereco(cliente.getEndereco());
				clienteVO.setTelefone(cliente.getTelefone());
				clienteVO.setSituacao(cliente.getSituacao());
				clientes[ i ] = clienteVO ;
			}
			responseDTO.set("listaCliente", clientes);
		}
		return responseDTO ;
	}

	public ServiceDTO getCliente(ServiceDTO requestDTO, String login) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		Cliente cliente = new Cliente();
		cliente.setLogin(login);
		Cliente lista = DaoFactory.getClienteDAO(em).localizarPorLogin(cliente);
		if(lista != null) {
			cliente = (Cliente) lista;
			ClienteVO clienteVO = new ClienteVO();
			clienteVO.setLogin(cliente.getLogin());
			clienteVO.setSenha(cliente.getSenha());
			clienteVO.setGrupo(cliente.getGrupo());
			clienteVO.setPerfil(cliente.getPerfil());
			clienteVO.setBloqueado(cliente.getBloqueado());
			clienteVO.setUltimoAcesso(cliente.getUltimoAcesso());
			clienteVO.setCodigo(cliente.getCodigo());
			clienteVO.setNome(cliente.getNome());
			clienteVO.setEndereco(cliente.getEndereco());
			clienteVO.setTelefone(cliente.getTelefone());
			clienteVO.setSituacao(cliente.getSituacao());
			responseDTO.set("getCliente", clienteVO);
		}
		return responseDTO;
	}
}