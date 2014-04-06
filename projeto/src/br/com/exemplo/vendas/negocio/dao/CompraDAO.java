package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.negocio.entity.Compra;

public class CompraDAO extends GenericDAO<Compra> {
	
	public CompraDAO(EntityManager em) {
		super(em);
	}

	public CompraDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	public boolean inserir(Compra compra) {
		try{
			localizarPorNumero(compra.getNumero());
			return false;//Compra com numero ja existente
		}catch(Exception e){
			try{//OK, nao encontro uma Compra com este numero, pode inserir
				em.persist(compra);
				return true;
			}catch(Exception f){
				if(debugInfo){
					f.printStackTrace();
				}
				return false;
			}
		}
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

	public Compra localizarPorNumero(Long numero) throws Exception {
		try{
			Query query = em.createQuery("from Compra where numero = :numero");
			query.setParameter("numero", numero);
			Compra obj = (Compra) query.getSingleResult();
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