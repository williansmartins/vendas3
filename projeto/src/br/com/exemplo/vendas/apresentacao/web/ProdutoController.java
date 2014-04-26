package br.com.exemplo.vendas.apresentacao.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.apresentacao.delegate.BusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.Redirecionador;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
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
		new Redirecionador().redirecionar("produtos-inserir.xhtml");
	}

	public String save() throws Exception {
		System.out.println("Salvando");
		try{
			/*Hashtable prop = new Hashtable();
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");

		Context ctx = new InitialContext(prop);

		UsuarioInterface remoteUsuario = (UsuarioInterface) ctx
				.lookup("UsuarioBean/remote");

		ServiceLocator serviceLocator = ServiceLocatorFactory
				.getServiceLocator("serviceLocator");*/
			ServiceDTO requestDTO = new ServiceDTO();
			ServiceDTO responseDTO = new ServiceDTO();

			requestDTO.set("produtoVO", vo);

			BusinessDelegate bd = BusinessDelegate.getInstance();
			responseDTO = bd.inserirProduto(requestDTO);
			//responseDTO = remoteUsuario.inserirUsuario(requestDTO);
			Boolean sucesso = (Boolean) responseDTO.get("resposta");

			vo = new ProdutoVO();
			System.out.println("Inseriu? " + sucesso);
			closeDialog();
			displayInfoMessageToUser("Sucesso!");
		}catch (Exception e){
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not complete the current operation. Try again later");
			e.printStackTrace();
		}
		return listAll();
	}

	public String listAll() throws LayerException {
		vo = new ProdutoVO();
		lista = getLista();
		//new Redirecionador().redirecionar("produtos-lista.xhtml");
		return "";
	}

	public String remove() throws LayerException {
		// dao.delete( entity.getId() );
		System.out.println("removendo");
		return listAll();
	}

	// GETTERS AND SETTERS

	public List<ProdutoVO> getLista() throws LayerException {
		lista = new ArrayList<ProdutoVO>();
		BusinessDelegate bd = BusinessDelegate.getInstance();
		lista = (List<ProdutoVO>) bd.selectionarTodosProdutos(null).get("listaProduto");
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
