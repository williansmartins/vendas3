package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	public UsuarioDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	/**
	 * Metodo reponsavel por inserir um Usuario.java (TBL_USUARIO)
	 * no sistema.
	 * @param Usuario recebe o usuario que sera inserido.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean inserir(Usuario usuario) {
		try{
			//Nao precisa verificar existecia de cliente, pois
			//todos os dados da tabelas poderam ser repetir, apenas
			//o login nao, e unico por usuario e PK.
			em.persist(usuario);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	public boolean alterar( Usuario usuario ) {
		/*
		boolean result = false ;
		Usuario existenteUsuario = null ;

		try
		{
			existenteUsuario = em.find( Usuario.class, usuario.getLogin( ) ) ;
			if (existenteUsuario != null)
			{
				em.merge( usuario ) ;
				result = true ;
			}
			else
			{
				result = false ;
			}
		}
		catch (Exception e)
		{
			if (debugInfo)
			{
				e.printStackTrace( ) ;
			}
			result = false ;
		}
		return result ;
		*/
		return false;
	}

	/**
	 * Metodo reponsavel por excluir um Usuario.java (TBL_USUARIO)
	 * no sistema.
	 * @param Usuario recebe o usuario que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Usuario usuario) {
		try{
			em.remove(usuario);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Metodo reponsavel por excluir um Usuario.java (TBL_USUARIO)
	 * no sistema.
	 * @param String recebe o login do usuario que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(String login) {
		try{
			Usuario usuario = localizarPorLogin(login);
			return excluir(usuario);
		}catch(Exception e){
			return false;
		}
	}

	public Usuario localizarPorLogin(String login) throws Exception {
		try{
			Query query = em.createQuery("from Usuario where login like :login");
			query.setParameter("login", login);
			return (Usuario) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Usuario n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Usuario n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Usuario n\u00e3o encontrado.");
		}
	}
	
	
	public Usuario localizarPorLogin(Usuario usuario) throws Exception {
		try{
			Query query = em.createQuery("from Usuario where login like :login");
			query.setParameter("login", usuario.getLogin());
			return (Usuario) query.getSingleResult();
		}catch(EntityNotFoundException e){
			throw new Exception("Usuario n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Usuario n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Usuario n\u00e3o encontrado.");
		}
	}
}