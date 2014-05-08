package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.exemplo.vendas.negocio.entity.Compra;

public class CompraVO implements Serializable {

	private Long numero;
	private Date data;
	private String situacao;
	private BigDecimal valor;
	private ReservaVO reserva;
	private ClienteVO cliente;

	public CompraVO(){}
	public CompraVO(Long numero, BigDecimal valor){
		this.numero = numero;
		this.valor = valor;
	}

	public CompraVO(Long numero, Date data, String situacao, BigDecimal valor, Long reserva, String cliente) {
		this.numero = numero;
		this.data = data;
		this.situacao = situacao;
		this.valor = valor;
		this.reserva = new ReservaVO();
		this.reserva.setCodigo(reserva);
		this.cliente = new ClienteVO();
		this.cliente.setNome(cliente);
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
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