package br.com.exemplo.vendas.negocio.model.vo;

import java.math.BigDecimal;

public class ItemVO {
	
	private Integer quantidade;
	private BigDecimal valor;
	private String situacao;
	private ReservaVO reserva;
	private CompraVO compra;
	private ProdutoVO produto;
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public ReservaVO getReserva() {
		return reserva;
	}
	public void setReserva(ReservaVO reserva) {
		this.reserva = reserva;
	}
	public CompraVO getCompra() {
		return compra;
	}
	public void setCompra(CompraVO compra) {
		this.compra = compra;
	}
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}
}