package br.com.exemplo.vendas.negocio.model.vo;

import java.math.*;
import java.util.Date;

public class ReservaVO {
	
	private Integer codigo;
	private Date data;
	private String atendente;
	private String situacao;
	private BigDecimal valor;
	private ClienteVO cliente;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
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
	public ClienteVO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
}