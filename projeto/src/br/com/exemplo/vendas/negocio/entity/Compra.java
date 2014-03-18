package br.com.exemplo.vendas.negocio.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Compra {
	
	private Integer numero;
	private Date data;
	private String situacao;
	private BigDecimal valor;
	private Reserva reserva;
	private Cliente cliente;
	
	public Compra(){}
	public Compra(Integer numero, BigDecimal valor){
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
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}