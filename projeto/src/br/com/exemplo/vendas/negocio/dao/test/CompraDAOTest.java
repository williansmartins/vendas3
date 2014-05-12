package br.com.exemplo.vendas.negocio.dao.test;

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
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;

public class CompraDAOTest
{
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    Boolean sucesso1;
    Boolean sucesso2;
    Compra compraBuscado;
    Compra entity;
    List<Compra> lista;
    CompraVO vo;

    @Test
    public void inserirELocalizarCompra( )
    {
	vo = new CompraVO(new Long(6), new Date(), "A", new BigDecimal(1), new Long(1), "alberto");
	entity = Compra.create( vo );
	
	try
	{
	    sucesso1 = DaoFactory.getCompraDAO( em ).inserir( entity );
	    entity = DaoFactory.getCompraDAO(em).localizar(entity.getNumero());
	    Assert.assertTrue( sucesso1 );
	    Assert.assertFalse(entity == null);
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
	public void inserirEExcluirCompra() {
		CompraVO vo = new CompraVO(new Long(1), new Date(), "A", new BigDecimal(50.00), 1L, "cliente");
		Compra o2 = Compra.create(vo);

		try {
			sucesso1 = DaoFactory.getCompraDAO(em).inserir(o2);
			sucesso2 = DaoFactory.getCompraDAO(em).excluir(o2);
			compraBuscado = DaoFactory.getCompraDAO(em).localizar(o2.getNumero());
		} catch (Exception e) {
		}

		Assert.assertTrue(sucesso1);
		Assert.assertTrue(sucesso2);
		Assert.assertTrue(compraBuscado == null);

	}

	@Test
	public void inserirElistarTodosComprasInseridos() {
		CompraVO vo3 = new CompraVO(new Long(2), new Date(), "A", new BigDecimal(50.00), 2L, "cliente2");
		CompraVO vo4 = new CompraVO(new Long(3), new Date(), "A", new BigDecimal(50.00), 3L, "cliente3");
		Compra o3 = Compra.create(vo3);
		Compra o4 = Compra.create(vo4);
		try {
			sucesso1 = DaoFactory.getCompraDAO(em).inserir(o3);
			sucesso2 = DaoFactory.getCompraDAO(em).inserir(o4);

			lista = DaoFactory.getCompraDAO(em).listar();
			for (Compra compra : lista) {
				System.out.println(compra);
			}

			Assert.assertTrue(sucesso1);
			Assert.assertTrue(sucesso2);
			Assert.assertTrue(lista.size() >= 2);

			o3 = DaoFactory.getCompraDAO(em).localizarPorNumero(vo3.getNumero());
			o4 = DaoFactory.getCompraDAO(em).localizarPorNumero(vo4.getNumero());

			sucesso1 = DaoFactory.getCompraDAO(em).excluir(o3);
			sucesso2 = DaoFactory.getCompraDAO(em).excluir(o4);

		} catch (Exception e) {
		}

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
