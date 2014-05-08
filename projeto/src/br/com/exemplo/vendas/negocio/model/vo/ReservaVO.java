package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.exemplo.vendas.negocio.entity.Reserva;

public class ReservaVO implements Serializable{

	private Long codigo;
	private Date data;
	private String atendente;
	private String situacao;
	private BigDecimal valor;
	private ClienteVO cliente;

	public ReservaVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ReservaVO(Long codigo, Date data, String atendente, String situacao, BigDecimal valor, String cliente) {
		this.codigo = codigo;
		this.data = data;
		this.atendente = atendente;
		this.situacao = situacao;
		this.valor = valor;
		this.cliente = new ClienteVO();
		this.cliente.setNome(cliente);
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
	public ClienteVO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public static ReservaVO create(Reserva reserva){
		ReservaVO reservaVO = new ReservaVO();
		reservaVO.setCodigo(reserva.getCodigo());
		reservaVO.setData(reserva.getData());
		reservaVO.setAtendente(reserva.getAtendente());
		reservaVO.setSituacao(reserva.getSituacao());
		reservaVO.setValor(reserva.getValor());
		return reservaVO;
	}
}