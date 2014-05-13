package br.com.exemplo.vendas.apresentacao.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.apresentacao.service.Service;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.Redirecionador;
import br.com.exemplo.vendas.util.exception.LayerException;

@SuppressWarnings("serial")
@ManagedBean(name = "produto_controller")
@SessionScoped
public class ProdutoController extends AbstractController
								implements Serializable {

	private ProdutoVO vo;
	private List<ProdutoVO> lista;
	

	public ProdutoController() throws LayerException {
		vo = new ProdutoVO();
		System.out.println("Controller do Produto");
	}

	public void insertView() throws Exception {
		irPara("produtos-inserir.xhtml");
	}

	public String save() throws LayerException {
		System.out.println("Salvando");
		try{
			vo.setEstoque("1");
			if(getServico().inserirProduto(vo)){
				vo = new ProdutoVO();
				System.out.println("Inseriu? Sim");
				closeDialog();
				displayInfoMessageToUser("Inseriu com Sucesso!");
			} else if (getServico().alterarProduto(vo)){
				vo = new ProdutoVO();
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

	public String listAll() throws LayerException {
		vo = new ProdutoVO();
		lista = getLista();
		irPara("produtos-lista.xhtml");
		return "";
	}

	public String excluir() throws LayerException {
		// dao.delete( entity.getId() );
		getServico().excluirProduto(vo);
		System.out.println("removendo");
		return listAll();
	}

	// GETTERS AND SETTERS

	public List<ProdutoVO> getLista() throws LayerException {
		lista = new ArrayList<ProdutoVO>();
		lista = (List<ProdutoVO>) getServico().listarProdutos();
		return lista;
	}

	public ProdutoVO getVo() {
		return vo;
	}

	public void setVo(ProdutoVO vo) {
		this.vo = vo;
	}

	public void setLista(List<ProdutoVO> lista) {
		this.lista = lista;
	}
	
	public void reset(){
		this.vo = new ProdutoVO();
	}
}
