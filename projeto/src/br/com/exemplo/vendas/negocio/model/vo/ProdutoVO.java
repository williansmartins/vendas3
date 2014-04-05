package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.exemplo.vendas.negocio.entity.Produto;

public class ProdutoVO implements Serializable {
	
	private static final long serialVersionUID = 3717585905983816406L;
	private Long codigo;
	private String descricao;
	private BigDecimal preco;
	private String estoque;
	
	@Override
	public String toString() {
		return this.codigo + ":" + this.descricao + ":" + this.preco + ":" + this.estoque;
	}
	
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
	public static ProdutoVO create(Produto produto) {
		ProdutoVO produtoVO = new ProdutoVO();
		produtoVO.setCodigo(produto.getCodigo());
		produtoVO.setDescricao(produto.getDescricao());
		produtoVO.setPreco(produto.getPreco());
		produtoVO.setEstoque(produto.getEstoque());
		return produtoVO;
	}
}