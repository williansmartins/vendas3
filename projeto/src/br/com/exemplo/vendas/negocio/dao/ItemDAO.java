package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Item;

public class ItemDAO extends GenericDAO<Item> {
	
	public ItemDAO(EntityManager em) {
		super(em);
	}

	public ItemDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	public boolean inserir(Item item) {
		try{
			em.persist(item);
			return true;
		}catch(Exception f){
			if(debugInfo){
				f.printStackTrace();
			}
			return false;
		}
	}

	public boolean alterar(Item item) {
		boolean result = false ;
		Item existenteItem = null ;

		try{
			//existenteItem = em.find(Item.class, item.getCodigo());
			if(existenteItem != null){
				em.merge(item);
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

	public boolean excluir(Item item) {
		Item obj = null ;
		boolean result = false ;

		try {
			Query q = em.createQuery("from Item where login = :login");
			//q.setParameter("login", item.getCodigo());
			obj = (Item) q.getSingleResult();
			em.remove(obj);
			result = true;
		}catch(Exception e) {
			if(debugInfo) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Item localizar(Long codigoReserva, Long numeroCompra, Long codigoProduto) throws Exception {
		try{
			Query query = em.createQuery("from Item where produto.codigo = :codigoProduto and compra.numero = :numeroCompra and reserva.codigo = :codigoReserva");
			query.setParameter("codigoReserva", codigoReserva);
			query.setParameter("numeroCompra", numeroCompra);
			query.setParameter("codigoProduto", codigoProduto);
			Item obj = (Item) query.getSingleResult();
			return obj;
		}catch(EntityNotFoundException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}catch(NoResultException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}catch(NonUniqueResultException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}
	}
}