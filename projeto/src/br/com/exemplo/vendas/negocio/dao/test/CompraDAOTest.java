package br.com.exemplo.vendas.negocio.dao.test;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
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
import br.com.exemplo.vendas.negocio.entity.Compra;
import br.com.exemplo.vendas.negocio.entity.Usuario;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;

public class CompraDAOTest
{
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    Boolean sucesso1;
    Boolean sucesso2;
    Compra compraBuscada;
    Compra entity;
    List<Usuario> lista;
    CompraVO vo;

    @Test
    public void inserirELocalizarCompra( )
    {
	vo = new CompraVO(new Long(5), new Date(), "aberto", new BigDecimal(1), new Long(1), "alberto");
	entity = entity.create( vo );
	
	try
	{
	    sucesso1 = DaoFactory.getCompraDAO( em ).inserir( entity );
	    Assert.assertTrue( sucesso1 );

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
