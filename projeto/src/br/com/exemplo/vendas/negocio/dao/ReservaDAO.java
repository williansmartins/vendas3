package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.negocio.entity.Reserva;

public class ReservaDAO extends GenericDAO<Reserva> {
	
	public ReservaDAO(EntityManager em) {
		super(em);
	}

	public ReservaDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	public boolean inserir(Reserva reserva) {
		try{
			localizarPorCodigo(reserva.getCodigo());
			return false;//Reserva com codigo ja existente
		}catch(Exception e){
			try{//OK, nao encontro uma Reserva com este codigo, pode inserir
				em.persist(reserva);
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

	public Reserva localizarPorCodigo(Long codigo) throws Exception {
		try{
			Query query = em.createQuery("from Reserva where codigo = :codigo");
			query.setParameter("codigo", codigo);
			Reserva obj = (Reserva) query.getSingleResult();
			return obj;
		}catch(EntityNotFoundException e){
			throw new Exception("Reserva n\u00e3o encontrada.");
		}catch(NoResultException e){
			throw new Exception("Reserva n\u00e3o encontrada.");
		}catch(NonUniqueResultException e){
			throw new Exception("Reserva n\u00e3o encontrada.");
		}
	}
}