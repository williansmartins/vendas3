package br.com.exemplo.vendas.apresentacao.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.negocio.model.vo.Situacao;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.exception.LayerException;

@SuppressWarnings("serial")
@ManagedBean(name = "compra_controller")
@SessionScoped
public class CompraController extends AbstractController
									implements Serializable {

	private CompraVO vo;
	private List<CompraVO> lista;
	private List<UsuarioVO> usuarios;
	private List<ProdutoVO> produtos;
	private List<Situacao> situacoes;
	
	public CompraController() {
		vo = new CompraVO();
		System.out.println("Controller do Compra");
	}

	public void insertView() {
		irPara("compra-inserir.xhtml");
	}

	public void save() {
		System.out.println("Salvando");
		try{
			Boolean sucesso = getServico().inserirCompra(vo); 
			if(sucesso){
				vo = new CompraVO();
				System.out.println("Inseriu? " + sucesso);
				closeDialog();
				displayInfoMessageToUser("Sucesso!");
				listAll();
			} else {
				keepDialogOpen();
				displayErrorMessageToUser("Ops, we could not complete the current operation. Try again later");
			}
			
		}catch (Exception e){
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not complete the current operation. Try again later.\n Detalhes do Erro: "+e.getMessage());
			e.printStackTrace();
		}
	}

	public void listAll() {
		vo = new CompraVO();
		lista = getLista();
		irPara("produtos-lista.xhtml");
	}

	public void remove(){
		try {
			System.out.println("removendo");
			getServico().excluirCompra(vo);
			listAll();
		} catch (LayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// GETTERS AND SETTERS

	public List<CompraVO> getLista(){
		try {
			lista = new ArrayList<CompraVO>();
			lista = (List<CompraVO>) getServico().listarCompras();
		} catch (LayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public CompraVO getVo() {
		return vo;
	}

	public void setVo(CompraVO vo) {
		this.vo = vo;
	}

	public void setLista(List<CompraVO> lista) {
		this.lista = lista;
	}
	
	/**
	 * @return the clientes
	 */
	public final List<UsuarioVO> getUsuarios() {
		try {
			this.usuarios = getServico().listarUsuarios();
		} catch (LayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public final void setUsuarios(List<UsuarioVO> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return the produtos
	 */
	public final List<ProdutoVO> getProdutos() {
		try {
			this.produtos = getServico().listarProdutos();
		} catch (LayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtos;
	}

	/**
	 * @param produtos the produtos to set
	 */
	public final void setProdutos(List<ProdutoVO> produtos) {
		this.produtos = produtos;
	}

	/**
	 * @return the situacoes
	 */
	public List<Situacao> getSituacoes() {
		if(this.situacoes == null){
			this.situacoes = Arrays.asList(Situacao.values());
		}
		return situacoes;
	}

	/**
	 * @param situacoes the situacoes to set
	 */
	public final void setSituacoes(List<Situacao> situacoes) {
		this.situacoes = situacoes;
	}

	public void reset(){
		this.vo = new CompraVO();
	}
}
