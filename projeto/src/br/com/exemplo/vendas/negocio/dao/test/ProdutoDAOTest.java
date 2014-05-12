package br.com.exemplo.vendas.negocio.dao.test;

import java.math.BigDecimal;
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
import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;

public class ProdutoDAOTest {
	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	Boolean sucesso1;
	Boolean sucesso2;
	Produto produtoBuscado;
	List<Produto> lista;

	@Test
	public void inserirELocalizarProduto() {
		ProdutoVO vo = new ProdutoVO(new Long(12), "Produto teste",
				new BigDecimal(100), "Sei la", new Integer(1000));
		Produto o = Produto.create(vo);

		try {
			sucesso1 = DaoFactory.getProdutoDAO(em).inserir(o);
			produtoBuscado = DaoFactory.getProdutoDAO(em).localizar(o.getCodigo());

			Assert.assertTrue(sucesso1);
			Assert.assertTrue(produtoBuscado != null);

			DaoFactory.getProdutoDAO(em).excluir(produtoBuscado);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void zerarSucessos() {
		sucesso1 = false;
		sucesso2 = false;
	}

	@Test
	public void inserirEExcluirProduto() {
		ProdutoVO vo = new ProdutoVO(new Long(12), "Produto teste",
				new BigDecimal(100), "Sei la", new Integer(1000));
		Produto o2 = Produto.create(vo);

		try {
			sucesso1 = DaoFactory.getProdutoDAO(em).inserir(o2);
			sucesso2 = DaoFactory.getProdutoDAO(em).excluir(o2);
			produtoBuscado = DaoFactory.getProdutoDAO(em).localizar(o2.getCodigo());
		} catch (Exception e) {
		}

		Assert.assertTrue(sucesso1);
		Assert.assertTrue(sucesso2);
		Assert.assertTrue(produtoBuscado == null);

	}

	@Test
	public void inserirElistarTodosProdutosInseridos() {
		ProdutoVO vo3 = new ProdutoVO(new Long(12), "Produto teste3",
				new BigDecimal(103), "Sei la3", new Integer(1003));
		ProdutoVO vo4 = new ProdutoVO(new Long(13), "Produto teste4",
				new BigDecimal(104), "Sei la4", new Integer(1004));
		Produto o3 = Produto.create(vo3);
		Produto o4 = Produto.create(vo4);
		try {
			sucesso1 = DaoFactory.getProdutoDAO(em).inserir(o3);
			sucesso2 = DaoFactory.getProdutoDAO(em).inserir(o4);

			lista = DaoFactory.getProdutoDAO(em).listar();

			Assert.assertTrue(sucesso1);
			Assert.assertTrue(sucesso2);
			Assert.assertTrue(lista.size() >= 2);

			o3 = DaoFactory.getProdutoDAO(em).localizarPorCodigo(o3.getCodigo());
			o4 = DaoFactory.getProdutoDAO(em).localizarPorCodigo(o4.getCodigo());

			sucesso1 = DaoFactory.getProdutoDAO(em).excluir(o3);
			sucesso2 = DaoFactory.getProdutoDAO(em).excluir(o4);

		} catch (Exception e) {
		}

	}

	/* ABAIXO FICAM OS MÉTODOS QUE GERENCIAM AS TRANSAÇÕES */

	@BeforeClass
	public static void createEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("VendasJunit");
	}

	@AfterClass
	public static void closeEntityManagerFactory() {
		if (em.isOpen()) {
			em.close();
		}
		emf.close();
	}

	@Before
	public void beginTransaction() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		zerarSucessos();
	}

	@After
	public void rollbackTransaction() {
		if (em.getTransaction().isActive()) {
			em.flush();
			em.getTransaction().commit();
			// em.getTransaction().rollback();
		}
	}
}
