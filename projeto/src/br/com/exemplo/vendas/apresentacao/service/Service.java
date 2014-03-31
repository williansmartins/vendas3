package br.com.exemplo.vendas.apresentacao.service ;

import java.util.Arrays ;
import java.util.List ;

import br.com.exemplo.vendas.apresentacao.delegate.BusinessDelegate ;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO ;
import br.com.exemplo.vendas.util.dto.ServiceDTO ;
import br.com.exemplo.vendas.util.exception.LayerException ;

public class Service {
	
	public String enviaMensagem(String mensagem) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("teste", mensagem);
		responseDTO = BusinessDelegate.getInstance().inserirQueue(requestDTO);
		return (String) responseDTO.get("ticket");
	}
	
	public Boolean inserirUsuario(UsuarioVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("usuarioVO", vo);
		responseDTO = BusinessDelegate.getInstance().inserirUsuario(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		return sucesso;
	}

	public List<UsuarioVO> listarUsuarios() throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		responseDTO = BusinessDelegate.getInstance().selectionarTodosUsuarios(requestDTO);
		UsuarioVO[] usuarios = (UsuarioVO[])responseDTO.get("listaUsuario");
		List<UsuarioVO> lista = Arrays.asList(usuarios);
		return lista;
	}

	public Boolean alterarUsuario( UsuarioVO vo ) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set( "usuarioVO", vo ) ;
		responseDTO = BusinessDelegate.getInstance().alterarUsuario(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}

	public Boolean excluirUsuario(UsuarioVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();
		requestDTO.set("usuarioVO", vo);
		responseDTO = BusinessDelegate.getInstance().excluirUsuario(requestDTO);
		Boolean sucesso = (Boolean)responseDTO.get("resposta");
		return sucesso;
	}
}