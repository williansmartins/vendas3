package br.com.exemplo.vendas.negocio.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.exemplo.vendas.negocio.model.vo.CompraVO;

@Entity
@Table(name="TBL_COMPRA")
public class Compra implements Serializable {
	
	private static final long serialVersionUID = -721402309397612891L;

	@Id
	@Column(name="numero", nullable=false)
	private Long numero;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data", nullable=false)
	private Date data;
	
	@Column(name="situacao", nullable=false)
	private String situacao;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_reserva")
	@Fetch(FetchMode.JOIN)
	private Reserva reserva;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="login_cliente")
	@Fetch(FetchMode.JOIN)
	private Cliente cliente;
	
	public Compra(){}
	public Compra(Long numero, Date data, String situacao, BigDecimal valor, Reserva reserva, Cliente cliente){
		this.numero = numero;
		this.data = data;
		this.situacao = situacao;
		this.valor = valor;
		this.reserva = reserva;
		this.cliente = cliente;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Compra other = (Compra) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	public static Compra create(CompraVO compraVO) {
		Compra compra = new Compra();
		compra.setNumero(compraVO.getNumero());
		compra.setData(compraVO.getData());
		compra.setSituacao(compraVO.getSituacao());
		compra.setValor(compraVO.getValor());
		return compra;
	}
	public static CompraVO create(Compra compra) {
		CompraVO compraVO = new CompraVO();
		compraVO.setNumero(compra.getNumero());
		compraVO.setData(compra.getData());
		compraVO.setSituacao(compra.getSituacao());
		compraVO.setValor(compra.getValor());
		return compraVO;
	}
}