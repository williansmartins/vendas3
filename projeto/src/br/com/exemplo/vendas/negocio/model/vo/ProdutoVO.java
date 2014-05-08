package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoVO implements Serializable {
	
	private static final long serialVersionUID = 3717585905983816406L;
	private Long codigo;
	private String descricao;
	private BigDecimal preco;
	private String estoque;
	private Integer quantidadeEstoque;
	
	@Override
	public String toString() {
		return this.codigo + ":" + this.descricao + ":" + this.preco + ":" + this.estoque;
	}
	
	public ProdutoVO(){}
	public ProdutoVO(Long codigo, String descricao, BigDecimal preco, String estoque, Integer quantidadeEstoque) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
		this.quantidadeEstoque = quantidadeEstoque;
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
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

}