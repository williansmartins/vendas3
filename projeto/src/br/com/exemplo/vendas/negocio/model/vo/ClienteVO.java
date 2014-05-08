package br.com.exemplo.vendas.negocio.model.vo;

import java.util.Date;

public class ClienteVO extends UsuarioVO {
	
	private static final long serialVersionUID = 9062887494914643927L;
	private Long codigo;
	private String nome;
	private String endereco;
	private Integer telefone;
	private String situacao;
	
	@Override
	public String toString() {
		return this.getLogin();
	}
	
//	public String toString() {
//		return this.getLogin() + ":" + this.getSenha() + ":" + this.getGrupo() + ":" + this.getPerfil() + ":" + this.getBloqueado() + ":" + this.getUltimoAcesso() + ":" + this.codigo + ":" + this.nome + ":" + this.endereco + ":" + this.telefone + ":" + this.situacao;
//	}
	
	public ClienteVO(){}
	public ClienteVO(String login, String senha, String perfil, Boolean bloqueado, Date ultimoAcesso, Long codigo, String nome, String endereco, Integer telefone, String situacao){
		this.setLogin(login);
		this.setSenha(senha);
		this.setPerfil(perfil);
		this.setBloqueado(bloqueado);
		this.setUltimoAcesso(ultimoAcesso);
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.situacao = situacao;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}