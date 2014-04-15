package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Reserva;

public class ReservaDAO extends GenericDAO<Reserva> {
	
	public ReservaDAO(EntityManager em) {
		super(em);
	}

	public ReservaDAO() {
		super(Persistence.createEntityManagerFactory("Vendas").createEntityManager());
	}

	/**
	 * Metodo reponsavel por inserir uma Reserva.java (TBL_RESERVA)
	 * no sistema.
	 * @param Reserva recebe a reserva que sera inserida.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean inserir(Reserva reserva) {
		try{
			localizarPorCodigo(reserva.getCodigo());
			return false;//Reserva com codigo ja existente
		}catch(Exception e){
			try{//OK, nao encontro uma Reserva com este codigo, pode inserir
				em.persist(reserva);
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
	 * Metodo reponsavel por alterar uma Reserva.java (TBL_RESERVA)
	 * no sistema.
	 * @param Reserva recebe a reserva que sera alterada.
	 * @return boolean 
	 * 		true: Para sucesso na insercao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a insercao.
	 */
	public boolean alterar(Reserva reserva) {
		try{
			localizarPorCodigo(reserva.getCodigo());
			em.merge(reserva);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Metodo reponsavel por excluir uma Reserva.java (TBL_RESERVA)
	 * no sistema.
	 * @param Reserva recebe a reserva que sera excluida.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Reserva reserva) {
		try{
			em.remove(reserva);
			return true;
		}catch(Exception e){
			if(debugInfo){
				e.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * Metodo reponsavel por excluir uma Reserva.java (TBL_RESERVA)
	 * no sistema.
	 * @param Long recebe o codigo da reserva que sera excluida.
	 * @return boolean 
	 * 		true: Para sucesso na exclusao.
	 * 		false: Caso ocorra algum problema e nao seja posivel realizar a exclusao.
	 */
	public boolean excluir(Long codigo) {
		try{
			Reserva reserva = localizarPorCodigo(codigo);
			return excluir(reserva);
		}catch(Exception e){
			return false;
		}
	}

	public Reserva localizarPorCodigo(Long codigo) throws Exception {
		try{
			Query query = em.createQuery("from Reserva where codigo = :codigo");
			query.setParameter("codigo", codigo);
			Reserva obj = (Reserva) query.getSingleResult();
			return obj;
		}catch(EntityNotFoundException e){
			throw new Exception("Reserva n\u00e3o encontrada.");
		}catch(NoResultException e){
			throw new Exception("Reserva n\u00e3o encontrada.");
		}catch(NonUniqueResultException e){
			throw new Exception("Reserva n\u00e3o encontrada.");
		}
	}
}