package br.com.exemplo.vendas.negocio.ejb.interfaces ;

import javax.ejb.Remote;

import br.com.exemplo.vendas.negocio.interfaces.ClienteInterface;

@Remote
public interface ClienteRemote extends ClienteInterface {
	
}