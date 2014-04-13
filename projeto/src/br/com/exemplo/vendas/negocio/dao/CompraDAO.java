package br.com.exemplo.vendas.negocio.dao ;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Compra;
import br.com.exemplo.vendas.negocio.entity.Produto;

public class CompraDAO extends GenericDAO<Compra> {
	
	public CompraDAO(EntityManager em) {
		super(em);
	}

	public CompraDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	/**
	 * Metodo reponsavel por inserir uma Compra.java (TBL_COMPRA)
	 * no sistema.
	 * @param Compra recebe a compra que sera inserida.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
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

	/**
	 * Metodo reponsavel por excluir uma Compra.java (TBL_COMPRA)
	 * no sistema.
	 * @param Compra recebe a compra que sera excluida.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Compra compra) {
		try {
			em.remove(compra);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * Metodo reponsavel por excluir uma Compra.java (TBL_COMPRA)
	 * no sistema.
	 * @param Long recebe o numero da compra que sera excluida.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Long numero) {
		try{
			Compra compra = localizarPorNumero(numero);
			return excluir(compra);
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * Busca uma Compra.java (TBL_COMPRA) por numero.
	 */
	public Compra localizarPorNumero(Long numero) throws Exception {
		try{
			Query query = em.createQuery("from Compra where numero = :numero");
			query.setParameter("numero", numero);
			return (Compra) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}catch(NoResultException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}catch(NonUniqueResultException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}
	}
	
	/**
	 * Busca Compra.java (TBL_COMPRA) que sera usada no webservices.
	 */
	@SuppressWarnings("unchecked")
	public List<Compra> localizarPorValorAbaixoDe(BigDecimal valor) throws Exception {
		try{
			Query query = em.createQuery("from Compra where valor between :valorDe and :valorAte");
			query.setParameter("valorDe", new BigDecimal(0));
			query.setParameter("valorAte", valor);
			return (List<Compra>) query.getResultList();
		}catch(EntityNotFoundException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}catch(NoResultException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}catch(NonUniqueResultException e){
			throw new Exception("Compra n\u00e3o encontrada.");
		}
	}
}