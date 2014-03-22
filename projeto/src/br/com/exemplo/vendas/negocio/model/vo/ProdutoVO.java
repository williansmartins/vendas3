package br.com.exemplo.vendas.negocio.model.vo;

import java.math.BigDecimal;

public class ProdutoVO {
	
	private Long codigo;
	private String descricao;
	private BigDecimal preco;
	private String estoque;
	
	public ProdutoVO(){}
	public ProdutoVO(Long codigo, String descricao, BigDecimal preco, String estoque) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getEstoque() {
		return estoque;
	}
	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}
}