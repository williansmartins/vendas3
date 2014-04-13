package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.negocio.entity.ClienteFisico;

public class ClienteFisicoDAO extends GenericDAO<ClienteFisico> {
	
	public ClienteFisicoDAO(EntityManager em) {
		super(em);
	}

	public ClienteFisicoDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	/**
	 * Metodo reponsavel por inserir um ClienteFisico.java (TBL_CLIENTE_FISICO)
	 * no sistema.
	 * @param ClienteFisico recebe o cliente fisico que sera inserido.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean inserir(ClienteFisico clienteFisico) {
		try{
			//Nao precisa verificar existecia de cliente, pois
			//todos os dados da tabelas poderam ser repetir, apenas
			//o login nao, e unico por usuario e PK.
			em.persist(clienteFisico);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
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

	/**
	 * Metodo reponsavel por excluir um ClienteFisico.java (TBL_CLIENTE_FISICO)
	 * no sistema.
	 * @param ClienteFisico recebe o cliente fisico que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(ClienteFisico clienteFisico) {
		try{
			em.remove(clienteFisico);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Metodo reponsavel por excluir um ClienteFisico.java (TBL_CLIENTE_FISICO)
	 * no sistema.
	 * @param String recebe o login do cliente fisico que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(String login) {
		try{
			ClienteFisico clienteFisico = localizarPorLogin(login);
			return excluir(clienteFisico);
		}catch(Exception e){
			return false;
		}
	}
	
	public ClienteFisico localizarPorLogin(ClienteFisico clienteFisico) throws Exception {
		try{
			Query query = em.createQuery("from ClienteFisico where login like :login");
			query.setParameter("login", clienteFisico.getLogin());
			return (ClienteFisico) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Cliente F\u00edsico n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Cliente F\u00edsico n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Cliente F\u00edsico n\u00e3o encontrado.");
		}
	}

	public ClienteFisico localizarPorLogin(String login) throws Exception {
		try{
			Query query = em.createQuery("from ClienteFisico where login like :login");
			query.setParameter("login", login);
			return (ClienteFisico) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Cliente F\u00edsico n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Cliente F\u00edsico n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Cliente F\u00edsico n\u00e3o encontrado.");
		}
	}
}