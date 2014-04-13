package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager ;

import br.com.exemplo.vendas.util.exception.SysException ;

public final class DaoFactory {
	
	private static DaoFactory myInstance = null;
	private static UsuarioDAO usuarioDAO_instance;
	private static ClienteDAO clienteDAO_instance;
	private static ClienteFisicoDAO clienteFisicoDAO_instance;
	private static ClienteJuridicoDAO clienteJuridicoDAO_instance;
	private static ProdutoDAO produtoDAO_instance;
	private static ReservaDAO reservaDAO_instance;
	private static CompraDAO compraDAO_instance;
	private static ItemDAO itemDAO_instance;

	private DaoFactory() throws SysException {}
	private synchronized static void initialize() throws SysException {
		if(myInstance == null) {
			myInstance = new DaoFactory();
		}
	}

	public static UsuarioDAO getUsuarioDAO(EntityManager em) throws SysException {
		initialize();
		if(usuarioDAO_instance == null) {
			usuarioDAO_instance = new UsuarioDAO(em);
		}
		return usuarioDAO_instance;
	}
	
	public static ClienteDAO getClienteDAO(EntityManager em) throws SysException {
		initialize();
		if(clienteDAO_instance == null) {
			clienteDAO_instance = new ClienteDAO(em);
		}
		return clienteDAO_instance;
	}
	
	public static ClienteFisicoDAO getClienteFisicoDAO(EntityManager em) throws SysException {
		initialize();
		if(clienteFisicoDAO_instance == null) {
			clienteFisicoDAO_instance = new ClienteFisicoDAO(em);
		}
		return clienteFisicoDAO_instance;
	}
	
	public static ClienteJuridicoDAO getClienteJuridicoDAO(EntityManager em) throws SysException {
		initialize();
		if(clienteJuridicoDAO_instance == null) {
			clienteJuridicoDAO_instance = new ClienteJuridicoDAO(em);
		}
		return clienteJuridicoDAO_instance;
	}
	
	public static ProdutoDAO getProdutoDAO(EntityManager em) throws SysException {
		initialize();
		if(produtoDAO_instance == null) {
			produtoDAO_instance = new ProdutoDAO(em);
		}
		return produtoDAO_instance;
	}
	
	public static ReservaDAO getReservaDAO(EntityManager em) throws SysException {
		initialize();
		if(reservaDAO_instance == null) {
			reservaDAO_instance = new ReservaDAO(em);
		}
		return reservaDAO_instance;
	}
	
	public static CompraDAO getCompraDAO(EntityManager em) throws SysException {
		initialize();
		if(compraDAO_instance == null) {
			compraDAO_instance = new CompraDAO(em);
		}
		return compraDAO_instance;
	}
	
	public static ItemDAO getItemDAO(EntityManager em) throws SysException {
		initialize();
		if(itemDAO_instance == null) {
			itemDAO_instance = new ItemDAO(em);
		}
		return itemDAO_instance;
	}
}