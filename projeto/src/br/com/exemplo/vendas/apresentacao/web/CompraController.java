package br.com.exemplo.vendas.apresentacao.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.apresentacao.delegate.BusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.Redirecionador;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@SuppressWarnings("serial")
@ManagedBean(name = "compra_controller")
@SessionScoped
public class CompraController implements Serializable {

	private CompraVO vo;
	private List<CompraVO> lista;

	public CompraController() throws LayerException {
		vo = new CompraVO();
		System.out.println("Controller do Compra");
	}

	public void insertView() throws Exception {
		new Redirecionador().redirecionar("compra-inserir.xhtml");
	}

	public String save() throws Exception {
		System.out.println("Salvando");
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

		requestDTO.set("compraVO", vo);
		
		BusinessDelegate bd = BusinessDelegate.getInstance();
		responseDTO = bd.inserirCompra(requestDTO);
		//responseDTO = remoteUsuario.inserirUsuario(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		vo = new CompraVO();
		System.out.println("Inseriu? " + sucesso);
		return listAll();
	}

	public String listAll() throws LayerException {
		vo = new CompraVO();
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

	public List<CompraVO> getLista() throws LayerException {
		lista = new ArrayList<CompraVO>();
		BusinessDelegate bd = BusinessDelegate.getInstance();
		lista = (List<CompraVO>) bd.selectionarTodasCompras(null).get("listaCompra");
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
	
	public void reset(){
		this.vo = new CompraVO();
	}
}
