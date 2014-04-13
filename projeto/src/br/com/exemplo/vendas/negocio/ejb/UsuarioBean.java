package br.com.exemplo.vendas.negocio.ejb ;

import java.rmi.RemoteException;
import java.util.List ;

import javax.ejb.Stateless ;
import javax.persistence.EntityManager ;
import javax.persistence.PersistenceContext ;

import br.com.exemplo.vendas.negocio.dao.DaoFactory ;
import br.com.exemplo.vendas.negocio.ejb.interfaces.UsuarioLocal ;
import br.com.exemplo.vendas.negocio.ejb.interfaces.UsuarioRemote ;
import br.com.exemplo.vendas.negocio.entity.Usuario ;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO ;
import br.com.exemplo.vendas.util.dto.ServiceDTO ;
import br.com.exemplo.vendas.util.exception.LayerException ;

@Stateless
public class UsuarioBean implements UsuarioRemote, UsuarioLocal {
	
	@PersistenceContext(unitName = "Vendas")
	EntityManager em ;

	public ServiceDTO inserirUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		UsuarioVO vo = (UsuarioVO) requestDTO.get("usuarioVO");
		if(vo != null) {
			Usuario usuario = new Usuario(vo.getLogin(), vo.getSenha(), vo.getGrupo(), vo.getPerfil(), new Boolean(vo.getBloqueado()), vo.getUltimoAcesso());
			if (DaoFactory.getUsuarioDAO(em).inserir(usuario)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO;
	}

	public ServiceDTO alterarUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		UsuarioVO vo = (UsuarioVO) requestDTO.get("usuarioVO");
		if(vo != null) {
			Usuario usuario = new Usuario(vo.getLogin(), vo.getSenha(), vo.getGrupo(), vo.getPerfil(), new Boolean(vo.getBloqueado()), vo.getUltimoAcesso());
			if(DaoFactory.getUsuarioDAO(em).alterar(usuario)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}
		return responseDTO ;
	}

	public ServiceDTO excluirUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		UsuarioVO vo = (UsuarioVO) requestDTO.get("usuarioVO");
		if(vo != null) {
			try{
				Usuario usuario = DaoFactory.getUsuarioDAO(em).localizarPorLogin(vo.getLogin());
				if(DaoFactory.getUsuarioDAO(em).excluir(usuario)) {
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
		return responseDTO ;
	}
	
	public ServiceDTO excluirUsuarioPorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO();
		String login = (String) requestDTO.get("loginUsuario");
		if(login != null){
			if(DaoFactory.getUsuarioDAO(em).excluir(login)) {
				responseDTO.set("resposta", new Boolean(true));
			}else{
				responseDTO.set("resposta", new Boolean(false));
			}
		}else{
			responseDTO.set("resposta", new Boolean(false));
		}
		return responseDTO;
	}

	public ServiceDTO selecionarTodosUsuarios(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		Usuario usuario = null;
		List<Usuario> lista = DaoFactory.getUsuarioDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			UsuarioVO[] usuarios = new UsuarioVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				usuario = (Usuario) lista.get(i);
				UsuarioVO usuarioVO = UsuarioVO.create(usuario);
				usuarios[i] = usuarioVO;
			}
			responseDTO.set("listaUsuario", usuarios);
		}
		return responseDTO ;
	}

	public ServiceDTO getUsuario(ServiceDTO requestDTO, String login) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try{
			Usuario usuario = DaoFactory.getUsuarioDAO(em).localizarPorLogin(login);
			if(usuario != null){
				UsuarioVO usuarioVO = UsuarioVO.create(usuario);
				responseDTO.set("getUsuario", usuarioVO);
			}else{
				responseDTO.set("getUsuario", null);
			}
		}catch(Exception e){
			responseDTO.set("getUsuario", null);
		}
		return responseDTO;
	}
}