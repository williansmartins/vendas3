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
		UsuarioVO vo = (UsuarioVO) requestDTO.get("usuarioVO");
		if(vo != null) {
			Usuario usuario = Usuario.create(vo);
			if(DaoFactory.getUsuarioDAO(em).inserir(usuario)){
				return new ServiceDTO("resposta", new Boolean(true));
			}else{
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}

	public ServiceDTO alterarUsuario(ServiceDTO requestDTO) throws LayerException {
		UsuarioVO vo = (UsuarioVO) requestDTO.get("usuarioVO");
		if(vo != null) {
			Usuario usuario = Usuario.create(vo);
			if(DaoFactory.getUsuarioDAO(em).alterar(usuario)){
				return new ServiceDTO("resposta", new Boolean(true));
			}else{
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}

	public ServiceDTO excluirUsuario(ServiceDTO requestDTO) throws LayerException {
		UsuarioVO vo = (UsuarioVO) requestDTO.get("usuarioVO");
		if(vo != null){
			try{
				Usuario usuario = DaoFactory.getUsuarioDAO(em).localizarPorLogin(vo.getLogin());
				if(DaoFactory.getUsuarioDAO(em).excluir(usuario)){
					return new ServiceDTO("resposta", new Boolean(true));
				}else{
					return new ServiceDTO("resposta", new Boolean(false));
				}
			}catch(Exception e){
				e.printStackTrace();
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}
	
	public ServiceDTO excluirUsuarioPorLogin(ServiceDTO requestDTO) throws LayerException, RemoteException {
		String login = (String) requestDTO.get("loginUsuario");
		if(login != null){
			if(DaoFactory.getUsuarioDAO(em).excluir(login)) {
				return new ServiceDTO("resposta", new Boolean(true));
			}else{
				return new ServiceDTO("resposta", new Boolean(false));
			}
		}else{
			return new ServiceDTO("resposta", new Boolean(false));
		}
	}

	public ServiceDTO selecionarTodosUsuarios(ServiceDTO requestDTO) throws LayerException {
		Usuario usuario = null;
		List<Usuario> lista = DaoFactory.getUsuarioDAO(em).listar();
		if((lista != null) && (!lista.isEmpty())) {
			UsuarioVO[] usuarios = new UsuarioVO[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				usuario = (Usuario) lista.get(i);
				UsuarioVO usuarioVO = UsuarioVO.create(usuario);
				usuarios[i] = usuarioVO;
			}
			return new ServiceDTO("listaUsuario", usuarios);
		}else{
			return new ServiceDTO("listaUsuario", new UsuarioVO[0]);
		}
	}

	public ServiceDTO getUsuario(ServiceDTO requestDTO, String login) throws LayerException {
		try{
			Usuario usuario = DaoFactory.getUsuarioDAO(em).localizarPorLogin(login);
			if(usuario != null){
				UsuarioVO usuarioVO = UsuarioVO.create(usuario);
				return new ServiceDTO("getUsuario", usuarioVO);
			}else{
				return new ServiceDTO("getUsuario", null);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ServiceDTO("getUsuario", null);
		}
	}
}