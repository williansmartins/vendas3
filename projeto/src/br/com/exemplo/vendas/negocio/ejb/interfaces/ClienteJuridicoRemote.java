package br.com.exemplo.vendas.negocio.ejb.interfaces ;

import javax.ejb.Remote;

import br.com.exemplo.vendas.negocio.interfaces.ClienteJuridicoInterface;

@Remote
public interface ClienteJuridicoRemote extends ClienteJuridicoInterface {
	
}