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

import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;

@Entity
@Table(name="TBL_RESERVA")
public class Reserva implements Serializable {

	private static final long serialVersionUID = -6722022398585105298L;

	@Id
	@Column(name="codigo", nullable=false)
	private Long codigo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data", nullable=false)
	private Date data;

	@Column(name="atendente", nullable=false)
	private String atendente;
	
	@Column(name="situacao", nullable=false)
	private String situacao;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="login_cliente")
	@Fetch(FetchMode.JOIN)
	private Cliente cliente;

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
		Reserva other = (Reserva) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	public static Reserva create(ReservaVO vo){
		Reserva reserva = new Reserva();
		reserva.setCodigo(vo.getCodigo());
		reserva.setData(vo.getData());
		reserva.setAtendente(vo.getAtendente());
		reserva.setSituacao(vo.getSituacao());
		reserva.setValor(vo.getValor());
		return reserva;
	}
}