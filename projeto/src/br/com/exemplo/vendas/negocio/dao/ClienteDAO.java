package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
		try{
			//Nao precisa verificar existecia de cliente, pois
			//todos os dados da tabelas poderam ser repetir, apenas
			//o login nao e unico por usuario.
			em.persist(cliente);
			result = true;
		}catch(Exception e){
			if(debugInfo){
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

	public Cliente localizarPorLogin(String login) throws Exception {
		try{
			Query query = em.createQuery("from Cliente where login like :login");
			query.setParameter("login", login);
			Cliente obj = (Cliente) query.getSingleResult();
			return obj;
		}catch(EntityNotFoundException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}
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