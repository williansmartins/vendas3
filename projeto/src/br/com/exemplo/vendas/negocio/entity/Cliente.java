package br.com.exemplo.vendas.negocio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;

@Entity
@Table(name="TBL_CLIENTE")
@PrimaryKeyJoinColumn(name="LOGIN")
public class Cliente extends Usuario {
	
	private static final long serialVersionUID = 9062887494914643927L;
	
	@Column(name="codigo", nullable=false)
	private Long codigo;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="endereco", nullable=false)
	private String endereco;
	
	@Column(name="telefone", nullable=false)
	private Integer telefone;
	
	@Column(name="situacao", nullable=false)
	private String situacao;
	
	public Cliente(){}
	public Cliente(String login, String senha, String grupo, String perfil, Boolean bloqueado, Date ultimoAcesso, Long codigo, String nome, String endereco, Integer telefone, String situacao){
		this.setLogin(login);
		this.setSenha(senha);
		this.setGrupo(grupo);
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	public static Cliente create(ClienteVO clienteVO) {
		Cliente cliente = new Cliente();
		cliente.setLogin(clienteVO.getLogin());
		cliente.setSenha(clienteVO.getSenha());
		cliente.setPerfil(clienteVO.getPerfil());
		cliente.setBloqueado(clienteVO.getBloqueado());
		cliente.setUltimoAcesso(clienteVO.getUltimoAcesso());
		cliente.setCodigo(clienteVO.getCodigo());
		cliente.setNome(clienteVO.getNome());
		cliente.setEndereco(clienteVO.getEndereco());
		cliente.setTelefone(clienteVO.getTelefone());
		cliente.setSituacao(clienteVO.getSituacao());
		return cliente;
	}
	public static ClienteVO create(Cliente cliente) {
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setLogin(cliente.getLogin());
		clienteVO.setSenha(cliente.getSenha());
		clienteVO.setPerfil(cliente.getPerfil());
		clienteVO.setBloqueado(cliente.getBloqueado());
		clienteVO.setUltimoAcesso(cliente.getUltimoAcesso());
		clienteVO.setCodigo(cliente.getCodigo());
		clienteVO.setNome(cliente.getNome());
		clienteVO.setEndereco(cliente.getEndereco());
		clienteVO.setTelefone(cliente.getTelefone());
		clienteVO.setSituacao(cliente.getSituacao());
		return clienteVO;
	}
}