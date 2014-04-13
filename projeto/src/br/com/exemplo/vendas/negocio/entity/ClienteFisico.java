package br.com.exemplo.vendas.negocio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.exemplo.vendas.negocio.model.vo.ClienteFisicoVO;

@Entity
@Table(name="TBL_CLIENTE_FISICO")
@PrimaryKeyJoinColumn(name="LOGIN")
public class ClienteFisico extends Cliente {
	
	private static final long serialVersionUID = 9062887494914643927L;
	
	@Column(name="rg", nullable=false)
	private String rg;
	
	@Column(name="cpf", nullable=false)
	private String cpf;
	
	public ClienteFisico(){}
	public ClienteFisico(String login, String senha, String grupo, String perfil, Boolean bloqueado, Date ultimoAcesso, Long codigo, String nome, String endereco, Integer telefone, String situacao, String rg, String cpf){
		this.setLogin(login);
		this.setSenha(senha);
		this.setGrupo(grupo);
		this.setPerfil(perfil);
		this.setBloqueado(bloqueado);
		this.setUltimoAcesso(ultimoAcesso);
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setSituacao(situacao);
		this.rg = rg;
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
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
		ClienteFisico other = (ClienteFisico) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}
	
	public static ClienteFisico create(ClienteFisicoVO clienteFisicoVO) {
		ClienteFisico clienteFisico = new ClienteFisico();
		clienteFisico.setLogin(clienteFisicoVO.getLogin());
		clienteFisico.setSenha(clienteFisicoVO.getSenha());
		clienteFisico.setGrupo(clienteFisicoVO.getGrupo());
		clienteFisico.setPerfil(clienteFisicoVO.getPerfil());
		clienteFisico.setBloqueado(clienteFisicoVO.getBloqueado());
		clienteFisico.setUltimoAcesso(clienteFisicoVO.getUltimoAcesso());
		clienteFisico.setCodigo(clienteFisicoVO.getCodigo());
		clienteFisico.setNome(clienteFisicoVO.getNome());
		clienteFisico.setEndereco(clienteFisicoVO.getEndereco());
		clienteFisico.setTelefone(clienteFisicoVO.getTelefone());
		clienteFisico.setSituacao(clienteFisicoVO.getSituacao());
		clienteFisico.setRg(clienteFisicoVO.getRg());
		clienteFisico.setCpf(clienteFisicoVO.getCpf());
		return clienteFisico;
	}
}