package br.com.exemplo.vendas.negocio.dao ;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {
	
	public ProdutoDAO(EntityManager em) {
		super(em);
	}

	public ProdutoDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	/**
	 * Metodo reponsavel por inserir um Produto.java (TBL_PRODUTO)
	 * no sistema.
	 * @param Produto recebe o produto que sera inserido.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean inserir(Produto produto) {
		try{
			localizarPorCodigo(produto.getCodigo());
			return false;//Produto com codigo ja existente
		}catch(Exception e){
			try{//OK, nao encontro um Produto com este codigo, pode inserir
				em.persist(produto);
				return true;
			}catch(Exception f){
				if(debugInfo){
					f.printStackTrace();
				}
				return false;
			}
		}
	}

	/**
	 * Metodo reponsavel por alterar um Produto.java (TBL_PRODUTO)
	 * no sistema.
	 * @param Produto recebe o produto que sera alterado.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean alterar(Produto produto) {
		try{
			localizarPorCodigo(produto.getCodigo());
			em.merge(produto);
			return true;
		}catch(Exception e){
			if(debugInfo) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * Metodo reponsavel por excluir um Produto.java (TBL_PRODUTO)
	 * no sistema.
	 * @param Produto recebe o produto que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Produto produto) {
		try{
			em.remove(produto);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * Metodo reponsavel por excluir um Produto.java (TBL_PRODUTO)
	 * no sistema.
	 * @param Long recebe o codigo do produto que sera excluido.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Long codigo) {
		try{
			Produto produto = localizarPorCodigo(codigo);
			return excluir(produto);
		}catch(Exception e){
			return false;
		}
	}

	public Produto localizarPorCodigo(Long codigo) throws Exception {
		try{
			Query query = em.createQuery("from Produto where codigo = :codigo");
			query.setParameter("codigo", codigo);
			Produto obj = (Produto) query.getSingleResult();
			return obj;
		}catch(EntityNotFoundException e){
			throw new Exception("Produto n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Produto n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Produto n\u00e3o encontrado.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> localizarPorQuantidadeAcimaDeEPrecoAbaixoDe(BigDecimal preco, Integer quantidadeEstoque) throws Exception {
		try{
			Query query = em.createQuery("from Produto where preco < :preco and quantidadeEstoque >= :quantidadeEstoque");
			query.setParameter("preco", preco);
			query.setParameter("quantidadeEstoque", quantidadeEstoque);
			return (List<Produto>) query.getResultList();
		}catch(EntityNotFoundException e){
			throw new Exception("Produto n\u00e3o encontrado.");
		}catch(NoResultException e){
			throw new Exception("Produto n\u00e3o encontrado.");
		}catch(NonUniqueResultException e){
			throw new Exception("Produto n\u00e3o encontrado.");
		}
	}
}