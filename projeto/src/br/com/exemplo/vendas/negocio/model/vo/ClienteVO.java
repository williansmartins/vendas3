package br.com.exemplo.vendas.negocio.model.vo;

public class ClienteVO extends UsuarioVO{
	
	private static final long serialVersionUID = 9062887494914643927L;
	private Integer codigo;
	private String nome;
	private String endereco;
	private Integer telefone;
	private String situacao;
	
	public ClienteVO(){}
	public ClienteVO(Integer codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}