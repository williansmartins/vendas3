package br.com.exemplo.vendas.negocio.dao ;

import java.util.List;

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

	/**
	 * Metodo reponsavel por inserir um Cliente.java (TBL_CLIENTE)
	 * no sistema.
	 * @param Cliente recebe o cliente que sera inserido.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean inserir(Cliente cliente) {
		try{
			//Nao precisa verificar existecia de cliente, pois
			//todos os dados da tabelas poderam ser repetir, apenas
			//o login nao, e unico por usuario e PK.
			em.persist(cliente);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Metodo reponsavel por alterar um Cliente.java (TBL_CLIENTE)
	 * no sistema.
	 * @param Cliente recebe o cliente que sera alterado.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean alterar(Cliente cliente) {
		try{
			localizarPorLogin(cliente.getLogin());
			em.merge(cliente);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Metodo reponsavel por excluir um Cliente.java (TBL_CLIENTE)
	 * no sistema.
	 * @param Cliente recebe o cliente que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Cliente cliente) {
		try{
			em.remove(cliente);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Metodo reponsavel por excluir um Cliente.java (TBL_CLIENTE)
	 * no sistema.
	 * @param String recebe o login do cliente que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(String login) {
		try{
			Cliente cliente = localizarPorLogin(login);
			return excluir(cliente);
		}catch(Exception e){
			return false;
		}
	}
	
	public Cliente localizarPorLogin(Cliente cliente) throws Exception {
		try{
			Query query = em.createQuery("from Cliente where login like :login");
			query.setParameter("login", cliente.getLogin());
			return (Cliente) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}
	}

	public Cliente localizarPorLogin(String login) throws Exception {
		try{
			Query query = em.createQuery("from Cliente where login like :login");
			query.setParameter("login", login);
			return (Cliente) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Cliente n\u00e3o encontrado.");
		}
	}
	
	/**
	 * Busca Cliente.java (TBL_CLIENTE) que sera usado no webservices.
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> localizarPorCompra() throws Exception {
		try{
			Query query = em.createQuery("Select Distinct(c.cliente) from Compra c");
			return (List<Cliente>) query.getResultList();
		}catch(EntityNotFoundException e){
			throw new Exception("Cliente n\u00e3o realizou nenhuma compra.");
		}catch(NoResultException e){
			throw new Exception("Cliente n\u00e3o realizou nenhuma compra.");
		}catch(NonUniqueResultException e){
			throw new Exception("Cliente n\u00e3o realizou nenhuma compra.");
		}
	}
}