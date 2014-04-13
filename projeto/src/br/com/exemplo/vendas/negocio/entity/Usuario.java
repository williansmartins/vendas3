package br.com.exemplo.vendas.negocio.entity ;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;

import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;

@Entity
@Table(name="TBL_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@BatchSize(size=50)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1624685417298531778L ;

	@Id
	private String login;
	
	@Column(name="bloqueado", nullable=false)
	private Boolean bloqueado;
	
	@Column(name="grupo", nullable=false)
	private String grupo;
	
	@Column(name="perfil", nullable=false)
	private String perfil;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ultimo_acesso", nullable=false)
	private Date ultimoAcesso;

	public Usuario(){}
	public Usuario(String login, String senha, String grupo, String perfil, Boolean bloqueado, Date ultimoAcesso) {
		this.login = login;
		this.senha = senha;
		this.grupo = grupo;
		this.perfil = perfil;
		this.bloqueado = bloqueado;
		this.ultimoAcesso = ultimoAcesso;
	}
	public String getLogin() {
		return this.login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Boolean getBloqueado() {
		return this.bloqueado;
	}
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public String getGrupo() {
		return this.grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getPerfil() {
		return this.perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getSenha() {
		return this.senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getUltimoAcesso() {
		return this.ultimoAcesso;
	}
	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	public static Usuario create(UsuarioVO usuarioVO) {
		Usuario usuario = new Usuario();
		usuario.setLogin(usuarioVO.getLogin());
		usuario.setSenha(usuarioVO.getSenha());
		usuario.setGrupo(usuarioVO.getGrupo());
		usuario.setPerfil(usuarioVO.getPerfil());
		usuario.setBloqueado(usuarioVO.getBloqueado());
		usuario.setUltimoAcesso(usuarioVO.getUltimoAcesso());
		return usuario;
	}
}