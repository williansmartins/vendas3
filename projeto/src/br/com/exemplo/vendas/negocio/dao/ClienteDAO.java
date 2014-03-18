package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {
	
	public ClienteDAO(EntityManager em) {
		super(em);
	}

	public ClienteDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	public boolean inserir(Cliente cliente) {
		boolean result = false ;
		Cliente existenteCliente = null ;

		try {
//			Query q = em.createQuery( "from Usuario where login like :login" ) ;
//			q.setParameter( "login", usuario.getLogin( ) ) ;
//
//			try
//			{
//				existenteUsuario = ( Usuario ) q.getSingleResult( ) ;
//			}
//			catch (NoResultException e)
//			{
//				existenteUsuario = null ;
//			}

			if(existenteCliente == null) {
				em.persist(cliente);
			} else {
				cliente.setLogin(existenteCliente.getLogin());
			}
			result = true;
		} catch (Exception e) {
			if(debugInfo) {
				e.printStackTrace();
			}
		}
		return result ;
	}

	public boolean alterar(Cliente cliente) {
		boolean result = false ;
		Cliente existenteCliente = null ;

		try {
			existenteCliente = em.find(Cliente.class, cliente.getLogin());
			if (existenteCliente != null) {
				em.merge(cliente);
				result = true ;
			} else {
				result = false;
			}
		}catch(Exception e) {
			if(debugInfo) {
				e.printStackTrace();
			}
			result = false;
		}
		return result;
	}

	public boolean excluir(Cliente cliente) {
		Cliente obj = null ;
		boolean result = false ;

		try {
			Query q = em.createQuery("from Cliente where login = :login");
			q.setParameter("login", cliente.getLogin());
			obj = (Cliente) q.getSingleResult();
			em.remove(obj);
			result = true;
		}catch(Exception e) {
			if(debugInfo) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Cliente localizarPorLogin(Cliente cliente) {
		Cliente obj = new Cliente();

		try {
			Query query = em.createQuery("from Cliente where login like :login");
			query.setParameter("login", cliente.getLogin());
			obj = (Cliente) query.getSingleResult();
		}catch(Exception e) {
			if(debugInfo){
				e.printStackTrace();
			}
		}
		return obj ;
	}

	// public List<Usuario> localizarPorNome( Usuario usuario )
	// {
	// List<Usuario> result = new ArrayList<Usuario>();
	//
	// try
	// {
	// Query q = em.createQuery( "from Usuario where nome like :nome");
	// q.setParameter( "nome", usuario.getNome() );
	// result = (List<Usuario>)q.getResultList();
	// }
	// catch (Exception e )
	// {
	// if (debugInfo )
	// {
	// e.printStackTrace();
	// }
	// }
	// return result;
	// }
}