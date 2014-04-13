package br.com.exemplo.vendas.negocio.model.vo;

import java.util.Date;

import br.com.exemplo.vendas.negocio.entity.ClienteFisico;

public class ClienteFisicoVO extends ClienteVO {

	private static final long serialVersionUID = -3193185576479085940L;
	private String rg;
	private String cpf;
	
	@Override
	public String toString() {
		return this.getLogin() + ":" + this.getSenha() + ":" + this.getGrupo() + ":" + this.getPerfil() + ":" + this.getBloqueado() + ":" + this.getUltimoAcesso() + ":" + this.getCodigo() + ":" + this.getNome() + ":" + this.getEndereco() + ":" + this.getTelefone() + ":" + this.getSituacao() + ":" + this.getRg() + ":" + this.getCpf();
	}
	
	public ClienteFisicoVO(){}
	public ClienteFisicoVO(String login, String senha, String grupo, String perfil, Boolean bloqueado, Date ultimoAcesso, Long codigo, String nome, String endereco, Integer telefone, String situacao, String rg, String cpf){
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
		this.rg = rg;
		this.cpf = cpf;
	}
	
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
	public static ClienteFisicoVO create(ClienteFisico clienteFisico) {
		ClienteFisicoVO clienteFisicoVO = new ClienteFisicoVO();
		clienteFisicoVO.setLogin(clienteFisico.getLogin());
		clienteFisicoVO.setSenha(clienteFisico.getSenha());
		clienteFisicoVO.setGrupo(clienteFisico.getGrupo());
		clienteFisicoVO.setPerfil(clienteFisico.getPerfil());
		clienteFisicoVO.setBloqueado(clienteFisico.getBloqueado());
		clienteFisicoVO.setUltimoAcesso(clienteFisico.getUltimoAcesso());
		clienteFisicoVO.setCodigo(clienteFisico.getCodigo());
		clienteFisicoVO.setNome(clienteFisico.getNome());
		clienteFisicoVO.setEndereco(clienteFisico.getEndereco());
		clienteFisicoVO.setTelefone(clienteFisico.getTelefone());
		clienteFisicoVO.setSituacao(clienteFisico.getSituacao());
		clienteFisicoVO.setRg(clienteFisico.getRg());
		clienteFisicoVO.setCpf(clienteFisico.getCpf());
		return clienteFisicoVO;
	}
}