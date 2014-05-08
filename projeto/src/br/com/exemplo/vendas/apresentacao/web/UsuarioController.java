package br.com.exemplo.vendas.apresentacao.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;

@SuppressWarnings("serial")
@ManagedBean(name = "usuario_controller")
@SessionScoped
public class UsuarioController extends AbstractController
								implements Serializable {

	private UsuarioVO vo;
	private List<UsuarioVO> lista;

	public UsuarioController() {
		vo = new UsuarioVO();
		System.out.println("Controller de Usuário");
		lista = getLista();
	}

	public String save() throws Exception {
		System.out.println("Salvando");
		try{
			if(getServico().inserirUsuario(vo)){
				reset();
				System.out.println("Inseriu? Sim");
				closeDialog();
				displayInfoMessageToUser("Inseriu com Sucesso!");
			} else if (getServico().alterarUsuario(vo)){
				reset();
				System.out.println("Alterou? Sim");
				closeDialog();
				displayInfoMessageToUser("Alterou com Sucesso!");
			} else {
				keepDialogOpen();
				displayErrorMessageToUser("Ops, we could not complete the current operation. Try again later");
			}
			
		}catch (Exception e){
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not complete the current operation. Try again later.\n Detalhes do Erro: "+e.getMessage());
			e.printStackTrace();
		}
		return listAll();
	}

	public String listAll() {
		vo = new UsuarioVO();
		lista = getLista();
		irPara("lista-usuarios.xhtml");
		return "";
	}

	public String remove() {
		// dao.delete( entity.getId() );
		System.out.println("removendo");
		return listAll();
	}

	// GETTERS AND SETTERS

	public List<UsuarioVO> getLista() {
		
		return lista;
	}

	public UsuarioVO getVo() {
		return vo;
	}

	public void setVo(UsuarioVO vo) {
		this.vo = vo;
	}

	public void setLista(List<UsuarioVO> lista) {
		this.lista = lista;
	}
	
	public void reset(){
		this.vo = new UsuarioVO();
	}
}
