package br.com.exemplo.vendas.negocio.model.vo ;

import java.io.Serializable ;
import java.util.Date ;

public class UsuarioVO implements Serializable
{
	private String login ;
	private String senha ;
	private String grupo ;
	private String perfil ;
	private String bloqueado ;
	private Date ultimoAcesso ;
	private String nome ;

	public String toString( )
	{
		return this.login + ":" + this.senha + ":" + this.grupo + ":" + this.perfil + ":"
				+ this.bloqueado + ":" + this.ultimoAcesso + ":" + this.nome ;
	}

	public UsuarioVO( )
	{
	}

	public UsuarioVO( String login, String senha, String grupo, String perfil, String bloqueado,
			Date ultimoAcesso )
	{
		this.login = login ;
		this.senha = senha ;
		this.grupo = grupo ;
		this.perfil = perfil ;
		this.bloqueado = bloqueado ;
		this.ultimoAcesso = ultimoAcesso ;
	}

	public UsuarioVO( String login, String senha, String grupo, String perfil, String bloqueado,
			Date ultimoAcesso, String nome )
	{
		this.login = login ;
		this.senha = senha ;
		this.grupo = grupo ;
		this.perfil = perfil ;
		this.bloqueado = bloqueado ;
		this.ultimoAcesso = ultimoAcesso ;
		this.nome = nome ;
	}

	public String getNome( )
	{
		return nome ;
	}

	public void setNome( String nome )
	{
		this.nome = nome ;
	}

	public String getBloqueado( )
	{
		return bloqueado ;
	}

	public void setBloqueado( String bloqueado )
	{
		this.bloqueado = bloqueado ;
	}

	public String getGrupo( )
	{
		return grupo ;
	}

	public void setGrupo( String grupo )
	{
		this.grupo = grupo ;
	}

	public String getLogin( )
	{
		return login ;
	}

	public void setLogin( String login )
	{
		this.login = login ;
	}

	public String getPerfil( )
	{
		return perfil ;
	}

	public void setPerfil( String perfil )
	{
		this.perfil = perfil ;
	}

	public String getSenha( )
	{
		return senha ;
	}

	public void setSenha( String senha )
	{
		this.senha = senha ;
	}

	public Date getUltimoAcesso( )
	{
		return ultimoAcesso ;
	}

	public void setUltimoAcesso( Date ultimoAcesso )
	{
		this.ultimoAcesso = ultimoAcesso ;
	}
}
