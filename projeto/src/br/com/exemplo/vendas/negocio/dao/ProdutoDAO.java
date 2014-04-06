package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {
	
	public ProdutoDAO(EntityManager em) {
		super(em);
	}

	public ProdutoDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	public boolean inserir(Produto produto) {
		boolean result = false ;
		Produto existenteProduto = null ;

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

			if(existenteProduto == null) {
				em.persist(produto);
			} else {
				produto.setCodigo(existenteProduto.getCodigo());
			}
			result = true;
		} catch (Exception e) {
			if(debugInfo) {
				e.printStackTrace();
			}
		}
		return result ;
	}

	public boolean alterar(Produto produto) {
		boolean result = false ;
		Produto existenteProduto = null ;

		try{
			existenteProduto = em.find(Produto.class, produto.getCodigo());
			if(existenteProduto != null){
				em.merge(produto);
				result = true ;
			}else{
				result = false;
			}
		}catch(Exception e){
			if(debugInfo) {
				e.printStackTrace();
			}
			result = false;
		}
		return result;
	}

	public boolean excluir(Produto produto) {
		Produto obj = null ;
		boolean result = false ;

		try {
			Query q = em.createQuery("from Produto where login = :login");
			q.setParameter("login", produto.getCodigo());
			obj = (Produto) q.getSingleResult();
			em.remove(obj);
			result = true;
		}catch(Exception e) {
			if(debugInfo) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Produto localizarPorCodigo(Long codigo) throws Exception {
		try{
			Query query = em.createQuery("from Produto where codigo = :codigo");
			query.setParameter("codigo", codigo);
			Produto obj = (Produto) query.getSingleResult();
			return obj;
		}catch(EntityNotFoundException e){
			throw new Exception("Produto n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Produto n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Produto n\u00e3o encontrado.");
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