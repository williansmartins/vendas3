package br.com.exemplo.vendas.apresentacao.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.Redirecionador;

@SuppressWarnings("serial")
@ManagedBean(name = "usuario_controller")
@SessionScoped
public class UsuarioController implements Serializable{

	private UsuarioVO vo;
	private List<UsuarioVO> lista;

	public UsuarioController() {
		vo = new UsuarioVO();
		System.out.println("construindo");
		lista = getLista();
	}
	
    public void insertView( )
    {
    	vo = new UsuarioVO();
    	new Redirecionador().redirecionar( "inserir-usuario.xhtml" );
    }
 
    public String save( )
    {
    	System.out.println( "Salvando" );
		return listAll();
    }
 
    public String listAll( )
    {
    	vo = new UsuarioVO();
		lista = getLista();
		new Redirecionador().redirecionar( "lista-usuarios.xhtml" );
		return "";
    }
    public String remove( )
    {
//		dao.delete( entity.getId() ); 
    	System.out.println( "removendo" );
		return listAll();
    }   
	// GETTERS AND SETTERS

	public UsuarioVO getVO() {
		return vo;
	}

	public void setVO(UsuarioVO entity) {
		this.vo = entity;
	}

	public List<UsuarioVO> getLista() {
		List<UsuarioVO> lista2 = new ArrayList<UsuarioVO>();
		lista2.add( new UsuarioVO("login1", "senha1", "grupo1", "perfil1", "sim", new Date()) );
		lista2.add( new UsuarioVO("login1", "senha1", "grupo1", "perfil1", "sim", new Date()) );
		lista2.add( new UsuarioVO("login1", "senha1", "grupo1", "perfil1", "sim", new Date()) );
		
		return lista2;
	}

	public void setLista(List<UsuarioVO> lista) {
		this.lista = lista;
	}
}
