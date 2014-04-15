package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.exemplo.vendas.negocio.entity.Item;

public class ItemVO implements Serializable {
	
	private static final long serialVersionUID = 6819396935195419556L;
	private Long id;
	private Integer quantidade;
	private BigDecimal valor;
	private String situacao;
	private Long codigoReserva;
	private ReservaVO reservaVO;
	private Long numeroCompra;
	private CompraVO compraVO;
	private Long codigoPrduto;
	private ProdutoVO produtoVO;
	
	@Override
	public String toString() {
		return this.quantidade + ":" + this.valor + ":" + this.situacao + ":" + this.codigoReserva + ":" + (codigoReserva==null?this.reservaVO.getCodigo():codigoReserva) + ":" + (numeroCompra==null?this.compraVO.getNumero():numeroCompra) + ":" + (codigoPrduto==null?this.produtoVO.getCodigo():codigoPrduto);
	}
	
	public ItemVO(){}
	public ItemVO(Integer quantidade, BigDecimal valor, String situacao, Long codigoReserva, Long numeroCompra, Long codigoPrduto){
		this.quantidade = quantidade;
		this.valor = valor;
		this.situacao = situacao;
		this.codigoReserva = codigoReserva;
		this.numeroCompra = numeroCompra;
		this.codigoPrduto = codigoPrduto;
	}
	public ItemVO(Integer quantidade, BigDecimal valor, String situacao, ReservaVO reservaVO, CompraVO compraVO, ProdutoVO produtoVO){
		this.quantidade = quantidade;
		this.valor = valor;
		this.situacao = situacao;
		this.reservaVO = reservaVO;
		this.compraVO = compraVO;
		this.produtoVO = produtoVO;
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
	public Long getCodigoReserva() {
		return codigoReserva;
	}
	public void setCodigoReserva(Long codigoReserva) {
		this.codigoReserva = codigoReserva;
	}
	public ReservaVO getReservaVO() {
		return reservaVO;
	}
	public void setReservaVO(ReservaVO reservaVO) {
		this.reservaVO = reservaVO;
	}
	public Long getNumeroCompra() {
		return numeroCompra;
	}
	public void setNumeroCompra(Long numeroCompra) {
		this.numeroCompra = numeroCompra;
	}
	public CompraVO getCompraVO() {
		return compraVO;
	}
	public void setCompraVO(CompraVO compraVO) {
		this.compraVO = compraVO;
	}
	public Long getCodigoPrduto() {
		return codigoPrduto;
	}
	public void setCodigoPrduto(Long codigoPrduto) {
		this.codigoPrduto = codigoPrduto;
	}
	public ProdutoVO getProdutoVO() {
		return produtoVO;
	}
	public void setProdutoVO(ProdutoVO produtoVO) {
		this.produtoVO = produtoVO;
	}
	public static ItemVO create(Item item){
		ItemVO itemVO = new ItemVO();
		itemVO.setId(item.getId());
		itemVO.setQuantidade(item.getQuantidade());
		itemVO.setValor(item.getValor());
		itemVO.setSituacao(item.getSituacao());
		return itemVO;
	}
}