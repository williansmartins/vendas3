package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.*;
import java.util.Date;

public class ReservaVO implements Serializable {
	
	private static final long serialVersionUID = -7140384086126242064L;
	private Long codigo;
	private Date data;
	private String atendente;
	private String situacao;
	private BigDecimal valor;
	private String loginCliente;
	private ClienteVO clienteVO;
	
	@Override
	public String toString() {
		return this.codigo + ":" + this.data + ":" + this.atendente + ":" + this.situacao + ":" + this.valor + ":" + (loginCliente==null?this.clienteVO.getLogin():loginCliente);// + ":" + this.clienteVO.getSenha() + ":" + this.clienteVO.getGrupo() + ":" + this.clienteVO.getPerfil() + ":" + this.clienteVO.getBloqueado() + ":" + this.clienteVO.getUltimoAcesso() + ":" + this.clienteVO.getCodigo() + ":" + this.clienteVO.getNome() + ":" + this.clienteVO.getEndereco() + ":" + this.clienteVO.getTelefone() + ":" + this.clienteVO.getSituacao();
	}

	public ReservaVO() {}
	public ReservaVO(Long codigo, Date data, String atendente, String situacao, BigDecimal valor, String loginCliente) {
		this.codigo = codigo;
		this.data = data;
		this.atendente = atendente;
		this.situacao = situacao;
		this.valor = valor;
		this.loginCliente = loginCliente;
	}
	public ReservaVO(Long codigo, Date data, String atendente, String situacao, BigDecimal valor, ClienteVO clienteVO) {
		this.codigo = codigo;
		this.data = data;
		this.atendente = atendente;
		this.situacao = situacao;
		this.valor = valor;
		this.clienteVO = clienteVO;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public String getLoginCliente() {
		return loginCliente;
	}

	public void setLoginCliente(String loginCliente) {
		this.loginCliente = loginCliente;
	}
}