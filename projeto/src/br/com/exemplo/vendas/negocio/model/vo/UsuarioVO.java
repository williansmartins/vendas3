package br.com.exemplo.vendas.negocio.model.vo ;

import java.io.Serializable;
import java.util.Date;

import br.com.exemplo.vendas.negocio.entity.Usuario;

public class UsuarioVO implements Serializable {
	
	private static final long serialVersionUID = -8151335332868453514L;
	private String login;
	private String senha;
	private String grupo;
	private String perfil;
	private Boolean bloqueado;
	private Date ultimoAcesso;

	@Override
	public String toString() {
		return this.login + ":" + this.senha + ":" + this.grupo + ":" + this.perfil + ":" + this.bloqueado + ":" + this.ultimoAcesso;
	}

	public UsuarioVO() {}
	public UsuarioVO(String login, String senha, String grupo, String perfil, Boolean bloqueado, Date ultimoAcesso) {
		this.login = login;
		this.senha = senha;
		this.grupo = grupo;
		this.perfil = perfil;
		this.bloqueado = bloqueado;
		this.ultimoAcesso = ultimoAcesso;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	
	public static UsuarioVO create(Usuario usuario) {
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setLogin(usuario.getLogin());
		usuarioVO.setSenha(usuario.getSenha());
		usuarioVO.setGrupo(usuario.getGrupo());
		usuarioVO.setPerfil(usuario.getPerfil());
		usuarioVO.setBloqueado(usuario.getBloqueado());
		usuarioVO.setUltimoAcesso(usuario.getUltimoAcesso());
		return usuarioVO;
	}
}