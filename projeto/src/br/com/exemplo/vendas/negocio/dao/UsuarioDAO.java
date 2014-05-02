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
    public boolean inserir( Usuario usuario )
    {
	try
	{
	    em.merge( usuario );
	    return true;
	} catch ( Exception e )
	{
	    System.out.println("Erro ao inserir: " + e.getMessage());
	    if ( debugInfo )
	    {
		e.printStackTrace();
	    }
	    return false;
	}
    }

	/**
	 * Metodo reponsavel por alterar um Usuario.java (TBL_USUARIO)
	 * no sistema.
	 * @param Usuario recebe o usuario que sera alterado.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean alterar(Usuario usuario) {
		try{
			localizarPorLogin(usuario.getLogin());
			em.merge(usuario);
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
			if(debugInfo){
				e.printStackTrace();
			}
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