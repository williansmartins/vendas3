package br.com.exemplo.vendas.negocio.dao ;

import javax.persistence.EntityManager ;

import br.com.exemplo.vendas.util.exception.SysException ;

public final class DaoFactory {
	
	private static DaoFactory myInstance = null;
	private static UsuarioDAO usuarioDAO_instance;
	private static ClienteDAO clienteDAO_instance;

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
}