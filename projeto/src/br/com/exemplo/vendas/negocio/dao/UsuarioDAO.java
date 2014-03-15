package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager ;
import javax.persistence.NoResultException ;
import javax.persistence.Persistence ;
import javax.persistence.Query ;

import br.com.exemplo.vendas.negocio.entity.Usuario ;

public class UsuarioDAO extends GenericDAO<Usuario>
{
	public UsuarioDAO( EntityManager em )
	{
		super( em ) ;
	}

	public UsuarioDAO( )
	{
		super( Persistence.createEntityManagerFactory( "Vendas" ).createEntityManager( ) ) ;
	}

	public boolean inserir( Usuario usuario )
	{
		boolean result = false ;
		Usuario existenteUsuario = null ;

		try
		{
//			Query q = em.createQuery( "from Usuario where login like :login" ) ;
//			q.setParameter( "login", usuario.getLogin( ) ) ;
//
//			try
//			{
//				existenteUsuario = ( Usuario ) q.getSingleResult( ) ;
//			}
//			catch (NoResultException e)
//			{
//				existenteUsuario = null ;
//			}

			if (existenteUsuario == null)
			{
				em.persist( usuario ) ;
			}
			else
			{
				usuario.setLogin( existenteUsuario.getLogin( ) ) ;
			}
			result = true ;

		}
		catch (Exception e)
		{
			if (debugInfo)
			{
				e.printStackTrace( ) ;
			}
		}
		return result ;
	}

	public boolean alterar( Usuario usuario )
	{
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
	}

	public boolean excluir( Usuario usuario )
	{
		Usuario obj = null ;
		boolean result = false ;

		try
		{
			Query q = em.createQuery( "from Usuario where login = :login" ) ;
			q.setParameter( "login", usuario.getLogin( ) ) ;
			obj = ( Usuario ) q.getSingleResult( ) ;
			em.remove( obj ) ;
			result = true ;
		}
		catch (Exception e)
		{
			if (debugInfo)
			{
				e.printStackTrace( ) ;
			}
		}
		return result ;
	}

	public Usuario localizarPorLogin( Usuario usuario )
	{
		Usuario obj = new Usuario( ) ;

		try
		{
			Query query = em.createQuery( "from Usuario where login like :login" ) ;
			query.setParameter( "login", usuario.getLogin( ) ) ;
			obj = ( Usuario ) query.getSingleResult( ) ;
		}
		catch (Exception e)
		{
			if (debugInfo)
			{
				e.printStackTrace( ) ;
			}
		}
		return obj ;
	}

	// public List<Usuario> localizarPorNome( Usuario usuario )
	// {
	// List<Usuario> result = new ArrayList<Usuario>();
	//
	// try
	// {
	// Query q = em.createQuery( "from Usuario where nome like :nome");
	// q.setParameter( "nome", usuario.getNome() );
	// result = (List<Usuario>)q.getResultList();
	// }
	// catch (Exception e )
	// {
	// if (debugInfo )
	// {
	// e.printStackTrace();
	// }
	// }
	// return result;
	// }
}