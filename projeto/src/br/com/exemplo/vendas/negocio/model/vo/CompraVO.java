package br.com.exemplo.vendas.negocio.model.vo;

import java.math.BigDecimal;
import java.util.Date;

public class CompraVO {
	
	private Integer numero;
	private Date data;
	private String situacao;
	private BigDecimal valor;
	private ReservaVO reserva;
	private ClienteVO cliente;
	
	public CompraVO(){}
	public CompraVO(Integer numero, BigDecimal valor){
		this.numero = numero;
		this.valor = valor;
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
	public ReservaVO getReserva() {
		return reserva;
	}
	public void setReserva(ReservaVO reserva) {
		this.reserva = reserva;
	}
	public ClienteVO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
}