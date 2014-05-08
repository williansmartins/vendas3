package br.com.exemplo.vendas.negocio.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;

@Entity
@Table(name="TBL_PRODUTO")
public class Produto implements Serializable {

	private static final long serialVersionUID = 8991974131620584188L;

	@Id
	private Long codigo;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="preco", nullable=false)
	private BigDecimal preco;
	
	@Column(name="estoque", nullable=false)
	private String estoque;
	
	@Column(name="quantidade_estoque", nullable=false)
	private Integer quantidadeEstoque;
	
	public Produto(){}
	public Produto(Long codigo, String descricao, BigDecimal preco, String estoque, Integer quantidadeEstoque) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	public static Produto create(ProdutoVO produtoVO) {
		Produto produto = new Produto();
		produto.setCodigo(produtoVO.getCodigo());
		produto.setDescricao(produtoVO.getDescricao());
		produto.setPreco(produtoVO.getPreco());
		produto.setEstoque(produtoVO.getEstoque());
		produto.setQuantidadeEstoque(produtoVO.getQuantidadeEstoque());
		return produto;
	}
	
	public static ProdutoVO create(Produto produto) {
		ProdutoVO produtoVO = new ProdutoVO();
		produtoVO.setCodigo(produto.getCodigo());
		produtoVO.setDescricao(produto.getDescricao());
		produtoVO.setPreco(produto.getPreco());
		produtoVO.setEstoque(produto.getEstoque());
		produtoVO.setQuantidadeEstoque(produto.getQuantidadeEstoque());
		return produtoVO;
	}	
}