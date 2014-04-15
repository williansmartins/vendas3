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

	/**
	 * Metodo reponsavel por inserir um Item.java (TBL_ITEM)
	 * no sistema.
	 * @param Item recebe o item que sera inserido.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
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

	/**
	 * Metodo reponsavel por alterar um Item.java (TBL_ITEM)
	 * no sistema.
	 * @param Item recebe o item que sera alterado.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean alterar(Item item) {
		try{
			Item otherItem = em.find(Item.class, item.getId());
			if(otherItem != null){
				em.merge(item);
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Metodo reponsavel por excluir um Item.java (TBL_ITEM)
	 * no sistema.
	 * @param item recebe o item que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Item item) {
		try{
			em.remove(item);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * Metodo reponsavel por excluir um Item.java (TBL_ITEM)
	 * no sistema.
	 * @param Long recebe o id do item que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Long id) {
		try{
			Item item = em.find(Item.class, id);
			if(item == null){
				return false;
			}else{
				return excluir(item);
			}
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Metodo reponsavel por excluir um Item.java (TBL_ITEM)
	 * no sistema.
	 * @param Long recebe o codigo da reserva do item que sera excluido.
	 * @param Long recebe o numero de compra do item que sera excluido.
	 * @param Long recebe o codigo do produto do item que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Long codigoReserva, Long numeroCompra, Long codigoProduto) {
		try{
			Item item = localizarPorReservaCompraProduto(codigoReserva, numeroCompra, codigoProduto);
			return excluir(item);
		}catch(Exception e){
			return false;
		}
	}

	public Item localizarPorReservaCompraProduto(Long codigoReserva, Long numeroCompra, Long codigoProduto) throws Exception {
		try{
			Query query = em.createQuery("from Item where produto.codigo = :codigoProduto and compra.numero = :numeroCompra and reserva.codigo = :codigoReserva");
			query.setParameter("codigoReserva", codigoReserva);
			query.setParameter("numeroCompra", numeroCompra);
			query.setParameter("codigoProduto", codigoProduto);
			return (Item) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Item n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Item n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Item n\u00e3o encontrado.");
		}
	}
}