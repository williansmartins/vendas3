package br.com.exemplo.vendas.negocio.model.vo;

import java.util.Date;

import br.com.exemplo.vendas.negocio.entity.ClienteFisico;
import br.com.exemplo.vendas.negocio.entity.ClienteJuridico;

public class ClienteJuridicoVO extends ClienteVO{

	private static final long serialVersionUID = -1717648428545136378L;
	private String cnpj;
	private String enderecoEmpresa;
	private String ie;
	
	@Override
	public String toString() {
		return this.getLogin() + ":" + this.getSenha() + ":" + this.getGrupo() + ":" + this.getPerfil() + ":" + this.getBloqueado() + ":" + this.getUltimoAcesso() + ":" + this.getCodigo() + ":" + this.getNome() + ":" + this.getEndereco() + ":" + this.getTelefone() + ":" + this.getSituacao() + ":" + ":" + this.getCnpj() + ":" + this.getEnderecoEmpresa() + ":" + this.getIe();
	}
	
	public ClienteJuridicoVO(){}
	public ClienteJuridicoVO(String login, String senha, String grupo, String perfil, Boolean bloqueado, Date ultimoAcesso, Long codigo, String nome, String endereco, Integer telefone, String situacao, String cnpj, String enderecoEmpresa, String ie){
		this.setLogin(login);
		this.setSenha(senha);
		this.setGrupo(grupo);
		this.setPerfil(perfil);
		this.setBloqueado(bloqueado);
		this.setUltimoAcesso(ultimoAcesso);
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setSituacao(situacao);
		this.cnpj = cnpj;
		this.enderecoEmpresa = enderecoEmpresa;
		this.ie = ie;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}
	public void setEnderecoEmpresa(String enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}
	public String getIe() {
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	public static ClienteJuridicoVO create(ClienteJuridico clienteJuridico) {
		ClienteJuridicoVO clienteJuridicoVO = new ClienteJuridicoVO();
		clienteJuridicoVO.setLogin(clienteJuridico.getLogin());
		clienteJuridicoVO.setSenha(clienteJuridico.getSenha());
		clienteJuridicoVO.setGrupo(clienteJuridico.getGrupo());
		clienteJuridicoVO.setPerfil(clienteJuridico.getPerfil());
		clienteJuridicoVO.setBloqueado(clienteJuridico.getBloqueado());
		clienteJuridicoVO.setUltimoAcesso(clienteJuridico.getUltimoAcesso());
		clienteJuridicoVO.setCodigo(clienteJuridico.getCodigo());
		clienteJuridicoVO.setNome(clienteJuridico.getNome());
		clienteJuridicoVO.setEndereco(clienteJuridico.getEndereco());
		clienteJuridicoVO.setTelefone(clienteJuridico.getTelefone());
		clienteJuridicoVO.setSituacao(clienteJuridico.getSituacao());
		clienteJuridicoVO.setCnpj(clienteJuridico.getCnpj());
		clienteJuridicoVO.setEnderecoEmpresa(clienteJuridico.getEnderecoEmpresa());
		clienteJuridicoVO.setIe(clienteJuridico.getIe());
		return clienteJuridicoVO;
	}
}