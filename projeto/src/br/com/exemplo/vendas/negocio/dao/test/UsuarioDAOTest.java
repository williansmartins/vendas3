package br.com.exemplo.vendas.negocio.dao.test;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.entity.Usuario;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;

public class UsuarioDAOTest
{
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    Boolean sucesso1;
    Boolean sucesso2;
    Usuario usuarioBuscado;
    List<Usuario> lista;

    @Test
    public void inserirELocalizarUsuario( )
    {
	UsuarioVO vo = new UsuarioVO( "Willians1", "senha", "grupo", "perfil", true, new Date() );
	Usuario usuario1 = Usuario.create( vo );

	try
	{
	    sucesso1 = DaoFactory.getUsuarioDAO( em ).inserir( usuario1 );
	    usuarioBuscado = DaoFactory.getUsuarioDAO( em ).localizarPorLogin( usuario1 );

	    Assert.assertTrue( sucesso1 );
	    Assert.assertTrue( usuarioBuscado != null );

	    DaoFactory.getUsuarioDAO( em ).excluir( usuarioBuscado );
	} catch ( Exception e )
	{
	    e.printStackTrace();
	}

    }

    private void zerarSucessos( )
    {
	sucesso1 = false;
	sucesso2 = false;
    }

    @Test
    public void inserirEExcluirUsuario( )
    {
	UsuarioVO vo = new UsuarioVO( "Willians2", "senha", "grupo", "perfil", true, new Date() );
	Usuario usuario2 = Usuario.create( vo );

	try
	{
	    sucesso1 = DaoFactory.getUsuarioDAO( em ).inserir( usuario2 );
	    sucesso2 = DaoFactory.getUsuarioDAO( em ).excluir( usuario2 );
	    usuarioBuscado = DaoFactory.getUsuarioDAO( em ).localizarPorLogin( usuario2 );
	    fail( "Se o método chegar aqui quer dizer que ele falhou, pois esperava uma Exception" );
	} catch ( Exception e )
	{}

	Assert.assertTrue( sucesso1 );
	Assert.assertTrue( sucesso2 );
	Assert.assertTrue( usuarioBuscado == null );

    }

    @Test
    public void inserirElistarTodosUsuariosInseridos( )
    {
	UsuarioVO vo3 = new UsuarioVO( "Willians3", "senha", "grupo", "perfil", true, new Date() );
	UsuarioVO vo4 = new UsuarioVO( "Willians4", "senha", "grupo", "perfil", true, new Date() );
	Usuario usuario3 = Usuario.create( vo3 );
	Usuario usuario4 = Usuario.create( vo4 );
	try
	{
	    sucesso1 = DaoFactory.getUsuarioDAO( em ).inserir( usuario3 );
	    sucesso2 = DaoFactory.getUsuarioDAO( em ).inserir( usuario4 );

	    lista = DaoFactory.getUsuarioDAO( em ).listar();

	    Assert.assertTrue( sucesso1 );
	    Assert.assertTrue( sucesso2 );
	    Assert.assertTrue( lista.size() >= 2 );
	    
	    usuario3 = DaoFactory.getUsuarioDAO( em ).localizarPorLogin( usuario3 );
	    usuario4 = DaoFactory.getUsuarioDAO( em ).localizarPorLogin( usuario4 );
	    
	    sucesso1 = DaoFactory.getUsuarioDAO( em ).excluir( usuario3 );
	    sucesso2 = DaoFactory.getUsuarioDAO( em ).excluir( usuario4 );
	    
	} catch ( Exception e )
	{}


    }

    /* ABAIXO FICAM OS MÉTODOS QUE GERENCIAM AS TRANSAÇÕES */

    @BeforeClass
    public static void createEntityManagerFactory( )
    {
	emf = Persistence.createEntityManagerFactory( "VendasJunit" );
    }

    @AfterClass
    public static void closeEntityManagerFactory( )
    {
	if ( em.isOpen() )
	{
	    em.close();
	}
	emf.close();
    }

    @Before
    public void beginTransaction( )
    {
	em = emf.createEntityManager();
	em.getTransaction().begin();
	zerarSucessos();
    }

    @After
    public void rollbackTransaction( )
    {
	if ( em.getTransaction().isActive() )
	{
	    em.flush();
	    em.getTransaction().commit();
	    // em.getTransaction().rollback();
	}
    }
}
