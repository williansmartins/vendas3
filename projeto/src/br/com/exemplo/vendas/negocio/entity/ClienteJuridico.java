package br.com.exemplo.vendas.negocio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.exemplo.vendas.negocio.model.vo.ClienteJuridicoVO;

@Entity
@Table(name="TBL_CLIENTE_JURIDICO")
@PrimaryKeyJoinColumn(name="LOGIN")
public class ClienteJuridico extends Cliente {
	
	private static final long serialVersionUID = 9062817494914643927L;
	
	@Column(name="cnpj", nullable=false)
	private String cnpj;
	
	@Column(name="endereco_empresa", nullable=false)
	private String enderecoEmpresa;
	
	@Column(name="ie", nullable=false)
	private String ie;
	
	public ClienteJuridico(){}
	public ClienteJuridico(String login, String senha, String grupo, String perfil, Boolean bloqueado, Date ultimoAcesso, Long codigo, String nome, String endereco, Integer telefone, String situacao, String cnpj, String enderecoEmpresa, String ie){
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
		this.cnpj = cnpj;
		this.enderecoEmpresa = enderecoEmpresa;
		this.ie = ie;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}
	public void setEnderecoEmpresa(String enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}
	public String getIe() {
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	public static ClienteJuridico create(ClienteJuridicoVO clienteJuridicoVO) {
		ClienteJuridico clienteJuridico = new ClienteJuridico();
		clienteJuridico.setLogin(clienteJuridicoVO.getLogin());
		clienteJuridico.setSenha(clienteJuridicoVO.getSenha());
		clienteJuridico.setGrupo(clienteJuridicoVO.getGrupo());
		clienteJuridico.setPerfil(clienteJuridicoVO.getPerfil());
		clienteJuridico.setBloqueado(clienteJuridicoVO.getBloqueado());
		clienteJuridico.setUltimoAcesso(clienteJuridicoVO.getUltimoAcesso());
		clienteJuridico.setCodigo(clienteJuridicoVO.getCodigo());
		clienteJuridico.setNome(clienteJuridicoVO.getNome());
		clienteJuridico.setEndereco(clienteJuridicoVO.getEndereco());
		clienteJuridico.setTelefone(clienteJuridicoVO.getTelefone());
		clienteJuridico.setSituacao(clienteJuridicoVO.getSituacao());
		clienteJuridico.setCnpj(clienteJuridicoVO.getCnpj());
		clienteJuridico.setEnderecoEmpresa(clienteJuridicoVO.getEnderecoEmpresa());
		clienteJuridico.setIe(clienteJuridicoVO.getIe());
		return clienteJuridico;
	}
}