package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.exemplo.vendas.negocio.entity.Compra;

public class CompraVO implements Serializable {
	
	private static final long serialVersionUID = -1295892845469290276L;
	private Long numero;
	private Date data;
	private String situacao;
	private BigDecimal valor;
	private Long codigoReserva;
	private ReservaVO reservaVO;
	private String loginCliente;
	private ClienteVO clienteVO;
	
	@Override
	public String toString() {
		return this.numero + ":" + this.data + ":" + this.situacao + ":" + this.valor + ":" + (codigoReserva==null?this.reservaVO.getCodigo():codigoReserva) + ":" + (loginCliente==null?this.clienteVO.getLogin():loginCliente);
	}
	
	public CompraVO(){}
	public CompraVO(Long numero, Date data, String situacao, BigDecimal valor, Long codigoReserva, String loginCliente){
		this.numero = numero;
		this.data = data;
		this.situacao = situacao;
		this.valor = valor;
		this.codigoReserva = codigoReserva;
		this.loginCliente = loginCliente;
	}
	public CompraVO(Long numero, Date data, String situacao, BigDecimal valor, ReservaVO reservaVO, ClienteVO clienteVO){
		this.numero = numero;
		this.data = data;
		this.situacao = situacao;
		this.valor = valor;
		this.reservaVO = reservaVO;
		this.clienteVO = clienteVO;
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
	public String getLoginCliente() {
		return loginCliente;
	}
	public void setLoginCliente(String loginCliente) {
		this.loginCliente = loginCliente;
	}
	public ClienteVO getClienteVO() {
		return clienteVO;
	}
	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
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