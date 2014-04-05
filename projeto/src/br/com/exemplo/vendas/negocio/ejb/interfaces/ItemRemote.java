package br.com.exemplo.vendas.negocio.ejb.interfaces ;

import javax.ejb.Remote;

import br.com.exemplo.vendas.negocio.interfaces.ItemInterface;

@Remote
public interface ItemRemote extends ItemInterface {
	
}