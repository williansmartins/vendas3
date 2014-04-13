package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.negocio.entity.ClienteJuridico;

public class ClienteJuridicoDAO extends GenericDAO<ClienteJuridico> {
	
	public ClienteJuridicoDAO(EntityManager em) {
		super(em);
	}

	public ClienteJuridicoDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	/**
	 * Metodo reponsavel por inserir um ClienteJuridico.java (TBL_CLIENTE_JURIDICO)
	 * no sistema.
	 * @param ClienteJuridico recebe o cliente juridico que sera inserido.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean inserir(ClienteJuridico clienteJuridico) {
		try{
			//Nao precisa verificar existecia de cliente, pois
			//todos os dados da tabelas poderam ser repetir, apenas
			//o login nao, e unico por usuario e PK.
			em.persist(clienteJuridico);
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
	 * Metodo reponsavel por excluir um ClienteJuridico.java (TBL_CLIENTE_JURIDICO)
	 * no sistema.
	 * @param ClienteJuridico recebe o cliente juridico que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(ClienteJuridico clienteJuridico) {
		try{
			em.remove(clienteJuridico);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Metodo reponsavel por excluir um ClienteJuridico.java (TBL_CLIENTE_JURIDICO)
	 * no sistema.
	 * @param String recebe o login do cliente juridico que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(String login) {
		try{
			ClienteJuridico clienteJuridico = localizarPorLogin(login);
			return excluir(clienteJuridico);
		}catch(Exception e){
			return false;
		}
	}
	
	public ClienteJuridico localizarPorLogin(ClienteJuridico clienteJuridico) throws Exception {
		try{
			Query query = em.createQuery("from ClienteJuridico where login like :login");
			query.setParameter("login", clienteJuridico.getLogin());
			return (ClienteJuridico) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Cliente Jur\u00eddico n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Cliente Jur\u00eddico n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Cliente Jur\u00eddico n\u00e3o encontrado.");
		}
	}

	public ClienteJuridico localizarPorLogin(String login) throws Exception {
		try{
			Query query = em.createQuery("from ClienteJuridico where login like :login");
			query.setParameter("login", login);
			return (ClienteJuridico) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Cliente Jur\u00eddico n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Cliente Jur\u00eddico n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Cliente Jur\u00eddico n\u00e3o encontrado.");
		}
	}
}