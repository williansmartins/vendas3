/**
 * 
 */
package br.com.exemplo.vendas.apresentacao.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.exemplo.vendas.negocio.entity.Usuario;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;

/**
 * @author Alex
 *
 */
public class GerenciadorUsuario {

	private Map<String, Usuario> usuarios;
	
	public void incluir(Usuario vo) throws Exception{
		if(getUsuarioPorNome(vo.getLogin()) != null){
			throw new Exception("Usuario já existente!");
		}
		getUsuarios().put(vo.getLogin(), vo);
	}
	
	public Usuario getUsuarioPorNome(String nome){
		for(Usuario p: getUsuarios().values()){
			if(p.getLogin().equals(nome)){
				return p;
			}
		}
		return null;
	}
	
	/*public Produto getProdutoPorID(Long id){
		if(getProdutos().containsKey(id)){
			return getProdutos().get(id);
		}
		return null;
	}*/
	
	public void alterar(Usuario vo){
		getUsuarios().put(vo.getLogin(), vo);
	}
	
	public void excluir(Usuario vo){
		getUsuarios().remove(vo.getLogin());
	}

	/**
	 * @return the produtos
	 */
	private final Map<String, Usuario> getUsuarios() {
		if(usuarios == null){
			setUsuarios(new HashMap<String, Usuario>());
		}
		return usuarios;
	}

	/**
	 * @param produtos the produtos to set
	 */
	private final void setUsuarios(Map<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<UsuarioVO> selecionarTodosUsuariosVOs() {
		List<UsuarioVO> lista = new ArrayList<UsuarioVO>();
		for(Usuario p: listar()){
			UsuarioVO vo = new UsuarioVO();
			vo.setBloqueado(p.getBloqueado());
			vo.setLogin(p.getLogin());
			vo.setPerfil(p.getPerfil());
			vo.setSenha(p.getSenha());
			vo.setUltimoAcesso(p.getUltimoAcesso());
			lista.add(vo);
		}
		return lista;
	}

	private List<Usuario> listar() {
		return new ArrayList<Usuario>(getUsuarios().values());
	}
	
}
