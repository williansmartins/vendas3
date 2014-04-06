package br.com.exemplo.vendas.negocio.ejb.interfaces ;

import javax.ejb.Remote;

import br.com.exemplo.vendas.negocio.interfaces.CompraInterface;

@Remote
public interface CompraRemote extends CompraInterface {
	
}