package br.com.exemplo.vendas.negocio.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.exemplo.vendas.negocio.model.vo.ItemVO;

@Entity
@Table(name="TBL_ITEM")
public class Item implements Serializable {
	
	private static final long serialVersionUID = -2370404449248272788L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="quantidade", nullable=false)
	private Integer quantidade;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@Column(name="situacao", nullable=false)
	private String situacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_reserva")
	@Fetch(FetchMode.JOIN)
	private Reserva reserva;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="numero_compra")
	@Fetch(FetchMode.JOIN)
	private Compra compra;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_produto")
	@Fetch(FetchMode.JOIN)
	private Produto produto;

	public Item(){}
	public Item(Integer quantidade, BigDecimal valor, String situacao, Reserva reserva, Compra compra, Produto produto){
		this.quantidade = quantidade;
		this.valor = valor;
		this.situacao = situacao;
		this.reserva = reserva;
		this.compra = compra;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public static Item create(ItemVO itemVO){
		Item item = new Item();
		item.setId(item.getId());
		item.setQuantidade(itemVO.getQuantidade());
		item.setValor(itemVO.getValor());
		item.setSituacao(itemVO.getSituacao());
		return item;
	}
}