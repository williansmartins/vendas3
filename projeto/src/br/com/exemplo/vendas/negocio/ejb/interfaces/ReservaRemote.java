package br.com.exemplo.vendas.negocio.ejb.interfaces ;

import javax.ejb.Remote;

import br.com.exemplo.vendas.negocio.interfaces.ReservaInterface;

@Remote
public interface ReservaRemote extends ReservaInterface {
	
}