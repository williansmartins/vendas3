package br.com.exemplo.vendas.negocio.entity;

public class ClienteFisico extends Cliente {

	private static final long serialVersionUID = -3193185576479085940L;
	private String rg;
	private String cpf;
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}